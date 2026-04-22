package br.com.tech.challenger.api_restaurante.config.security;

import br.com.tech.challenger.api_restaurante.dto.v1.AuthenticationTokenDetailsDTO;
import br.com.tech.challenger.api_restaurante.dto.v1.UserResponseDTO;
import br.com.tech.challenger.api_restaurante.exception.AccessTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    public AuthenticationTokenDetailsDTO generateToken(UserResponseDTO userDto) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret.getBytes());
            Instant expiresAt = this.getExpirationDate();
            String token = JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(userDto.login())
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);

            return new AuthenticationTokenDetailsDTO(token, expiresAt);

        } catch (JWTCreationException exception) {
            logger.error("Erro ao generar JWT: " + exception.getMessage());
            throw new AccessTokenException("Erro ao generar JWT");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret.getBytes());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.issuer)
                    .build();

            return verifier.verify(token).getSubject();
        } catch (JWTCreationException exception) {
            logger.error("Erro ao validar JWT: " + exception.getMessage());
            throw new AccessTokenException("Erro ao validar JWT");
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(this.expiration).toInstant(ZoneOffset.of("-03:00"));
    }
}
