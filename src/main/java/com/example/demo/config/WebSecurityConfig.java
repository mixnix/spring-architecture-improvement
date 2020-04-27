package com.example.demo.config;

import com.example.demo.service.security.JwtRequestFilter;
import com.example.demo.service.security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final MyUserDetailsService myUserDetailsService;

	private final JwtRequestFilter jwtRequestFilter;

	@Bean
	public JwtRequestFilter jwtAuthenticationFilter() { return new JwtRequestFilter(); }

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//go from most restrictive url to least restrictive, from single urls to /**
		httpSecurity.csrf().disable().authorizeRequests()

				.antMatchers("/api/persons").hasAuthority("MANAGER")
				.antMatchers("/api/users/register").hasAuthority("MANAGER")

				.antMatchers("/api/persons/{personId}")
				.access("@securityExpressions.hasPersonId(authentication,#personId) or hasAuthority(\"MANAGER\")")

				.antMatchers("/h2-console/**").permitAll().
				antMatchers("/api/users/authenticate").permitAll().

				and().
						exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		//todo: usun ta linijke, jest tylko po to by mozna bylo dostac sie do h2 console
		httpSecurity.headers().frameOptions().disable();
	}
}