package study.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.mypost.domain.Member;
import study.mypost.dto.PostCreateDTO;
import study.mypost.dto.PostResponseDTO;
import study.mypost.repository.MemberRepository;
import study.mypost.service.MemberService;
import study.mypost.service.PostService;
import study.mypost.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostViewController {

    private final PostService postService;

    private final MemberRepository memberRepository;

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<PostResponseDTO> allPosts = postService.getAllPosts();

        model.addAttribute("member", getMemberInfo());

        model.addAttribute("posts", allPosts);

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

        return "newPost";
    }

    private Member getMemberInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();

        return memberRepository.findByEmail(member.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("No Such Member"));

    }
}
