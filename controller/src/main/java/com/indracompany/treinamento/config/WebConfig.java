package com.indracompany.treinamento.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

  
  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/modulo/**").addResourceLocations("/modulo/");
    registry.addResourceHandler("/acoes/**").addResourceLocations("/acoes/");
    registry.addResourceHandler("/cadastro/**").addResourceLocations("/cadastro/");
    registry.addResourceHandler("/error/**").addResourceLocations("/error/");
    registry.addResourceHandler("/images/**").addResourceLocations("/images/");
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(0);
  }
  
  @Override
  public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
              .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
  }

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/swagger-ui.html");
//    registry.addRedirectViewController("/", "/index.html");
    registry.addRedirectViewController("/rest/", "/swagger-ui.html");
  }

  @Override
  public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
    converters.add(customJackson2HttpMessageConverter());
    super.addDefaultHttpMessageConverters(converters);
  }

  @Bean
  public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
    final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    jsonConverter.setObjectMapper(objectMapper);
    return jsonConverter;
  }

}
