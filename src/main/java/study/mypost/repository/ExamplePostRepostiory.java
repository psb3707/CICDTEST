package study.mypost.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.mypost.domain.Post;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExamplePostRepostiory {
    private final EntityManager em;

    public List<Post> getPostsByDefault() {
        List result = em.createQuery("select p from Post p ").getResultList();
        return result;
    }

    public List<Post> getPostsByTag(Long tagId) {
        List<Post> resultList = em.createQuery("select p from Post p" +
                        " join fetch p.postTags pt" +
                        " where pt.tag.id = :tagId", Post.class).setParameter("tagId", tagId)
                .getResultList();
        return resultList;
    }
}
