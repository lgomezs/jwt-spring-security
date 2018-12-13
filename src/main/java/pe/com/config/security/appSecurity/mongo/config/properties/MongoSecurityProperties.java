package pe.com.config.security.appSecurity.mongo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import pe.com.core.mongo.mongodb.config.MongoDbProperties;

@PropertySource("classpath:config-default.properties")
@ConfigurationProperties(prefix="seguridad")
public class MongoSecurityProperties extends MongoDbProperties{
		
}
