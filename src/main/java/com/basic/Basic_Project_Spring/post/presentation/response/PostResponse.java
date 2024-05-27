package com.basic.Basic_Project_Spring.post.presentation.response;

import com.basic.Basic_Project_Spring.member.presentation.response.MemberResponse;
import com.basic.Basic_Project_Spring.post.domain.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdDate,
        MemberResponse memberResponse
) {
    public static PostResponse of(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedDate(),
                new MemberResponse(post.getWriter().getName())
        );
    }
}
