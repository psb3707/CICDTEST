package study.mypost.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import study.mypost.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponseDTO {
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private List<String> tags = new ArrayList<>();

    private List<CommentResponseDTO> comments = new ArrayList<>();

    private int likeCount;

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();

        for (PostTag postTag : post.getPostTags()) {
            String name = postTag.getTag().getName();
            this.tags.add(name);
        }

        for(Comment comment : post.getComments()) {
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment);
            this.comments.add(commentResponseDTO);
        }

        List<PostLike> likes = post.getLikes();

        this.likeCount = likes.size();
    }
}
