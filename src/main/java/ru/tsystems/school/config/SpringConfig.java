package ru.tsystems.school.config;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.tsystems.school.dao.PassengerDao;
import ru.tsystems.school.dao.impl.PassengerDaoImpl;
import ru.tsystems.school.model.dto.TrainDto;

import java.util.Collections;


@Configuration
@ComponentScan("ru.tsystems.school")
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

    @Bean
    public MappingJackson2MessageConverter messageConverter() {

        val converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("content-type");
        converter.setTypeIdMappings(Collections.singletonMap("train", TrainDto.class));
        return converter;
    }

}
