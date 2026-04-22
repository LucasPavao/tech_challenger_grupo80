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

    @Value("${security.expiration.minutes}")
    private Integer expirationMinutes;

    public AuthenticationTokenDetailsDTO generateToken(UserResponseDTO userDto) {
        return new AuthenticationTokenDetailsDTO(
                generateAccessToken(userDto),
                generateRefreshToken(userDto),
                getExpirationDate(expirationMinutes)
        );
    }

    private String generateAccessToken(UserResponseDTO userDto) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret.getBytes());
            return JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(userDto.login())
                    .withExpiresAt(this.getExpirationDate(expirationMinutes))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            logger.error("Erro ao generar AccessToken: " + exception.getMessage());
            throw new AccessTokenException("Erro ao generar AccessToken");
        }
    }

    private String generateRefreshToken(UserResponseDTO userDto) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret.getBytes());
            return JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(userDto.id().toString())
                    .withExpiresAt(this.getExpirationDate(expirationMinutes * 3))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            logger.error("Erro ao generar RefreshToken: " + exception.getMessage());
            throw new AccessTokenException("Erro ao generar RefreshToken");
        }
    }

    private Instant getExpirationDate(Integer minutesToExpire) {
        return LocalDateTime.now().plusHours(minutesToExpire).toInstant(ZoneOffset.of("-03:00"));
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
}
