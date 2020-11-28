package com.example.imagesharing.model;

import com.example.imagesharing.audit.BaseAudit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Post extends BaseAudit {

    private String postImageTitle;
    private String postImageLink;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> postComments = new ArrayList<>();

    public Post(String postImageTitle, String postImageLink) {
        this.postImageTitle = postImageTitle;
        this.postImageLink = postImageLink;
    }

    public void addPostComment(PostComment postComment) {
        this.postComments.add(postComment);
    }

    public void removePostComment(PostComment postComment) {
         postComment.setPost(null);
         this.postComments.remove(postComment);
    }

    public void removeAllPostComments() {
        Iterator<PostComment> postCommentIterator = this.postComments.iterator();

        while(postCommentIterator.hasNext()) {
            postCommentIterator.next().setPost(null);
            postCommentIterator.remove();
        }
    }

}
