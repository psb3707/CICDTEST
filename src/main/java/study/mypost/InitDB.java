package study.mypost;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.mypost.domain.Tag;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.initDb();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void initDb() {
            Tag tag = Tag.builder().name("여행").build();
            em.persist(tag);

            Tag tag1 = Tag.builder().name("자기계발").build();
            em.persist(tag1);

            Tag tag2 = Tag.builder().name("여가").build();
            em.persist(tag2);

            Tag tag3 = Tag.builder().name("취업").build();
            em.persist(tag3);

            Tag tag4 = Tag.builder().name("사회적 이슈").build();
            em.persist(tag4);
        }
    }
}
