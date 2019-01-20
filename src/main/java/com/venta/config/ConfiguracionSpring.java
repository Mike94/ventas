package com.venta.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
@ComponentScan("com.venta.*")
@EnableWebMvc
@Import({ConfiguracionPersistencia.class,ConfiguracionMVC.class})
public class ConfiguracionSpring extends WebMvcConfigurerAdapter{
	
	
	//REGISTRAMOS UN MANEJADOR DE RECURSOS PARA MAPEAR LOS RECURSOS ESTATICOS..CSS, JS, ETC
	@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/static/**")
	                .addResourceLocations("/resources/");
	    }  
	 
	 
	  
	
}

