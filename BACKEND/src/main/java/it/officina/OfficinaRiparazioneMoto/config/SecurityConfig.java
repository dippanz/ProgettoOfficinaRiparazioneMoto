/**
 * 
 */
package it.officina.OfficinaRiparazioneMoto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
	        /*.csrf(csrf -> csrf.disable())
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	        )*/
	    	.securityMatcher("/api/**")
	        .authorizeHttpRequests(authorize -> authorize
	        	.dispatcherTypeMatchers(DispatcherType.FORWARD,DispatcherType.ERROR).permitAll()
	            .requestMatchers("/","/resources/**", "/home/**").permitAll()
	            //.requestMatchers("/admin/**").hasRole("ADMIN")
	            .anyRequest().denyAll()
	        )
	        .formLogin(Customizer.withDefaults())
	        /*.formLogin(form -> form
	            .loginPage("/login")
	            .defaultSuccessUrl("/", true)
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .permitAll()
	        )*/;

	    return http.build();
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
