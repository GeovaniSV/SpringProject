package com.atividade.spring.services;

import com.atividade.spring.controllers.CommentController;
import com.atividade.spring.controllers.dto.CreateCommentDTO;
import com.atividade.spring.controllers.dto.UpdateCommentDTO;
import com.atividade.spring.controllers.dto.UpdatePostDTO;
import com.atividade.spring.domain.Comment;
import com.atividade.spring.domain.Post;
import com.atividade.spring.repository.CommentRepository;
import com.atividade.spring.repository.PostRepository;
import com.atividade.spring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(CreateCommentDTO createCommentDTO){
        var user = userRepository.findById(createCommentDTO.authorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var post = postRepository.findById(createCommentDTO.idPost()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var entity = new Comment(createCommentDTO.content(), createCommentDTO.authorId(), createCommentDTO.idPost(), user, post);
        var comment = commentRepository.save(entity);

        return comment;
    }

    public List<Comment> listComment() {
        return commentRepository.findAll();
    }

    public void updateComment(String id, UpdateCommentDTO updateCommentDTO) {
        var commentId = Integer.parseInt(id);

        var commentEntity = commentRepository.findById(commentId);

        if(commentEntity.isPresent()) {
            var comment = commentEntity.get();

            if (updateCommentDTO.content() != null) {
                comment.setContent(updateCommentDTO.content());
            }

            commentRepository.save(comment);
        }
    }

    public void deleteComment(String id) {
        commentRepository.deleteById(Integer.parseInt(id));
    }

}
