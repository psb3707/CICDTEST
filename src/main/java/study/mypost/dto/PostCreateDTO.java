package study.mypost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.mypost.domain.Post;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDTO { // 데이터 무결성 검증 필요

    private String title;

    private String content;

    @Builder.Default
    private List<String> tags = new ArrayList<>();
}
