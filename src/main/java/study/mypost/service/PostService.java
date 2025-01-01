package study.mypost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.mypost.domain.*;
import study.mypost.dto.PostCreateDTO;
import study.mypost.dto.PostResponseDTO;
import study.mypost.exception.CustomPostException;
import study.mypost.exception.ErrorCode;
import study.mypost.repository.ExamplePostRepostiory;
import study.mypost.repository.PostRepository;
import study.mypost.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final ExamplePostRepostiory examplePostRepostiory;

    @Transactional
    public PostResponseDTO createPost(PostCreateDTO request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build();

        if (!request.getTags().isEmpty()) {
            for (String tagName : request.getTags()) {
                Tag tag = tagRepository.findByName(tagName)
                                .orElseThrow(() -> new CustomPostException(ErrorCode.TAG_NOT_FOUND));
                PostTag postTag = PostTag.builder()
                        .tag(tag)
                        .post(post)
                        .build();
                post.getPostTags().add(postTag);

            }
        }

        Post result = postRepository.save(post);
        return new PostResponseDTO(result);
    }

    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            return List.of(); // 커스텀 exception 클래스 필요
        }
        List<PostResponseDTO> response = posts.stream()
                .map(PostResponseDTO::new)
                .toList();
        return response;
    }

    public List<PostResponseDTO> getPostsByKeyword(String keyword) {
        List<Post> posts = postRepository.getPostsByTag(keyword);

        if(posts.isEmpty()) {
            throw new CustomPostException(ErrorCode.POST_NOT_FOUND);
        }

        List<PostResponseDTO> list = posts.stream()
                .map(PostResponseDTO::new)
                .toList();
        return list;
    }

    public List<PostResponseDTO> getPostsByTag(Long tagId) {
        List<Post> posts = postRepository.getPostsByTag(tagId);
        if(posts.isEmpty()) {
            throw new CustomPostException(ErrorCode.POST_NOT_FOUND);
        }
        List<PostResponseDTO> list = posts.stream()
                .map(PostResponseDTO::new)
                .toList();
        return list;
    }

    public PostResponseDTO getPost(Long postId) { // @OneToMany 조회 쿼리 최적화 필요!
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomPostException(ErrorCode.POST_NOT_FOUND));

        return new PostResponseDTO(post);
    }

    @Transactional
    public Map<String, String> likesUp(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomPostException(ErrorCode.POST_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();


        PostLike like = PostLike.builder()
                .member(member)
                .post(post)
                .build();

        post.addLike(like);

        postRepository.save(post);

        return Map.of("RESULT", "SUCCESS");
    }

    @Transactional
    public Map<String, String> deletePost(Long postId) {
        postRepository.deleteById(postId);
        return Map.of("RESULT", "SUCCESS");
    }

    @Transactional
    public Map<String, String> updatePost(Long postId, PostCreateDTO postCreateDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomPostException(ErrorCode.POST_NOT_FOUND));

        post.updateTitle(postCreateDTO.getTitle());
        post.updateContent(postCreateDTO.getContent());

        postRepository.save(post);

        return Map.of("RESULT", "SUCCESS");
    }
}
