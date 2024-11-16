package com.theprojectchow.backend.profile.application.internal.outboundservice.acl;

import com.theprojectchow.backend.iam.interfaces.acl.IamContextFacade;
import com.theprojectchow.backend.profile.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalIamService {
    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<UserId> createUser(String email, String password, List<String> roles) {
        var userId = iamContextFacade.createUser(email, password, roles);
        if (userId == 0) return Optional.empty();
        return Optional.of(new UserId(userId));
    }
}
