package org.authenticationservice.www.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authenticationservice.www.dto.*;
import org.authenticationservice.www.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) {
        log.info("Sign up request with login: {}", request.getLogin());
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        log.info("Sign in request with login: {}", request.getLogin());
        return authenticationService.signIn(request);
    }

    @GetMapping("public-key/{alias}")
    public PublicKeyDTO getPublicKey(@PathVariable String alias) {
        log.info("Request for the public key");
        return authenticationService.getPublicKey(alias);
    }
}
