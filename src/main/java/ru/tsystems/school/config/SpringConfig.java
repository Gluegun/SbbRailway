package ru.tsystems.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.tsystems.school.dao.PassengerDao;
import ru.tsystems.school.dao.impl.PassengerDaoImpl;


@Configuration
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
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "passengerDaoImpl")
    public PassengerDao foo() {
        return new PassengerDaoImpl();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }
}
