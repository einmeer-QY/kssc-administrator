package com.einmeer.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author 芊嵛
 * @date 2024/3/11
 */
public class JwtTest {
    public static void main(String[] args) {
        // 加密
        String key = "einmeer";
        String token1 = JWT.create().withClaim("name", "张三")
                .withClaim("id", 10)
                .sign(Algorithm.HMAC256(key));
        System.out.println(token1);

        // 解密
        String token2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoi5byg5LiJIiwiaWQiOjEwfQ._SarWdZQP59kJ3AnNKpTlm3_LeLj64QqoblT_HJinn4";
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(key))
                .build().verify(token2);
        String name = verify.getClaim("name").asString();
        Integer id = verify.getClaim("id").asInt();
        System.out.println("name1"+name);
        System.out.println("id1"+id);
    }
}
