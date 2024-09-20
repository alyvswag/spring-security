package com.example.springsecurity;

import com.example.springsecurity.models.User;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Value("${authentication.jwt.access-expiration-in-ms}")
    private Long JWT_ACCESS_EXPIRATION_IN_MS;
    @Value("${authentication.jwt.refresh-expiration-in-ms}")
    private Long JWT_REFRESH_EXPIRATION_IN_MS;

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findByUserId(1);
        String accessToken = jwtProvider.generateToken(user, JWT_ACCESS_EXPIRATION_IN_MS);
        System.out.println(accessToken);
        String subject = jwtProvider.getSubject(accessToken);
        System.out.println(subject);
    }
}
