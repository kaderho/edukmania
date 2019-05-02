package com.eduKmania.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.eduKmania.site.properties.FileStorageProperties;

@EnableConfigurationProperties({
    FileStorageProperties.class
})

@SpringBootApplication
@EnableJpaAuditing
public class SiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}

}
