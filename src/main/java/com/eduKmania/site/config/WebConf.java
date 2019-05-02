package com.eduKmania.site.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
public class WebConf extends WebMvcConfigurerAdapter{

	//BOOTSTRAP
		
		 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		        //
		        // Access Bootstrap static resource:
		        //
		  
		        //
		        // http://somedomain/SomeContextPath/jquery/jquery.min.css
		        //
		        registry.addResourceHandler("/jquery/**") //
		                .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/1.11.1/");
		  
		        //
		        // http://somedomain/SomeContextPath/popper/popper.min.js
		        //
		        registry.addResourceHandler("/popper/**") //
		                .addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/1.14.1/umd/");
		  
		        // http://somedomain/SomeContextPath/bootstrap/css/bootstrap.min.css
		        // http://somedomain/SomeContextPath/bootstrap/js/bootstrap.min.js
		        //
		        registry.addResourceHandler("/bootstrap/**") //
		                .addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.1.1/");
		        
		       registry.addResourceHandler("/css/**", "/img/**", "/js/**", "/fonts/**")
		       .addResourceLocations("classpath:/static/css/"
		    		   				,"classpath:/static/img/",
		    		   				"classpath:/static/js/",
		    		   				"classpath:/static/fonts/");
		        
		        registry.addResourceHandler( "/admin/**",
		        		 					"/admin/css/**",
		        		 					"/admin/css/bootstrap/**",
		        		 					"/admin/css/datatables/**",
		        		 					"/admin/css/fontawesome-free/**",
		        		 					"/admin/css/fontawesome-free/css/**",
		        		 					"/admin/css/fontawesome-free/js/**",
		        		 					"/admin/css/jquery/**",
		        		 					"/admin/css/jquery-easing/**",
		        		 					"/admin/js/**",
		        		 					"/admin/js/bootstrap/**",
		        		 					"/admin/js/datatable/**",
		        		 					"/admin/js/demo/**",
		        		 					"/admin/js/jquery/**")
		        .addResourceLocations("classpath:/static/admin/",
		        					"classpath:/static/admin/css/",
		        					"classpath:/static/admin/css/bootstrap/",
		        					"classpath:/static/admin/css/bootstrap/css/",
		        					"classpath:/static/admin/css/bootstrap/js/",
		        					
		        					"classpath:/static/admin/css/datatables/",
		        					"classpath:/static/admin/css/fontawesome-free/",
		        					"classpath:/static/admin/css/fontawesome-free/css/",
		        					"classpath:/static/admin/css/fontawesome-free/js/",
		        					"classpath:/static/admin/css/jquery/",
		        					"classpath:/static/admin/css/jquery-easing/",
		        					
		        					"classpath:/static/admin/js/",
		        					"classpath:/static/admin/js/bootstrap/",
		        					"classpath:/static/admin/js/datatable/",
		        					"classpath:/static/admin/js/jquery/",
		        					"classpath:/static/admin/js/demo/");
		    }
		 
		 @Bean
		    public MessageSource messageSource() {
		        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		        // Load file: validation.properties
		        messageSource.setBasename("classpath:validation");
		        messageSource.setDefaultEncoding("UTF-8");
		        return messageSource;
		    }
		
}

