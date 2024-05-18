package org.authenticationservice.www.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authenticationservice.www.dto.RolesDTO;
import org.authenticationservice.www.entity.User;
import org.authenticationservice.www.exception.UserAlreadyExistException;
import org.authenticationservice.www.exception.UserNotFoundException;
import org.authenticationservice.www.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistException("User with this email exists");
        }
        if (existsByLogin(user.getLogin())) {
            throw new UserAlreadyExistException("User with this login exists");
        }

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        log.debug("Finding user by login {}", login);
        return userRepository.findByLogin(login).orElseThrow(
                () -> new UserNotFoundException("User with login " + login + " not found")
        );
    }

    public User findById(Long id) {
        log.debug("Finding user by id {}", id);
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );
    }

    public List<String> getRolesName(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );

        return user.getRolesName();
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
