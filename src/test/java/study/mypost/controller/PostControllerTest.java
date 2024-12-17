package study.mypost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import study.mypost.domain.Member;
import study.mypost.domain.Post;
import study.mypost.dto.PostCreateDTO;
import study.mypost.dto.PostResponseDTO;
import study.mypost.repository.ExamplePostRepostiory;
import study.mypost.repository.MemberRepository;
import study.mypost.service.PostService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class PostControllerTest {

    @Autowired
    private PostService postService;

    @Autowired
    private ExamplePostRepostiory examplePostRepostiory;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext web;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(web).build();
    }



    @Test
    @Transactional
    public void 디폴트_배치_사이즈() {
        for( int  i = 0; i < 10; i++){
            PostCreateDTO postCreateDTO = PostCreateDTO.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .build();
            postService.createPost(postCreateDTO);
        }

        postService.getAllPosts();

        log.info("---------------------");

        examplePostRepostiory.getPostsByDefault();
    }


}