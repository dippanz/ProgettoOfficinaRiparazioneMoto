/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

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
	        .authorizeHttpRequests(authorize -> authorize
	        	.dispatcherTypeMatchers(DispatcherType.FORWARD,DispatcherType.ERROR).permitAll()
	            .requestMatchers(
					"/",
					"/css/**",
					"/js/**",
					"/images/**",
					"/meccanico/**",
					"/public/**"
					).permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/accettazione/**").hasRole("ADDETTO_ACCETTAZIONE")
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
                .loginPage("/public/login")
                .loginProcessingUrl("/public/process_login") 
                .defaultSuccessUrl("/", true) 
                .failureUrl("/public/login?error") 
                .permitAll()
            );

	    return http.build();
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
