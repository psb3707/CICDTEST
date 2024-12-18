package study.mypost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.mypost.domain.Comment;
import study.mypost.domain.Post;
import study.mypost.dto.CommentCreateDTO;
import study.mypost.dto.CommentResponseDTO;
import study.mypost.exception.CustomPostException;
import study.mypost.exception.ErrorCode;
import study.mypost.repository.CommentRepository;
import study.mypost.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDTO createComment(CommentCreateDTO commentCreateDTO) {
        Post post = postRepository.findById(commentCreateDTO.getPost_id())
                .orElseThrow(() -> new CustomPostException(ErrorCode.POST_NOT_FOUND));

        Comment comment = Comment.createComment(post, commentCreateDTO.getContent());
        Comment result = commentRepository.save(comment);

        return new CommentResponseDTO(result);
    }
}
