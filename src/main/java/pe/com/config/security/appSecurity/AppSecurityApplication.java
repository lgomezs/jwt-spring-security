package pe.com.config.security.appSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import pe.com.config.security.appSecurity.mongo.config.MultipleMongoConfig;



@Configuration
@PropertySources({@PropertySource(value = "classpath:config-${APP_ENV:default}.properties")})
@Import(value = {MultipleMongoConfig.class})
@EntityScan("pe.com.config.security.appSecurity.bean")
@ComponentScan(basePackages = {
		"pe.com.config.security.appSecurity",
		"pe.com.core.mongo.mongodb"
	}
)
@SpringBootApplication
public class AppSecurityApplication  {
		
	public static void main(String[] args) {
		SpringApplication.run(AppSecurityApplication.class, args);
	}	 
}
