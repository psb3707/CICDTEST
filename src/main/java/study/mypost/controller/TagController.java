package study.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.mypost.domain.Tag;
import study.mypost.dto.CreateTagRequestDTO;
import study.mypost.dto.CreateTagResponseDTO;
import study.mypost.repository.TagRepository;
import study.mypost.service.TagService;

@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagRepository tagRepository;
    private final TagService tagService;

    @PostMapping("/api/tags")
    public ResponseEntity<CreateTagResponseDTO> createTag(@RequestBody CreateTagRequestDTO tagDTO) { // DTO로 감싸야 함
        CreateTagResponseDTO result = tagService.createTag(tagDTO);
        return ResponseEntity.ok(result);
    }
}
