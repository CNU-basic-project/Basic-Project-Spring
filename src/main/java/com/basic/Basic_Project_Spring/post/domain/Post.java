package com.basic.Basic_Project_Spring.post.domain;

import com.basic.Basic_Project_Spring.common.exception.ForbiddenException;
import com.basic.Basic_Project_Spring.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // INT id AUTO_INCREMENT

    private String title;
    private String content;
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member writer;

    public Post(String title, String content, Member writer) {
        this.title = title; // VARCHAR title
        this.content = content; // VARCHAR content
        this.writer = writer; // INT writer_id
        this.createdDate = LocalDateTime.now(); // created_date VARCHAR
    }

    public void validateAuthority(Member member) {
        if (!this.writer.getId().equals(member.getId())) {
            throw new ForbiddenException("포스트에 대한 권한이 없습니다.");
        }
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
