package study.mypost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.mypost.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p,pt from Post p inner join p.postTags pt where pt.tag.id =:tagId")
    List<Post> getPostsByTag(@Param("tagId") Long tagId);

    @Query("select p from Post p join fetch p.member m")
    List<Post> getPosts();

    @Query("select p from Post p inner join p.postTags pt where pt.tag.name = :keyword")
    List<Post> getPostsByTag(@Param("keyword") String keyword);
}
