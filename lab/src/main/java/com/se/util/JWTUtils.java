package com.se.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.List;

public class JWTUtils {
     /**
         * 生命周期为5分钟，单位是毫秒
         */
        private static final long EXPIRE_TIME = 5 * 60 * 1000;
        /**
         * 密钥
         */
        private static final String SECRET = "jwt_secret";

        /**
         * 生成签名
         * @param userId
         * @return
         */
        public static String getToken(String userId,Byte authority) {
            try {
                Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                return JWT.create()
                        // 将 user id 保存到 token 里面
                        .withClaim("id",userId)
                        .withClaim("authority",authority.toString())
//                        .withAudience(authority.toString())
                        // 五分钟后token过期
                        .withExpiresAt(date)
                        // token 的密钥
                        .sign(algorithm);
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * 根据token获取userId
         * @param token
         * @return
         */
        public static String getUserId(String token) {
            try {
                String userId = JWT.decode(token).getClaim("id").asString();
                return userId;
            } catch (JWTDecodeException e) {
                return null;
            }
        }

    /**
     * 根据token获取userId
     * @param token
     * @return
     */
    public static String getAuthority(String token) {
        try {
//            List string = JWT.decode(token).getAudience();
            String authority = JWT.decode(token).getClaim("authority").asString();
            return authority;
        } catch (JWTDecodeException e) {
            return null;
        }
    }


        /**
         * 校验token
         * @param token
         * @return
         */
        public static boolean checkSign(String token) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                JWTVerifier verifier = JWT.require(algorithm)
                        // .withClaim("username", username)
                        .build();
                DecodedJWT jwt = verifier.verify(token);
                return true;
            } catch (JWTVerificationException exception) {
                throw new RuntimeException("token 无效，请重新获取");
            }
        }



}
