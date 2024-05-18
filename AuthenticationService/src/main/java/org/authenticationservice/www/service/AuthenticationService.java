package org.authenticationservice.www.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authenticationservice.www.dto.*;
import org.authenticationservice.www.entity.Roles;
import org.authenticationservice.www.entity.User;
import org.authenticationservice.www.exception.UserNotFoundException;
import org.authenticationservice.www.exception.UserPasswordIncorrectException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleService roleService;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final KeyStoreService keyStoreService;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        log.debug("Sign up user with login: {}", request.getLogin());
        User user = User.builder()
                .login(request.getLogin())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(
                        roleService.getRoleByName(Roles.USER.toString())
                ))
                .build();

        User saved = userService.create(user);
        String token = jwtService.generateToken("user", keyStoreService.getPrivateKey("user"), saved);
        log.info("Token generated: {}", token);
        return new JwtAuthenticationResponse(token);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        log.debug("Sign in user with login: {}", request.getLogin());
        User user = userService.findByLogin(request.getLogin());

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            if (user.getRolesName().contains("ADMIN")) {
                return new JwtAuthenticationResponse(jwtService.generateToken("admin", keyStoreService.getPrivateKey("admin"), user));
            } else if (user.getRolesName().contains("MODERATOR")) {
                return new JwtAuthenticationResponse(jwtService.generateToken("moderator", keyStoreService.getPrivateKey("moderator"), user));
            } else {
                return new JwtAuthenticationResponse(jwtService.generateToken("user", keyStoreService.getPrivateKey("user"), user));
            }
        } else {
            log.info("Request: {}", passwordEncoder.encode(request.getPassword()));
            log.info("User: {}", user.getPassword());
            throw new UserPasswordIncorrectException("Incorrect password");
        }
    }

    public PublicKeyDTO getPublicKey(String alias) {
         PublicKey key = keyStoreService.getPublicKey(alias);
         X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key.getEncoded());
         return new PublicKeyDTO(Base64.getEncoder().encodeToString(x509EncodedKeySpec.getEncoded()));
    }
}
