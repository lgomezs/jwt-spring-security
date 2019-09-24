Spring Security - Web Security
==========================

This is application based on SPRING REST Security AND using JSON Web Token (JWT). 

Provide the Spring Security configuration - in our example this is done with a WebSecurityConfigurerAdapter.

the class main is WebSecurityConfig.
	- Require the user to be authenticated prior to accessing any URL within our application.
	- Require a user with the username, password and roles.
	
# Requirements

  This application is build with:
        - maven 3
        - Java 8
        - docker v18.09.2

# Dependency

  For connection with mongo require the artifactId mongodb:
  
  GitHub (compile y generate artifact): 
  
  	git clone https://github.com/lgomezs/appcore-mongodb
  	cd appcore-mongodb
  	mvn clean install
	
# Configuration

 - Configure mongdb -> config-default.properties
  	
 - Configure data for generate jwt encrypt and port for the application. -> application.yml	

# Generate Library

   mvn clean install -Dmaven.test.skip=true -Dfindbugs.skip=true -Dpmd.skip=true

# Running with docker

  ##Run mongo
  
    docker volume create --name=mongodbdata
    docker-compose up mongo
  
  ![Screenshot from running application](img/mongo.png?raw=true "Mongo start")

  
  ##Run scripts (data)
  
    docker-compose build mongo_se
    docker-compose up mongo_se
  
  ![Screenshot from running application](img/data.png?raw=true "Mongo start")


  ##Run application
        
    docker-compose build jwt-spring-security
    docker-compose up jwt-spring-security
     
  http://localhost:8080/api/auth/login
    	
    	
  ![Screenshot from running application](img/post_auth.png?raw=true "Screenshot JWT Spring Security Demo")
  
  
  
  ![Screenshot from running application](img/login.png?raw=true "Screenshot JWT Spring Security Demo")
  
  