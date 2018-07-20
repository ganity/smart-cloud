package com.citic.zktd.smart.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserApprovalRequiredException;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.RequestEnhancer;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@EnableOAuth2Client
public class Controller1 {


// @Autowired
//
// private OAuth2ClientContext context;


// @Bean
//
// @Scope("session")
//
// public OAuth2ClientContext createContext(){
//
//  OAuth2ClientContext context = new DefaultOAuth2ClientContext();
//
//  return context;
//
// }

    @Autowired
    private OAuth2ClientContext context;

    @ResponseBody

    //TODO http://localhost:8300/weixin/authorize
    @RequestMapping("/weixin/authorize")

    public Object getUserInfo(HttpServletRequest request) {


        class WeixinAuthorizationCodeAccessTokenProvider extends AuthorizationCodeAccessTokenProvider {


            public WeixinAuthorizationCodeAccessTokenProvider(List<HttpMessageConverter<?>> messageConverters) {

                this.setMessageConverters(messageConverters);

                this.setTokenRequestEnhancer(new RequestEnhancer() {

                    @Override

                    public void enhance(AccessTokenRequest request, OAuth2ProtectedResourceDetails resource,

                                        MultiValueMap<String, String> form, HttpHeaders headers) {

                        String clientId = form.getFirst("client_id");

                        String clientSecret = form.getFirst("client_secret");

                        form.set("appid", clientId);

                        form.set("secret", clientSecret);

                        form.remove("client_id");

                        form.remove("client_secret");

                    }

                });

            }

            @Override

            public OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails details,

                                                       AccessTokenRequest request) throws UserRedirectRequiredException, UserApprovalRequiredException,

                    AccessDeniedException, OAuth2AccessDeniedException {

                try {

                    return super.obtainAccessToken(details, request);

                } catch (UserRedirectRequiredException e) {

                    Map<String, String> params = e.getRequestParams();

                    String clientId = params.get("client_id");

                    params.put("appid", clientId);

                    params.remove("client_id");

                    throw e;

                }

            }

        }
        ;

        class WeixinOAuth2RestTemplate extends OAuth2RestTemplate {


            public WeixinOAuth2RestTemplate(AuthorizationCodeResourceDetails resource, OAuth2ClientContext context) {

                super(resource, context);

                List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

                messageConverters.add(new MappingJackson2HttpMessageConverter() {

                    @Override

                    protected boolean canRead(MediaType mediaType) {

                        return true;

                    }

                });

                this.setMessageConverters(messageConverters);

                this.setAccessTokenProvider(new WeixinAuthorizationCodeAccessTokenProvider(messageConverters));

            }


            @Override

            protected URI appendQueryParameter(URI uri, OAuth2AccessToken accessToken) {

                uri = super.appendQueryParameter(uri, accessToken);

                String url = uri.toString();

                if (url.contains("$openid$")) {

                    String openid = (String) accessToken.getAdditionalInformation().get("openid");

                    try {

                        uri = new URI(url.replace("$openid$", openid));

                    } catch (URISyntaxException e) {

                        e.printStackTrace();

                    }

                }

                return uri;

            }


        }

        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();

        resource.setAuthenticationScheme(AuthenticationScheme.form);

        resource.setClientAuthenticationScheme(AuthenticationScheme.form);

        resource.setClientId("xxxxxxxxxxx");

        resource.setClientSecret("xxxxxxxxxxx");

        resource.setUserAuthorizationUri("https://open.weixin.qq.com/connect/oauth2/authorize");

        resource.setAccessTokenUri("https://api.weixin.qq.com/sns/oauth2/access_token");

        resource.setScope(Arrays.asList("snsapi_userinfo"));


//        OAuth2ClientContext context = new DefaultOAuth2ClientContext();

        context.getAccessTokenRequest().setCurrentUri(request.getRequestURL().toString());

//  resource.setPreEstablishedRedirectUri("http://www.baidu.com");

//  resource.setUseCurrentUri(false);

        OAuth2RestTemplate template = new WeixinOAuth2RestTemplate(resource, context);

        OAuth2AccessToken token = template.getAccessToken();
        System.err.println(token.getValue());

        String url = "https://api.weixin.qq.com/sns/userinfo?lang=zh_CN&openid=$openid$";

        ResponseEntity<Object> result = template.getForEntity(url, Object.class);

        return result.getBody();

    }

}