package com.citic.zktd.smart.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.core.io.Resource;
//import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * Created by Arip Hidayat on 12/03/2016.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    public static final String RESOURCE_ID = "arip";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
//    @Qualifier("domainUserDetailsService")
    private UserDetailsService userDetailsService;

//    @Autowired
//    private RedisConnectionFactory connectionFactory;

    //    @Bean
//    public RedisTokenStore tokenStore() {
//        return new RedisTokenStore(connectionFactory);
//    }
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
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
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
            /***
             * 重写增强token方法,用于自定义一些token返回的信息
             */
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                String userName = authentication.getUserAuthentication().getName();
                User user = (User) authentication.getUserAuthentication().getPrincipal();// 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
                /** 自定义一些token属性 ***/
                final Map<String, Object> additionalInformation = new HashMap<>();
//                additionalInformation.put("userName", userName);
//                find resource form db by userName;
                List<String> resources = new ArrayList<>();
                resources.add("/room/name/**");
                resources.add("/room2/**");
                additionalInformation.put("resources", resources);
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                return enhancedToken;
            }

        };
        converter.setSigningKey("123");
        return converter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore())
                .tokenEnhancer(jwtTokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.checkTokenAccess("hasRole('CLIENT')");
        oauthServer
//                .tokenKeyAccess("permitAll()")
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client.inMemory()
                .withClient("clientapp")
                .secret("{noop}123456")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .resourceIds(RESOURCE_ID)
                .and()
                .withClient("clientcred")
                .secret("{noop}123456")
                .authorizedGrantTypes("client_credentials")
                .scopes("trust")
//                .authorities()
                .resourceIds(RESOURCE_ID)
                .and()
                .withClient("clientauthcode")
                .secret("{noop}123456")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("read", "write")
                .resourceIds(RESOURCE_ID)
                .and()
                .withClient("jsclient")
                .secret("{noop}123456")
                .authorizedGrantTypes("implicit")
                .scopes("read", "write")
                .resourceIds(RESOURCE_ID);
//                .authorities("CLIENT")
//                .redirectUris("http://localhost:8081/resource-server/api/state/verify")
//                .accessTokenValiditySeconds(3600)
//                .autoApprove(true);
    }
}