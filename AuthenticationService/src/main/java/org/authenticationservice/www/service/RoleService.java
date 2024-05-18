package org.authenticationservice.www.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authenticationservice.www.entity.Role;
import org.authenticationservice.www.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
