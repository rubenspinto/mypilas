package com.mypilas.aplication;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry; 

@SpringBootApplication
public class MypilasApplication {

	// função para configuar a linguagem para portugues
	public static void main(String[] args) {
		SpringApplication.run(MypilasApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	// Método para adicionar controladores de visualização

	@Configuration
	public static class MvcConfigurerAdapter implements WebMvcConfigurer {

		@Override
		public void addViewControllers(@org.springframework.lang.NonNull ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("index");
			registry.addViewController("/home").setViewName("index");
			registry.addViewController("/login").setViewName("login");
		}

	}

}
