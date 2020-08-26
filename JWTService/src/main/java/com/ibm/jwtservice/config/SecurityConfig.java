package com.ibm.jwtservice.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.ibm.jwtservice.constants.RoleConstants;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    TokenService tokenservice;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

  
    /**
     * Configure security based on roles
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/authorize").permitAll()	//allowed to all
                .antMatchers("/productmgmnt/**").hasRole(RoleConstants.ROLE_MANAGER)	// only to MANAGER
                .antMatchers("/storemgmnt/**").hasAnyRole(RoleConstants.ROLE_ADMIN)	// only to ADMIN
                .antMatchers("/user/**").hasAnyRole(RoleConstants.ROLE_USER) // only to USER
                .anyRequest().authenticated()	
            .and()
            .apply(new JWTConfiguration(tokenservice)) 
            ;
    }
}