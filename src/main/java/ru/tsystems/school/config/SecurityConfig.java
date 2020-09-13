package ru.tsystems.school.config;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.tsystems.school.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("ru.tsystems.school")
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider = new AuthProviderImpl();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/trains/add", "/trains", "/stations/add", "/passengers/**")
                .hasAuthority("ADMIN")
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
