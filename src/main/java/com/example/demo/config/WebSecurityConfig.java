package com.example.demo.config;

import com.example.demo.security.JwtRequestFilter;
import com.example.demo.security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final MyUserDetailsService myUserDetailsService;

	private final PasswordEncoder passwordEncoder;

	private final JwtRequestFilter jwtRequestFilter;

	@Bean
	public JwtRequestFilter jwtAuthenticationFilter() { return new JwtRequestFilter(); }

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//go from most restrictive url to least restrictive, from single urls to /**
		httpSecurity.csrf().ignoringAntMatchers("/**").and().cors().and()
//				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
//				.addFilter(new JwtAuthorizationFilter(authenticationManager()))
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		//todo: usun ta linijke, jest tylko po to by mozna bylo dostac sie do h2 console
		httpSecurity.headers().frameOptions().disable();
	}
}