package com.spring.hero.config;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				 CorsConfiguration config = new CorsConfiguration();
	                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	                config.setAllowedMethods(Collections.singletonList("*"));
	                config.setAllowCredentials(true);
	                config.setAllowedHeaders(Collections.singletonList("*"));
	                config.setMaxAge(3600L);
	                return config;
			}
        }).and()
		.authorizeRequests()
		.antMatchers("/user").authenticated()
		.antMatchers("/").authenticated()
		.antMatchers("/heros").authenticated()
		.antMatchers("//heros/{id}").authenticated()
		.antMatchers("/save").authenticated()
		.antMatchers("/update").authenticated()
		.antMatchers("/heros/remove/{id}").authenticated()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/adduser").permitAll()
		.and().formLogin().and().httpBasic();
		// this will ignore only h2-console csrf, spring security 4+
        http.csrf().ignoringAntMatchers("/h2-console/**");
        //this will allow frames with same origin which is much more safe
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
 
     }
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("kamal").password("12345").authorities("admin")
		.and()
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}*/
	
	@Bean
	public UserDetailsService userDetailsService(DataSource datasource)
	{
		return new JdbcUserDetailsManager(datasource);
	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder()
	    {
		   return NoOpPasswordEncoder.getInstance();
	    }

}
