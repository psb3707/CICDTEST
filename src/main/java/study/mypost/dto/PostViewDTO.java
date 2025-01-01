package study.mypost.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import study.mypost.domain.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PostViewDTO {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private List<String> tags = new ArrayList<>();

    public PostViewDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();

        List<String> tagNames = post.getPostTags().stream()
                .map(tag -> tag.getTag().getName()).toList();

        this.tags.addAll(tagNames);
    }
}
