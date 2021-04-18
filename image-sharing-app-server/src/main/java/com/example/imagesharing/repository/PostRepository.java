package com.example.imagesharing.repository;

import com.example.imagesharing.model.Post;
import com.example.imagesharing.payload.projections.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p.id FROM Post p")
    Page<Long> fetchIdsOfPosts(Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT p.id AS id, p.createdBy AS createdBy, p.createdAt AS createdAt, p.modifiedAt AS modifiedAt, " +
            "p.postImageLink AS postImageLink, size(p.postComments) AS totalComments FROM Post p WHERE p.id IN ?1")
    List<PostDTO> fetchPostsWithoutPostComments(List<Long> ids);

    @Transactional(readOnly = true)
    @Query("SELECT p.id AS id, p.createdBy AS createdBy, p.createdAt AS createdAt, p.modifiedAt AS modifiedAt, " +
            "p.postImageLink AS postImageLink FROM Post p WHERE p.id = ?1")
    PostDTO fetchPostById(Long id);
}
