package com.marco.apps.security.config;


import com.marco.apps.services.UserService;
import lombok.AllArgsConstructor;

import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/api/v*/registration/**")
               .permitAll()
               .anyRequest()
               .authenticated().and()
               .formLogin().successHandler(new AuthenticationSuccessHandler() {

           @Override
           public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                               Authentication authentication) throws IOException, ServletException {
               response.setContentType("application/json;charset=UTF-8");
               response.setStatus(HttpServletResponse.SC_ACCEPTED);
               response.getWriter().write(new JSONObject()
                       .put("status", "ok")
                       .put("message", "Login correcto")
                       .toString()); // run custom logics upon successful login
           }
       });

        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) ->
                {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write(new JSONObject()
                            .put("status", "error")
                            .put("message", "Login incorrecto")
                            .toString());
                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
