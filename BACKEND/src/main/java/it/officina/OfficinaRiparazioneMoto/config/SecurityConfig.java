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
 * Configuration class that defines the security settings for the application.
 * <p>
 * This class configures:
 * <ul>
 * <li>HTTP security using a
 * {@link org.springframework.security.web.SecurityFilterChain} bean.</li>
 * <li>Disabling of CSRF protection.</li>
 * <li>Session management with a custom invalid session URL.</li>
 * <li>Authorization for different URL patterns based on user roles, including
 * public access for resources
 * (such as CSS, JS, images, and public endpoints) and restricted access for
 * admin, accettazione, and meccanico sections.
 * Additionally, all other requests require authentication.</li>
 * <li>Form based login configuration with a custom login page, processing URL,
 * success and failure URLs.</li>
 * <li>Definition of a
 * {@link org.springframework.security.crypto.password.PasswordEncoder} bean
 * using BCrypt hashing.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The security settings ensure that the application correctly handles session
 * management, role-based access control,
 * and password encoding.
 * </p>
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session
						.invalidSessionUrl("/public/login?sessionExpired=true"))
				.authorizeHttpRequests(authorize -> authorize
						.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
						.requestMatchers(
								"/",
								"/css/**",
								"/js/**",
								"/images/**",
								"/public/**")
						.permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/accettazione/**").hasRole("ADDETTO_ACCETTAZIONE")
						.requestMatchers("/meccanico/**").hasRole("MECCANICO")
						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/public/login")
						.loginProcessingUrl("/public/process_login")
						.defaultSuccessUrl("/", true)
						.failureUrl("/public/login?error")
						.permitAll());

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
