package pe.com.config.security.appSecurity.mongo.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import pe.com.config.security.appSecurity.mongo.config.properties.MongoSecurityProperties;
import pe.com.core.mongo.mongodb.config.MongoDBConfig;

@Configuration
@EnableConfigurationProperties(MongoSecurityProperties.class)
@EnableReactiveMongoRepositories(
		basePackages = {
				"pe.com.config.security.appSecurity.repository"
			},	
		reactiveMongoTemplateRef="seguridadtemplateReference"
		)
public class MultipleMongoConfig extends MongoDBConfig {

	@Autowired
	private  MongoSecurityProperties mongoProperties;
	
	@PostConstruct
	public void init() {
		super.setProperties(mongoProperties);
	}	

	@Bean(name = "seguridadtemplateReference")
	public ReactiveMongoTemplate reactiveMongoTemplate() throws Exception {
		return super.reactiveMongoTemplate();
	}

	@Bean(name = "seguridadtemplateReferenceDbFactory")
	public ReactiveMongoDatabaseFactory mongoDbFactory() throws Exception {
		return super.mongoDbFactory();
	}
}
