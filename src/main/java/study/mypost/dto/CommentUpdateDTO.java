package study.mypost.dto;

import lombok.Data;

@Data
public class CommentUpdateDTO {
    private String body;
    private Long post_id;
    private Long id;
}
