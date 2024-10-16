package com.example.DoAnPhanMem.Service;

import ch.qos.logback.classic.Logger;
import com.example.DoAnPhanMem.Repository.UserRepository;
import com.example.DoAnPhanMem.Request.AuthenticationRequest;
import com.example.DoAnPhanMem.Request.IntrospectRequest;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthenticatinService {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.signerKey}")
    protected String signerKey;
    private Logger log;

    public String SignIn(AuthenticationRequest authenticationRequest){
        var user = userRepository.findByUserName(authenticationRequest.getUserName());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        passwordEncoder.matches(authenticationRequest.getPassword(), user.get().getPassword());
        var token = genarateToken(authenticationRequest.getUserName());
        return token;
    }

    private String genarateToken(String userName) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userName)
                .issuer("DoAnPhanMem.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("customClaim","Custom")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader,payload);
        try {
            jwsObject.sign(new MACSigner(signerKey
            ));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token",e);
            throw new RuntimeException(e);
        }
    }

    public boolean Introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();
        JWSVerifier jwsVerifier = new MACVerifier(signerKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verify = signedJWT.verify(jwsVerifier);
        return verify && expirationTime.after(new Date());
    }
}
