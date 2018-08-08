package com.oshop;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.convert.Jsr310Converters;

import com.oshop.property.FileStorageProperties;

@SpringBootApplication
@EntityScan(basePackageClasses= {
		OshopBackEndApplication.class,
		Jsr310Converters.class
})
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class OshopBackEndApplication {
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(OshopBackEndApplication.class, args);
	}
}
