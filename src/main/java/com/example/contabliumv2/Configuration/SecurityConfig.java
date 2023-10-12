package com.example.contabliumv2.Configuration;

import com.example.contabliumv2.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/registrar_asiento",
                        "/home",
                        "/ver_asientos",
                        "/generar_reportes",
                        "/guardar_asiento",
                        "/guardar_detalle",
                        "/preparar_asiento",
                        "/detalle/{id}",
                        "/generar_libro_diario",
                        "/generar_libro_mayor",
                        "/agregar_cuenta",
                        "/plan_de_cuentas").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home",true).permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();

                return http.build();


    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }
}
