package com.music.app.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.music.app.backend.config.CustomCorsConfiguration;
import com.music.app.backend.service.MyUserDetailService;


@Configuration
public class SecurityConfiguration {

    private final MyUserDetailService myUserDetailService;
    @Autowired CustomCorsConfiguration customCorsConfiguration;
    // Constructor injection for MyUserDetailService
    public SecurityConfiguration(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    /**
     * Configures the security filter chain.
     * 
     * @param httpSecurity the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception in case of any configuration error
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        
         

        return httpSecurity.authorizeHttpRequests(
                        authorize -> {
                            // Permit access to static resources and login, home, and error pages
                            authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll();
                            authorize.requestMatchers("/api/auth/*").permitAll();
                            authorize.requestMatchers("/login", "/error/**", "/logout", "/", "/home").permitAll();
                            // Restrict access to admin and user pages based on roles
                            authorize.requestMatchers("/admin/**").hasRole("ADMIN");
                            authorize.requestMatchers("/user/**").hasRole("USER");
                            // All other requests require authentication
                            authorize.anyRequest().authenticated();
                        }
                ).formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/home", true)  // Redirect to home after successful login
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")  // Redirect to login page after logout
                        .permitAll()
                )
                // .sessionManagement(session -> 
                // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // .oauth2ResourceServer(server -> server
                //         .jwt(Customizer.withDefaults())
                // )

                // .csrf(csrf -> csrf
                //     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //     .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()) // For header-based token handling
                // )
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                 .cors(c -> c.configurationSource(customCorsConfiguration))
                .build();
    }


    /**
     * Configures the AuthenticationProvider.
     * 
     * @return the configured AuthenticationProvider
     */
    @SuppressWarnings("deprecation")
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Configures the password encoder.
     * 
     * @return the configured BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
