package com.basic.Basic_Project_Spring.member.presentation;

import com.basic.Basic_Project_Spring.auth.Auth;
import com.basic.Basic_Project_Spring.auth.jwt.JwtService;
import com.basic.Basic_Project_Spring.member.application.MemberService;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.member.presentation.request.LoginRequest;
import com.basic.Basic_Project_Spring.member.presentation.request.SignupRequest;
import com.basic.Basic_Project_Spring.member.presentation.response.LoginResponse;
import com.basic.Basic_Project_Spring.member.presentation.response.MemberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    @PostMapping()
    public ResponseEntity<Void> signup(
        @Valid @RequestBody SignupRequest signupRequest
    ) {
        Long memberId = memberService.signup(signupRequest.username(), signupRequest.password(), signupRequest.name());
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest loginRequest
    ) {
        Member member = memberService.login(loginRequest.username(), loginRequest.password());
        Long memberId = member.getId();
        String accessToken = jwtService.createToken(memberId);
        return ResponseEntity.ok(new LoginResponse(accessToken));
    }

    @GetMapping("/token")
    public ResponseEntity<MemberResponse> token(
        @Auth Long memberId
    ) {
       Member member = memberService.findById(memberId);
       return ResponseEntity.ok(new MemberResponse(member.getId(), member.getName()));
    }
}
