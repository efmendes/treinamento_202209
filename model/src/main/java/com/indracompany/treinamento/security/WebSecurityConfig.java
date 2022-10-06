package com.indracompany.treinamento.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    public final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic() //configurações básicas de conexão
                .and()//concatenando as configurações
                .authorizeRequests()
                .antMatchers("/rest/login/**").permitAll() //Permitindo acesso ao endpoint de login
                .antMatchers("/rest/clientes/**").permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().cors();//desativando a proteção de csrf para conseguir fazer POST e DELETE sem tomar o erro "Forbiden"

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) //Essa seria uma autenticação usando o JPA, que busca o usernamen na base de dados
                .passwordEncoder(passwordEncoder());
    }

    @Bean //Bean ao que tudo indica é como se fosse um método statico, que vc pode usar sem que ele seja implementado.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
