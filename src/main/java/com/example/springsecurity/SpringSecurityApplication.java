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

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

//    @Value("${authentication.jwt.access-expiration-in-ms}")
//    private Long JWT_ACCESS_EXPIRATION_IN_MS;
//    @Value("${authentication.jwt.refresh-expiration-in-ms}")
//    private Long JWT_REFRESH_EXPIRATION_IN_MS;
//
//    @Autowired
//    private JwtProvider jwtProvider;
//    @Autowired
//    private UserRepository userRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        User user = userRepository.findByUserId(1);
//        String accessToken = jwtProvider.generateToken(user, JWT_ACCESS_EXPIRATION_IN_MS);
//        System.out.println(accessToken);
//        String subject = jwtProvider.getSubject(accessToken);
//        System.out.println(subject);
//    }
    @Override
    public void run(String... args) throws Exception {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(1024);
        KeyPair kp = keyGenerator.genKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        System.out.println(convertToPublicKey(encodedPublicKey));

        System.out.println();

        System.out.println(convertToPrivateKey(encodedPrivateKey));

    }

    private static String convertToPrivateKey(String key) {
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PRIVATE KEY-----\n");
        result.append(key);
        result.append("\n-----END PRIVATE KEY-----");
        return result.toString();
    }

    private static String convertToPublicKey(String key) {
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PUBLIC KEY-----\n");
        result.append(key);
        result.append("\n-----END PUBLIC KEY-----");
        return result.toString();
    }
}
