Spring Security - Web Security
==========================

This is application based on SPRING REST Security AND using JSON Web Token (JWT). 

Provide the Spring Security configuration - in our example this is done with a WebSecurityConfigurerAdapter.

the class main is WebSecurityConfig.
	- Require the user to be authenticated prior to accessing any URL within our application.
	- Require a user with the username, password and roles.
	

endpoint for login: /api/auth/login 
	
	![Screenshot](img/login.png?raw=true "Screenshot JWT Spring Security Demo")

# Requirements

  This application is build with maven 3, Java 8, MongoDB (in this demo for mongo connection, I used mlab). 

# Configuration

 - Configure mongdb -> config-default.properties
  	
 - Configure data for generate jwt encrypt and port for the application. -> application.yml	

# Generate Library

   mvn clean install -Dmaven.test.skip=true -Dfindbugs.skip=true -Dpmd.skip=true

# Running

   Just start the application with the Spring Boot maven plugin (mvn spring-boot:run) o execute class AppSecurityApplication.	
   The application is running at http://localhost:8200
   
# Docker


   

