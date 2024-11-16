package com.theprojectchow.backend.forum.domain.model.aggregates;

import com.theprojectchow.backend.forum.domain.model.commands.CreatePostCommand;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Post extends AuditableAbstractAggregateRoot<Post> {
    private String content;
    private String image;

    public Post() {
        this.content = Strings.EMPTY;
        this.image = Strings.EMPTY;
    }
    public Post(String content, String image) {
        this();
        this.content = content;
        this.image = image;
    }
    public  Post(CreatePostCommand command) {
        this();
        this.content = command.content();
        this.image = command.image();
    }
    public Post updateInformation(String content, String image) {
        this.content = content;
        this.image = image;
        return this;
    }
}
