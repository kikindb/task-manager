package dev.kikindb.task_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
        configuration.setExposedHeaders(Arrays.asList("x-auth-token")); // Expose the custom header
        configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies, authorization headers)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Allow same-origin framing
            )
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable()); // Optionally disable CSRF if it's not needed
            //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return httpSecurity.build();
    }

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//            .setType(EmbeddedDatabaseType.H2)
//            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//            .build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        var admin = User.withUsername("iscjedb@gmail.com")
//            .password("12345")
//            .passwordEncoder(str -> passwordEncoder().encode(str))
//            .roles("ADMIN")
//            .build();
//
//        var user = User.withUsername("diana@gmail.com")
//            .password("12345")
//            .passwordEncoder(str -> passwordEncoder().encode(str))
//            .roles("USER")
//            .build();
//
//        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//       jdbcUserDetailsManager.createUser(admin);
//       jdbcUserDetailsManager.createUser(user);
//
//        return jdbcUserDetailsManager;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public KeyPair keyPair() {
//      KeyPairGenerator keyPairGenerator = null;
//      try {
//        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//      } catch (NoSuchAlgorithmException e) {
//        throw new RuntimeException(e);
//      }
//      keyPairGenerator.initialize(2048);
//        return keyPairGenerator.generateKeyPair();
//    }

}
