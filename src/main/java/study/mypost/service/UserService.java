package study.mypost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import study.mypost.domain.Member;
import study.mypost.dto.RegisterUserDTO;
import study.mypost.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(RegisterUserDTO dto) {
        return memberRepository.save(Member.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
