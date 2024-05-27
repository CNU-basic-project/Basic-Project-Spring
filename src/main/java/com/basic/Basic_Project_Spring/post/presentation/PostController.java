package com.basic.Basic_Project_Spring.post.presentation;

import com.basic.Basic_Project_Spring.auth.Auth;
import com.basic.Basic_Project_Spring.common.response.Result;
import com.basic.Basic_Project_Spring.post.application.PostService;
import com.basic.Basic_Project_Spring.post.domain.Post;
import com.basic.Basic_Project_Spring.post.presentation.request.PostUpdateRequest;
import com.basic.Basic_Project_Spring.post.presentation.request.PostWriteRequest;
import com.basic.Basic_Project_Spring.post.presentation.response.PostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<Result<List<PostResponse>>> get() {
        List<Post> posts = postService.getPosts();
        List<PostResponse> postResponses = posts.stream()
                .map(PostResponse::of)
                .toList();
        return ResponseEntity.ok().body(new Result<>(postResponses, postResponses.size()));
    }

    @PostMapping
    public ResponseEntity<Void> write(
            @Auth Long memberId,
            @Valid @RequestBody PostWriteRequest postWriteRequest
    ) {
        Long postId = postService.write(memberId, postWriteRequest.title(), postWriteRequest.content());
        return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }

    @PutMapping("/{id}")
    public void update(
            @Auth Long memberId,
            @PathVariable("id") Long postId,
            @Valid @RequestBody PostUpdateRequest postUpdateRequest
    ) {
       postService.update(memberId, postId, postUpdateRequest.title(), postUpdateRequest.content());
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Auth Long memberId,
            @PathVariable("id") Long postId
    ) {
        postService.delete(memberId, postId);
    }
}
