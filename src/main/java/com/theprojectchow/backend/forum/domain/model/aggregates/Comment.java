package com.theprojectchow.backend.forum.domain.model.aggregates;

import com.theprojectchow.backend.forum.domain.model.commands.CreateCommentCommand;
import com.theprojectchow.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Comment extends AuditableAbstractAggregateRoot<Comment> {
    private String author;
    private String content;
    @ManyToOne
    private Post post;

    public Comment() {
        this.post = null;
        this.author = Strings.EMPTY;
        this.content = Strings.EMPTY;
    }
    public Comment(String author, String comment,Post post) {
        this();
        this.author = author;
        this.content = comment;
        this.post = post;
    }
    public Comment(CreateCommentCommand command) {
        this();
        this.author = command.author();
        this.content = command.content();
        this.post = post;
    }
    public Comment updateInformation(Post post,String author, String comment) {
        this.author = author;
        this.content = comment;
        this.post = post;
        return this;
    }
}
