package br.edu.ifpb.decexpress.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Permitir CORS para todas as URLs
                        .allowedMethods("*") // Permitir todos os métodos (GET, POST, PUT, DELETE, etc.)
                        .allowedOrigins("*"); // Permitir solicitações de qualquer origem
            }
        };
    }
}