package com.indracompany.treinamento.config;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import com.sun.faces.config.ConfigureListener;
import com.sun.faces.config.FacesInitializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FacesConfig implements ServletContextInitializer {

  @Override
  public void onStartup(final ServletContext container) throws ServletException {

    final FacesInitializer facesInitializer = new FacesInitializer();
    final Set<Class<?>> clazz = new HashSet<>();
    clazz.add(FacesConfig.class);

    container.addListener(ConfigureListener.class.getName());
    container.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
    container.setInitParameter("com.ocpsoft.pretty.BASE_PACKAGES", "com.indracompany.treinamento");
    container.setInitParameter("com.sun.faces.enableRestoreView11Compatibility", "true");
    container.setInitParameter("javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE", "true");
    container.setInitParameter("javax.faces.CONFIG_FILES", "/WEB-INF/faces-config.xml");
    container.setInitParameter("primefaces.THEME", "none");
    container.setInitParameter("primefaces.FONT_AWESOME", "true");
    container.setInitParameter("javax.faces.STATE_SAVING_METHOD", "client");
    container.setInitParameter("javax.servlet.jsp.jstl.fmt.localizationContext", "resources.application");
    container.setInitParameter("javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL", "true");
    container.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
    container.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
    container.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "0");
    container.setInitParameter("javax.faces.FACELETS_BUFFER_SIZE", "131072");
    container.setInitParameter("primefaces.SUBMIT", "partial");
    container.setInitParameter("primefaces.MOVE_SCRIPTS_TO_BOTTOM", "true");
    container.setInitParameter("com.sun.faces.numberOfViewsInSession", "15");
    container.setInitParameter("com.sun.faces.compressViewState", "false");
    facesInitializer.onStartup(clazz, container);
  }

  @Bean(name = "openEntityManagerInViewFilter")
  public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
    return new OpenEntityManagerInViewFilter();
  }

  @Bean
  public FilterRegistrationBean<OpenEntityManagerInViewFilter> openSessionInViewFilterRegistration() {

    final FilterRegistrationBean<OpenEntityManagerInViewFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(this.openEntityManagerInViewFilter());
    registration.addUrlPatterns("/*");
    registration.setName("openEntityManagerInViewFilter");
    registration.setOrder(1);
    return registration;
  }

  @Bean(name = "PrimeFaces FileUpload Filter")
  public FileUploadFilter someFilter() {
    return new FileUploadFilter();
  }

  @Bean
  public FilterRegistrationBean<FileUploadFilter> someFilterRegistration() {

    final FilterRegistrationBean<FileUploadFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(this.someFilter());
    registration.setOrder(2);
    return registration;
  }

}
