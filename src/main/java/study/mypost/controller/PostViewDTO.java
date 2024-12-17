package study.mypost.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.mypost.domain.Post;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostViewDTO {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    public PostViewDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
    }
}
