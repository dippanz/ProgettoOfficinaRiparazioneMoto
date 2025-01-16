/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	        )
	        .authorizeHttpRequests(authorize -> authorize
	            //.requestMatchers("/login", "/resources/**", "/public/**").permitAll()
	            //.requestMatchers("/admin/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .defaultSuccessUrl("/home", true)
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .permitAll()
	        );

	    return http.build();
	}

}
