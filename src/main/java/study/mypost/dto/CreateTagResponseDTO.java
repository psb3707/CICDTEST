package study.mypost.dto;

import lombok.Data;
import study.mypost.domain.Tag;

@Data
public class CreateTagResponseDTO {
    private String tagName;

    public CreateTagResponseDTO(Tag tag) {
        this.tagName = tag.getName();
    }
}
