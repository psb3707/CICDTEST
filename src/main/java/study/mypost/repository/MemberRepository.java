package study.mypost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mypost.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
