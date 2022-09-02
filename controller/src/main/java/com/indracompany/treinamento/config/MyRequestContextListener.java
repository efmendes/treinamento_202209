package com.indracompany.treinamento.config;

import javax.servlet.annotation.WebListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@WebListener
public class MyRequestContextListener extends RequestContextListener {
}
