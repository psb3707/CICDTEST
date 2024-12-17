package study.mypost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mypost.domain.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
