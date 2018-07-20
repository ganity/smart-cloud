package com.citic.zktd.smart;

import org.junit.Test;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/18 15:07
 */
public class JwtTest {

//    public OAuth2AccessToken readAccessToken(String accessToken) {
//        return tokenStore.readAccessToken(accessToken);
//    }
    @Test
    public void readAccessToken() {
        String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYXJpcCJdLCJ1c2VyX25hbWUiOiJ6aGFuZ3NhbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE1MzE5MjcyNjEsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiMGU1YzQ4OGEtNWJmOC00NDY3LTlhZGEtODM3ZGRkY2RlODFhIiwiY2xpZW50X2lkIjoiY2xpZW50YXBwIn0.ngKJbngqkjEDqoPpVlT6_HC6FM1o7nfSo3xws6HeEBY";
        TokenStore tokenStore = tokenStore();
//        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        DefaultTokenServices services = jwtTokenServices();
//        OAuth2AccessToken oAuth2AccessToken = services.readAccessToken(accessToken);
        OAuth2Authentication auth = services.loadAuthentication(accessToken);
        System.err.println(auth);
    }
    public DefaultTokenServices jwtTokenServices() {
        TokenStore jwtTokenStore = tokenStore();
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(jwtTokenStore);
        return services;
    }
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    protected JwtAccessTokenConverter jwtTokenEnhancer() {
//        keytool -genkeypair -alias jwt -keyalg RSA -dname "CN=jwt, L=Beijing, S=Beijing, C=CN" -keypass CmpJWTSecretKey -keystore jwt.jks -storepass CmpJWTSecretKey
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "CmpJWTSecretKey".toCharArray());
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));

//        Resource resource = new ClassPathResource("public.cert");
//        String publicKey = null;
//        try {
//            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        converter.setVerifierKey(publicKey);
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        converter.setVerifierKey("123");
        try {
            converter.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return converter;
    }



}
