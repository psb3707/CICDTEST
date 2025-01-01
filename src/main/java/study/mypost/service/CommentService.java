package study.mypost.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.mypost.domain.Comment;
import study.mypost.domain.Member;
import study.mypost.domain.Post;
import study.mypost.dto.CommentCreateDTO;
import study.mypost.dto.CommentResponseDTO;
import study.mypost.dto.CommentUpdateDTO;
import study.mypost.exception.CustomPostException;
import study.mypost.exception.ErrorCode;
import study.mypost.repository.CommentRepository;
import study.mypost.repository.PostRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDTO createComment(CommentCreateDTO commentCreateDTO) {
        Post post = postRepository.findById(commentCreateDTO.getPost_id())
                .orElseThrow(() -> new CustomPostException(ErrorCode.POST_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();



        Comment comment = Comment.createComment(post, member, commentCreateDTO.getContent());
        Comment result = commentRepository.save(comment);

        return new CommentResponseDTO(result);
    }

    @Transactional
    public Long updateComment(Long id, CommentUpdateDTO request) {

        log.info(id);

        Optional<Comment> comment = commentRepository.findById(id);

        log.info(comment);

        Comment comment1 = comment.get();

        comment1.updateContent(request.getBody());

        log.info(comment);

        commentRepository.save(comment1);

        log.info(comment);

        return comment1.getId();
    }

    @Transactional
    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
