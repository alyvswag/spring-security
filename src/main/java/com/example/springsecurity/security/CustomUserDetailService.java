package com.example.springsecurity.security;

import com.example.springsecurity.models.User;
import com.example.springsecurity.models.UserRole;
import com.example.springsecurity.repository.UserRoleRepository;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);//byPin
        List<UserRole> userRoles = userRoleRepository.findRolesByUserId(user.getId());
        List<String> authorities = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            authorities.add(String.valueOf(userRole.getRole().getRoleName()));
        }
        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), authorities);
    }
}
