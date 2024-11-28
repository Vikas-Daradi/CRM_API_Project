package com.crm.controller;

import com.crm.entiity.Post;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostRepository postRepository;
    private final CommentRepository commentRep;

    // Constructor
    public PostController(PostRepository postRepository, CommentRepository commentRep) {
        this.postRepository = postRepository;
        this.commentRep = commentRep;
    }

    @PostMapping
    public String createPost(@RequestBody Post post) {

        postRepository.save(post);
        return "data add successfully.";
    }
}
