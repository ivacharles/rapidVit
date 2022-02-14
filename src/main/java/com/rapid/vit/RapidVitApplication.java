package com.rapid.vit;

import com.rapid.vit.config.jwt.JwtConfig;
import com.rapid.vit.email.SESMailConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
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
