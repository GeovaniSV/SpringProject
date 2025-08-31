package com.atividade.spring.services;

import com.atividade.spring.controllers.dto.CreatePostDto;
import com.atividade.spring.controllers.dto.UpdatePostDTO;
import com.atividade.spring.domain.Post;
import com.atividade.spring.repository.PostRepository;
import com.atividade.spring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostServices {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServices(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(CreatePostDto createPostDto) {

        var user = userRepository.findById(createPostDto.userId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var entity = new Post(createPostDto.content(), user, createPostDto.userId());
        var post = postRepository.save(entity);
        return post;
    }

    public void updatePost(String id, UpdatePostDTO updatePostDTO) {
        var postId = Integer.parseInt(id);

        var postEntity = postRepository.findById(postId);

        if(postEntity.isPresent()) {
            var post = postEntity.get();

            if (updatePostDTO.content() != null) {
                post.setContent(updatePostDTO.content());
            }

            postRepository.save(post);
        }
    }

    public Optional<Post> getPostById(String id) {
        var idFormated = Integer.parseInt((id));
        return postRepository.findById(idFormated);
    }

    public List<Post> listPost() {
        return postRepository.findAll();
    }

    public void deletePost(String id) {
        postRepository.deleteById(Integer.parseInt(id));
    }
}
