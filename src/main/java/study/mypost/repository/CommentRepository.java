package study.mypost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mypost.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
