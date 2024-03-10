package com.einmeer.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.einmeer.entity.Administrators;

/**
 * @author 芊嵛
 * @date 2024/3/11
 */
public class MyUtil {
    private static final String KEY = "einmeer";

    // 生成token
    public static String getJWT(Administrators administrators) {
        return JWT.create()
                .withClaim("administratorsId", administrators.getAdministratorsId())
                .withClaim("administratorsUsername", administrators.getAdministratorsUsername())
                .sign(Algorithm.HMAC256(KEY));
    }

    // 解token
    public static Administrators doToken(String token) {
        JWTVerifier build = JWT.require(Algorithm.HMAC256(KEY))
                .build();
        DecodedJWT verify = build.verify(token);
        Integer administratorsId = verify.getClaim("administratorsId").asInt();
        String administratorsUsername = verify.getClaim("administratorsUsername").asString();
        Administrators administrators = new Administrators();
        administrators.setAdministratorsId(administratorsId);
        administrators.setAdministratorsUsername(administratorsUsername);
        return administrators;
    }
}
