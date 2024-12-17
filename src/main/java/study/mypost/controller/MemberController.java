package study.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import study.mypost.domain.Member;
import study.mypost.dto.RegisterUserDTO;
import study.mypost.repository.MemberRepository;
import study.mypost.service.UserService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;
    private final MemberRepository memberRepository;

    @PostMapping("/user")
    public String signUp(RegisterUserDTO registerUserDTO) {
        Long id = userService.save(registerUserDTO);
        return "redirect:/member/" + id;
    }

    @GetMapping("/member/{memberId}")
    public String member(@PathVariable Long memberId, Model model) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("No Such Member"));

        model.addAttribute("member", member);

        return "member";
    }
}
