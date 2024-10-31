package com.theprojectchow.backend.profile.interfaces.REST;

import com.theprojectchow.backend.profile.application.internal.queryservices.ProfileQueryServiceImpl;
import com.theprojectchow.backend.profile.domain.model.commands.DeleteProfileCommand;
import com.theprojectchow.backend.profile.domain.model.queries.GetAllProfilesByStatusQuery;
import com.theprojectchow.backend.profile.domain.model.queries.GetAllProfilesQuery;
import com.theprojectchow.backend.profile.domain.model.queries.GetProfileByIdQuery;
import com.theprojectchow.backend.profile.domain.services.ProfileCommandService;
import com.theprojectchow.backend.profile.domain.services.ProfileQueryService;
import com.theprojectchow.backend.profile.interfaces.REST.resources.CreateProfileResource;
import com.theprojectchow.backend.profile.interfaces.REST.resources.ProfileResource;
import com.theprojectchow.backend.profile.interfaces.REST.resources.UpdateProfileResource;
import com.theprojectchow.backend.profile.interfaces.REST.transform.CreateProfileCommandFromAssembler;
import com.theprojectchow.backend.profile.interfaces.REST.transform.ProfileResourceFromEntityAssembler;
import com.theprojectchow.backend.profile.interfaces.REST.transform.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profiles Management Endpoints")
public class ProfileController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService, ProfileQueryServiceImpl profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource createProfileResource) {
        var createProfileCommand = CreateProfileCommandFromAssembler.toCommandFromResource(createProfileResource);
        var profileId = profileCommandService.handle(createProfileCommand);
        if(profileId == null){
            return ResponseEntity.badRequest().build();
        }
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if(profile.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if(profile.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.OK);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProfileResource>> getProfilesByStatus(@PathVariable String status) {
        var getAllProfilesByStatusQuery = new GetAllProfilesByStatusQuery(status);
        var profiles = profileQueryService.handle(getAllProfilesByStatusQuery);
        if(profiles.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var profileResources = profiles.stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(profileResources);
    }
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles(){
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(profileResources);
    }
    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId){
        var deleteProfileCommand = new DeleteProfileCommand(profileId);
        profileCommandService.handle(deleteProfileCommand);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long profileId, @RequestBody UpdateProfileResource profileResource) {
        var updateProfileCommand = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(profileId, profileResource);
        var updatedProfile = profileCommandService.handle(updateProfileCommand);
        if(updatedProfile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var profileResourceUpdated = ProfileResourceFromEntityAssembler.toResourceFromEntity(updatedProfile.get());
        return new ResponseEntity<>(profileResourceUpdated, HttpStatus.OK);
    }
}
