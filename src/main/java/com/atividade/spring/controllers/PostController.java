package com.atividade.spring.controllers;


import com.atividade.spring.controllers.dto.CreatePostDto;
import com.atividade.spring.controllers.dto.UpdatePostDTO;
import com.atividade.spring.controllers.dto.UpdateUserDTO;
import com.atividade.spring.domain.Post;
import com.atividade.spring.services.PostServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostServices postService;

    public PostController(PostServices postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDto createPostDto) {
        var post = postService.createPost(createPostDto);
        return ResponseEntity.created(URI.create("/posts" + post.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable("id") String id,
                                           @RequestBody UpdatePostDTO updatePostDTO) {
        postService.updatePost(id, updatePostDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getById(@PathVariable("id") String id) {
        var post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> listPosts() {
        var posts = postService.listPost();
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
