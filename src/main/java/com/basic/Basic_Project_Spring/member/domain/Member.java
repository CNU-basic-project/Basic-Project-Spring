package com.basic.Basic_Project_Spring.member.domain;

import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String name;

    public Member(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void validator(MemberValidator memberValidator) {
        memberValidator.validateDuplicatedUsername(username);
    }

    public void login(String password) {
        if (!this.password.equals(password)) {
            throw new UnAuthorizeException("비밀번호가 일치하지 않습니다.");
        }
    }
}
