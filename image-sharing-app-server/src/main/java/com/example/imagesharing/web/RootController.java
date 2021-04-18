package com.example.imagesharing.web;

import com.example.imagesharing.payload.projections.RootDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/root")
public class RootController {

    @GetMapping
    public ResponseEntity<RootDTO> root() {
        RootDTO rootDTO = new RootDTO(
                linkTo(methodOn(PostController.class).createPost(null, null)).toString(),
                linkTo(methodOn(PostController.class).pagedPosts(0, 0)).toString());

        return ResponseEntity.ok(rootDTO);
    }
}
