package project.community.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import project.community.domain.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public String createToken(User user) {

        return Jwts.builder()
                .signWith(secretKey)
                .subject(user.getId().toString())
                .claim("username", user.getName())
                .claim("userRole", user.getUserRole())
                .issuedAt(new Date())
                .id(UUID.randomUUID().toString())
                .compact();
    }

    public Long getUserByToken(String token) {
        Claims payload = Jwts.parser().verifyWith(secretKey)
                .build().parseSignedClaims(token).getPayload();
        //예외처리
        return Long.parseLong(payload.getSubject());
    }
}
