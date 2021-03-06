package com.lambdaschool.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(RESOURCE_ID)
                .stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers( "/",
                        "/login",
                        "/h2-console/**",
                    "/swagger-resources/**",
                    "/swagger-resource/**",
                    "/swagger-ui.html",
                    "/v2/api-docs",
                    "/webjars/**",
                    "/createnewuser" )
            .permitAll()
            .antMatchers("roles/**")
            .hasAnyRole("ADMIN")
            .antMatchers("/users/**", "/logout")
            .authenticated()
            .antMatchers("useremails")
            .hasAnyRole("ADMIN", "DATA")
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new OAuth2AccessDeniedHandler());
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.logout().disable();
    }

}
