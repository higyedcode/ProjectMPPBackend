package com.ubb.backend;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class Secrets {
    public static final String SECRET_KEY = Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
}
