package com.rapid.vit;

import com.rapid.vit.config.jwt.JwtConfig;
import com.rapid.vit.email.SESMailConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class, SESMailConfig.class})
public class RapidVitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RapidVitApplication.class, args);
	}

}
