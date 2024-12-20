package study.mypost.dto;

import lombok.Data;
import study.mypost.domain.Comment;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentResponseDTO {
    private String content;
    private Long post_id;
    private String nickname;

    public CommentResponseDTO(Comment comment) {
        this.post_id = comment.getPost().getId();
        this.content = comment.getContent();
        this.nickname = comment.getMember().getName();
    }
}
