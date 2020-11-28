package com.example.imagesharing.model;

import com.example.imagesharing.audit.BaseAudit;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(exclude = {"post"}, callSuper = true)
public class PostComment extends BaseAudit {

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public PostComment(String comment) {
        this.comment = comment;
    }

}
