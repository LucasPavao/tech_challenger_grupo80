package br.com.tech.challenger.api_restaurante.config.security;

import br.com.tech.challenger.api_restaurante.dto.v1.AuthenticationTokenDetailsDTO;
import br.com.tech.challenger.api_restaurante.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private Logger logger = LoggerFactory.getLogger(TokenService.class);

    private String issuer = "Restaurante API";

    @Value("${security.secret}")
    private String secret;

    @Value("${security.expiration.time}")
    private Integer expiration;

    public AuthenticationTokenDetailsDTO generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret.getBytes());
            Instant expiresAt = this.getExpirationDate();
            String token = JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(user.getId().toString())
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);

            return new AuthenticationTokenDetailsDTO(token, expiresAt);

        } catch (JWTCreationException exception) {
            logger.error(exception.getMessage());
            throw new RuntimeException("Erro ao generar JWT");
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(this.expiration).toInstant(ZoneOffset.of("-03:00"));
    }
}
