package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// セキュリティフィルター　ログイン認証をコントロール
public class MessageSecurityConfig {
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize ->{
			authorize
			.requestMatchers("/").permitAll() // 開発時のみの設定
			.requestMatchers("/css/*").permitAll() // 開発時のみの設定
			.requestMatchers("/img/*").permitAll() // 開発時のみの設定
			.anyRequest().authenticated();	      // 開発時のみの設定
		});
		
		http.formLogin(form -> {form.defaultSuccessUrl("/secret");});
		
		return http.build() ;
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailManager() {
		String username = MessageBoardUtilities.username;
		String password = MessageBoardUtilities.password;
		
		UserDetails user = User.withUsername(username).password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password)).roles("USER").build();
		
		return new InMemoryUserDetailsManager(user);
	}
}
