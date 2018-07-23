# smart cloud

This project build in spring cloud Finchley.RELEASE, and include some example.

`eureka-server` discover server with eureka

`security-demo-rest-only` oauth2.0 server, with `sso server` and `password` authorized grant type

    -`security-demo-rest-only-client` simple resource service
    -`sso-demo-client1` the sso login example client 1
    -`sso-demo-client2` the sso login example client 2

`api-gateway` the gateway base on spring-cloud-gateway, and filter to verify access token and access granted authority.

`zeroiot-common` something common

`zeroiot-entry` the entries

`user-service` the user service

`user-api` the feign client api for user-service that the client can access.

`room-service` room service

`room-api` the feign client api

`hystrix-dashboard`

`turbine-dashboard`

`example-webflux-provider` an example provider in servlet

`example-webflux-customer` a feign customer webflux


