package com.basic.Basic_Project_Spring.member.presentation.response;

import com.basic.Basic_Project_Spring.member.domain.Member;

public record MemberResponse(
        Long id,
        String name,
        Long permission
) {

    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getPermission()
        );
    }
}
