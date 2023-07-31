package com.my.noobspace.infra.config;

import com.my.noobspace.infra.error.RestAccessDeniedHandler;
import com.my.noobspace.infra.error.RestAuthenticationEntryPoint;
import com.my.noobspace.infra.error.RestAuthenticationFailureHandler;
import com.my.noobspace.infra.error.RestSuccessHandler;
import com.my.noobspace.infra.filter.CustomAuthenticationFilter;
import com.my.noobspace.infra.filter.CustomAuthorizationFilter;
import com.my.noobspace.infra.filter.JwtExceptionFilter;
import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.account.AccountQueryRepository;
import com.my.noobspace.modules.account.AccountRepository;
import com.my.noobspace.modules.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AccountRepository accountRepository;

    private final AccountQueryRepository accountQueryRepository;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers("/favicon.ico", "/static/**", "/templates/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), accountRepository);
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(successHandler());
        customAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());

        // csrf 보안 설정 끄기
        // 토큰 방식, 즉 stateless 기반 인증에선 서버에서 인증 정보를 보관하지 않기 때문에
        // csrf 공격에 안전하고 매번 csrf 토큰을 받기 않기 때문에 불필요)
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**").permitAll()
//                .requestMatchers(HttpMethod.POST, "/account").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
        );
        // 스프링 시큐리티가 세션을 생성하지 않고 기존 세션을 사용하지도 않음(JWT 사용을 위함)
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(ex -> ex
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
        );
        http.formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .permitAll()
        );
        // jwt 인증을 위한 커스텀 인증 필터 추가
        http.addFilter(customAuthenticationFilter);
        // UsernamePasswordAuthenticationFilter 필터보다 먼저 실행되어야 하므로 Before
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtExceptionFilter(), new CustomAuthorizationFilter().getClass());

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (UserDetailsService) email -> {
            Optional<Account> account = accountRepository.findAccountByEmail(email);
            if(account.isEmpty()) {
                throw new UsernameNotFoundException("No account found with email: " + email);
            }
            return account.get();
        };
    }


    // 사용할 인코더 설정
    // Bean으로만 등록해둬도 Spring security가 찾아서 적용해줌
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 외부에서 authenticationManager 적용시켜주기 위해 구현
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    //Cors 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("https://localhost"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    RestAccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler();
    }

    @Bean
    RestAuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    RestAuthenticationFailureHandler authenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

    @Bean
    RestSuccessHandler successHandler() {
        return new RestSuccessHandler();
    }

}
