package rs.ognjen_uros.sala_spring_zakazivanje_treninga.secutiry.service;

import io.jsonwebtoken.Claims;

public interface TokenService {

    String generate(Claims claims);

    Claims parseToken(String jwt);

    String extractAllClaims(String jwt);
}
