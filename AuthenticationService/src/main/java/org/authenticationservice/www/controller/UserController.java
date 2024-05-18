package org.authenticationservice.www.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authenticationservice.www.dto.RolesDTO;
import org.authenticationservice.www.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}/roles")
    public RolesDTO getUserRoles(@PathVariable("id") Long id) {
        log.info("Get user roles with id: {}", id);
        return new RolesDTO(userService.getRolesName(id));
    }
}
