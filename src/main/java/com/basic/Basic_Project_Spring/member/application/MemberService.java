package com.basic.Basic_Project_Spring.member.application;

import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.member.domain.MemberRepository;
import com.basic.Basic_Project_Spring.member.domain.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    public Long signup(
        String username,
        String password,
        String name
    ) {
        Member member = new Member(username, password, name);
        member.validator(memberValidator);
        Member saved = memberRepository.save(member);
        return saved.getId();
    }

    public Member login(
        String username,
        String password
    ) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UnAuthorizeException("아이디를 찾을 수 없습니다."));
        member.login(password);
        return member;
    }

    public Member findById(
        Long memberId
    ) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("아이디를 찾을 수 없습니다."));
    }
}
