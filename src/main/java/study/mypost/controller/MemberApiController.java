package study.mypost.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.mypost.domain.Member;
import study.mypost.dto.MemberNameDTO;
import study.mypost.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MemberApiController {

    private final MemberRepository memberRepository;

    @PutMapping("/api/member/{memberId}")
    public Long registerName(@PathVariable Long memberId, @RequestBody MemberNameDTO dto) {

        log.info(dto.getUsername());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        member.registerName(dto.getUsername());

        memberRepository.save(member);

        return member.getId();
    }
}
