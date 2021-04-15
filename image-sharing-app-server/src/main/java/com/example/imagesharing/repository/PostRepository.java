package com.example.imagesharing.repository;

import com.example.imagesharing.model.Post;
import com.example.imagesharing.payload.projections.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p.id FROM Post p")
    Page<Long> fetchIdsOfPosts(Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.postComments WHERE p.id IN ?1")
    @QueryHints(value = @QueryHint(name = org.hibernate.annotations.QueryHints.PASS_DISTINCT_THROUGH, value = "false"))
    List<PostDTO> fetchPostsWithoutPostComments(List<Long> ids);
}
