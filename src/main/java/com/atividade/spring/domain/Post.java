package com.atividade.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    private int authorId;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<Comment> comments;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(String content, User user, int i) {
        this.content = content;
        this.authorId = i;
        this.user = user;
    }
}
