package com.atividade.spring.controllers;

import com.atividade.spring.controllers.dto.CreateCommentDTO;
import com.atividade.spring.controllers.dto.UpdateCommentDTO;
import com.atividade.spring.controllers.dto.UpdatePostDTO;
import com.atividade.spring.domain.Comment;
import com.atividade.spring.domain.Post;
import com.atividade.spring.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentDTO createCommentDTO) {
        var comment = commentService.createComment(createCommentDTO);
        return ResponseEntity.created(URI.create("/comments" + comment.getId())).body(comment);

    }

    @GetMapping
    public ResponseEntity<List<Comment>> listComment() {
        var comments = commentService.listComment();
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable("id") String id,
                                           @RequestBody UpdateCommentDTO updateCommentDTO) {
        commentService.updateComment(id, updateCommentDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
