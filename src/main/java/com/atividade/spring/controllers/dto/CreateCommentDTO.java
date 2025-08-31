package com.atividade.spring.controllers.dto;

public record CreateCommentDTO(String content, int authorId, int idPost) {
}
