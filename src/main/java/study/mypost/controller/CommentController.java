package study.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.mypost.dto.CommentCreateDTO;
import study.mypost.dto.CommentResponseDTO;
import study.mypost.repository.CommentRepository;
import study.mypost.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comments")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentCreateDTO request) {
        CommentResponseDTO comment = commentService.createComment(request);
        return ResponseEntity.ok(comment);
    }


}
