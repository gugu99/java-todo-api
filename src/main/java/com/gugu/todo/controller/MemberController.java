package com.gugu.todo.controller;

import com.gugu.todo.dto.MemberDto;
import com.gugu.todo.entity.Member;
import com.gugu.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final BCryptPasswordEncoder encoder;
    private final MemberRepository memberRepository;

    @PostMapping("/api/login")
    public String login(@RequestBody MemberDto memberDto) {
        // ID로 회원 엔티티를 조회해옴
        Member member = memberRepository.findByUsername(memberDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("아이디없음"));

        // 그 엔티티의 PW가 DTO의 PW와 같은지 검사
        boolean match = encoder.matches(memberDto.getPassword(), member.getPassword());

        if (!match) {
            throw new BadCredentialsException("비번 틀림");
        }

        return "로그인 성공";


        // 맞으면 Access Token을 만들어줌
        // return "testSuccess";
    }
}
