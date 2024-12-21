package study.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.mypost.dto.CommentCreateDTO;
import study.mypost.dto.CommentResponseDTO;
import study.mypost.dto.CommentUpdateDTO;
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

    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId,
                                           @RequestBody CommentUpdateDTO request){
        Long id = commentService.updateComment(commentId, request);

        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        Long id = commentService.deleteComment(commentId);
        return ResponseEntity.ok(id);
    }


}
