package com.loco.demo.services.authenService;

import com.loco.demo.DTO.JSON.TokenDTO;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Autowired
    public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public TokenDTO generateJwt(@NotNull Authentication authentication){
        Date currentDate = new Date();
        Calendar expirationCalendar = Calendar.getInstance();
        expirationCalendar.setTime(currentDate);
        expirationCalendar.add(Calendar.HOUR_OF_DAY, 48);
        Date expirationDate = expirationCalendar.getTime();
        Instant instant = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(instant)
                .expiresAt(expirationDate.toInstant())
                .subject(authentication.getName())
                .claim("roles",scope)
                .build();
        return new TokenDTO(jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue(),expirationDate.toInstant());
    }
}
