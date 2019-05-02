package com.eduKmania.site.config;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.eduKmania.site.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		return bCryptPasswordEncoder;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		 // Setting Service to find User in the database.
        // And Setting PassswordEncoder
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{

		http.csrf().disable();
		
		// The pages does not require login
		http.authorizeRequests().antMatchers("/", "/login", "/logout", "/resources/**")
								.permitAll();
		
		 // /userInfo page requires login as ROLE_USER_A or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
		http.authorizeRequests().antMatchers("/apprenant/**")
								.access("hasAnyRole('ROLE_APPRENANT','ROLE_ADMIN')");
		
		// /userInfo page requires login as ROLE_USER_P or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
		http.authorizeRequests().antMatchers("/repetiteur/**")
								.access("hasAnyRole('ROLE_REPETITEUR','ROLE_ADMIN')");
		
		 // /userInfo page requires login as ROLE_MANAGER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
		http.authorizeRequests().antMatchers("/manager/**")
								.access("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')");
		
		 // /userInfo page requires login as ROLE_ADMIN only.
        // If no login, it will redirect to /login page.
		http.authorizeRequests().antMatchers("/admin/**")
								.access("hasAnyRole('ROLE_ADMIN')");
		
		// When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
		http.authorizeRequests().and()
								.exceptionHandling()
								.accessDeniedPage("/403");
		// Config for Login Form
		http.authorizeRequests().and().formLogin()
			.loginPage("/login")
			.successHandler(customSuccessHandler)
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			 // Config for Logout Page
			.and()
			.logout()
			.logoutSuccessUrl("/?decoonexion")
			.deleteCookies("JSESSIONID")
			.and()
			.rememberMe()
			.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(1*24*60*60);
		
		
		http.sessionManagement()
			.sessionFixation()
			.migrateSession()
			.maximumSessions(1)
			.expiredUrl("/login?expired");
		
//		http.authorizeRequests().and().formLogin()
//		.loginPage("/j_spring_security_check")
//		.defaultSuccessUrl("/apprenant")
//		.failureUrl("/login?error=true")
//		.usernameParameter("username")
//		.passwordParameter("password")
//		 // Config for Logout Page
//		.and().logout()
//		.logoutUrl("/logout")
//		.logoutSuccessUrl("/");
		
		 // Config Remember Me.
//		http.authorizeRequests().and()
//			.rememberMe().tokenRepository(this.persistentTokenRepository())
//			.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
			
		
	}
	
	// Token stored in Table (Persistent_Logins)
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		
		db.setDataSource(dataSource);
		
		return db;
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	// Token stored in Memory (Of Web Server).
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//	    InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//	    return memory;
//	}
}