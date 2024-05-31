package com.basic.Basic_Project_Spring.member.domain;

import com.basic.Basic_Project_Spring.common.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberPermission {

    public void hasShipPermission(Long permission) {
        if (permission == 0) {
            throw new ForbiddenException("권한이 없습니다.");
        }
    }
}
