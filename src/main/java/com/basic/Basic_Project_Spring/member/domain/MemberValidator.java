package com.basic.Basic_Project_Spring.member.domain;

import com.basic.Basic_Project_Spring.common.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validateDuplicatedUsername(String username) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new ConflictException("이미 존재하는 아이디입니다.");
        }
    }
}
