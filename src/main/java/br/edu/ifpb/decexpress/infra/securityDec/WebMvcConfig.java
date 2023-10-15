package br.edu.ifpb.decexpress.infra.securityDec;

import br.edu.ifpb.decexpress.infra.securityDec.DecExpressInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public DecExpressInterceptor customInterceptor() {
        return new DecExpressInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor());
    }
}
