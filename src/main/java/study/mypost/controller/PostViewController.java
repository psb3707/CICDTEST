package study.mypost.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import study.mypost.domain.Member;
import study.mypost.domain.Tag;
import study.mypost.dto.PostResponseDTO;
import study.mypost.dto.PostViewDTO;
import study.mypost.repository.MemberRepository;
import study.mypost.repository.TagRepository;
import study.mypost.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class PostViewController {

    private final PostService postService;

    private final MemberRepository memberRepository;

    private final TagRepository tagRepository;

    @GetMapping("/posts")
    public String getPosts(@RequestParam(value = "search", defaultValue = "default") String searchParam,
                           Model model) {

        log.info(searchParam);

        List<PostResponseDTO> allPosts = new ArrayList<>();

        if(searchParam.equals("default")) {

            log.info("default search.....");

            allPosts = postService.getAllPosts();

        }
        else{
            log.info("searching........");
            allPosts = postService.getPostsByKeyword(searchParam);
        }

        log.info(allPosts);

        model.addAttribute("posts", allPosts);

        model.addAttribute("member", getMemberInfo());
        return "postList";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        PostResponseDTO post = postService.getPost(id);

        model.addAttribute("member", getMemberInfo());

        model.addAttribute("post", post);

        return "post";
    }

    @GetMapping("/new-post")
    public String newPost(@RequestParam(required = false) Long id, Model model) {
        if(id != null) {
            PostResponseDTO post = postService.getPost(id);
            model.addAttribute("member", getMemberInfo());
            model.addAttribute("post", post);
        }
        else{
            model.addAttribute("member", getMemberInfo());
            model.addAttribute("post", new PostViewDTO());
        }

        List<Tag> tags = tagRepository.findAll();

        model.addAttribute("tags", tags);

        return "newPost";
    }

    private Member getMemberInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();

        return memberRepository.findByEmail(member.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("No Such Member"));

    }
}
