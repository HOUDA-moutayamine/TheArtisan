package com.project.artisan.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.artisan.service.UserService;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private UserService userService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	
	@Autowired
	void registerProvider(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
		http.authorizeHttpRequests().requestMatchers(
				 "/registration**",
	                "/js/**",
	                "/css/**",
	                "/images/**",
	                "/icons/**",
	                "/",
	                "/decouvrir"
	                ).permitAll()
		
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll()
		.and()
		.csrf().disable().cors();
		return http.build();
	}
	

   
}
