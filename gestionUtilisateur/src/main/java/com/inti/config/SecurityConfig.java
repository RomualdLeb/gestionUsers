package com.inti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.inti.services.impl.AppUserDetailsService;

@Configuration
@EnableWebSecurity //active la securité web
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	//stock l'état de appUserDetailsService 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{	
		auth.userDetailsService(appUserDetailsService).passwordEncoder(bCryptPasswordEncoder);;		
	}
	
	//methode basic d'authentification
	protected void configure(HttpSecurity http) throws Exception {
		http//pour chaque req taper dans le navigateur
		.authorizeRequests()
			.anyRequest().authenticated()//toute les req doivent passer par la procédure de connexion
		.and()
		.formLogin()
			.permitAll()//il faut faire la connexion avant d'ouvrir la ressource
		.and()
		.logout()
			.logoutUrl("/logout")//un url pour faire la deconnexion
			.permitAll()
		.and()
		.httpBasic()//la methode de securité
		.and()
		.csrf().disable();//désactiver le cross-site request forgery (car vulnaribilité)		
	}
	
	
	
}
