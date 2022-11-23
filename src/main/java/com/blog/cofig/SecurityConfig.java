package com.blog.cofig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blog.security.CustomUserDetailService;
import com.blog.security.JWTAuthenticationentryPoint;
import com.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
   @Autowired
	private CustomUserDetailService customuserDetailService;
   @Autowired
   private JWTAuthenticationentryPoint entrypoint;
   @Autowired
   private JwtAuthenticationFilter filter;
   
   public static final String [] public_URL= {
		   "/api/v1/auth/**","/v3/api-docs","/v3/api-docs","/swagger-resources/**","/swagger-ui/**","/webjars/**"
   };
   
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		csrf()
		.disable()
		.authorizeHttpRequests().antMatchers(public_URL).permitAll()
		.antMatchers(HttpMethod.GET).permitAll()
		.anyRequest()
		.authenticated()
		.and().exceptionHandling().authenticationEntryPoint(entrypoint).
		and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.customuserDetailService).passwordEncoder(this.passwordEndcoder());
	}
	
	@Bean
	public PasswordEncoder passwordEndcoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	 
	 
}
