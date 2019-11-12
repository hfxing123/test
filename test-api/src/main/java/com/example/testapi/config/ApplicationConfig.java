package com.example.testapi.config;

import com.example.testapi.filter.JwtFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 添加拦截器到环境中
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    private static Logger log= LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    private JwtFilter jwtFilter;

    //使用UTF-8
    @Bean
    public HttpMessageConverter responseBodyConverter()
    {
        StringHttpMessageConverter converter = new StringHttpMessageConverter( Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**")

                .excludePathPatterns("/login/**","/m/**"
                        ,"/swagger-ui.html","/configuration/ui","/swagger-resources/**","/configuration/security","/v2/api-docs","/error","/webjars/**","/**/favicon.ico")

                // /swagger-resources/configuration/ui

//                .excludePathPatterns("/m/**")
//                //swagger
//                .excludePathPatterns("/swagger-ui.html")
//                .excludePathPatterns("/configuration/ui")
//                .excludePathPatterns("/swagger-resources")
//                .excludePathPatterns("/configuration/security")
//                .excludePathPatterns("/v2/api-docs")
//                .excludePathPatterns("/error")
//                .excludePathPatterns("/webjars/**")
//                .excludePathPatterns("/**/favicon.ico")
                ;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //log.debug("进入了-----------------------------------");

        /*
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

         */

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

}