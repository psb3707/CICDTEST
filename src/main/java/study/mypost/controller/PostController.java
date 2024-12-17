package study.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.mypost.domain.Post;
import study.mypost.dto.PostCreateDTO;
import study.mypost.dto.PostResponseDTO;
import study.mypost.service.PostService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostCreateDTO request) {
        PostResponseDTO result = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(@RequestParam(required = false) Long tagId) {
        if (tagId != null) {
            List<PostResponseDTO> postsByTag = postService.getPostsByTag(tagId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(postsByTag);
        }
        List<PostResponseDTO> allPosts = postService.getAllPosts();
        return ResponseEntity.ok(allPosts);
    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId,
                                                      @RequestBody PostCreateDTO request) {

        return ResponseEntity.ok(postService.updatePost(postId, request));
    }

    @PutMapping("/api/posts/likes/{postId}")
    public ResponseEntity<?> likesUp(@PathVariable Long postId) {
        Map<String, String> result = postService.likesUp(postId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long postId) {
        PostResponseDTO result = postService.getPost(postId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        Map<String, String> map = postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
