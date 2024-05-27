package com.basic.Basic_Project_Spring.post.application;

import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.member.domain.MemberRepository;
import com.basic.Basic_Project_Spring.post.domain.Post;
import com.basic.Basic_Project_Spring.post.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Long write(
            Long memberId,
            String title,
            String content
    ) {
        Member writer = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Post post = new Post(title, content, writer);
        return postRepository.save(post).getId();
    }

    public void update(
            Long memberId,
            Long postId,
            String title,
            String content
    ) {
        Member writer = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new UnAuthorizeException("포스트 정보가 존재하지 않습니다."));
        post.validateAuthority(writer);
        post.update(title, content);
    }

    public void delete(
            Long memberId,
            Long postId
    ) {
        Member writer = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new UnAuthorizeException("포스트 정보가 존재하지 않습니다."));
        post.validateAuthority(writer);
        postRepository.delete(post);
    }
}
