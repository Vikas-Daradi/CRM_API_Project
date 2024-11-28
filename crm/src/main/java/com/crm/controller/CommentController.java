package com.crm.controller;

import com.crm.entiity.Comment;
import com.crm.entiity.Post;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentController(PostRepository postRepository, CommentRepository commentRep, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @PostMapping
        public String createComment(@RequestBody Comment comment, @RequestParam long post_ID) {

        Post post = postRepository.findById(post_ID).get();
        comment.setPost(post);
        commentRepository.save(comment);

        return "Comment created successfully";

    }
    @DeleteMapping
    public void deleteComment(){
        postRepository.deleteById(1L);
    }


}
