package ru.tsystems.school.config;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tsystems.school.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan({
        "ru.tsystems.school",
        "ru.tsystems.school.config",
        "ru.tsystems.school.controller",
        "ru.tsystems.school.dao",
        "ru.tsystems.school.dao.impl",
        "ru.tsystems.school.dto",
        "ru.tsystems.school.mapper",
        "ru.tsystems.school.model",
        "ru.tsystems.school.security",
        "ru.tsystems.school.service",
        "ru.tsystems.school.service.impl",
})
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider = new AuthProviderImpl();

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/trains/add").hasAuthority("ADMIN")
                .antMatchers("/trains").hasAuthority("ADMIN")
                .antMatchers("/stations/add").hasAuthority("ADMIN")
                .antMatchers("/passengers/**").hasAuthority("ADMIN")
                .antMatchers("/login").anonymous()
                .antMatchers("/account").authenticated()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/");
    }
}
