# Emergency
## Backend Installation

Prerequisites:

Java: Spring Boot is Java-based, so you need to ensure Java is installed. Typically, Spring Boot recommends using Java 8, 11, or later versions.
Maven 
Installation Steps

a. Installing Java:

Download and install the Java JDK. It can be obtained from the Oracle official website or other Java distribution sites.
Configure the environment variables. Ensure that JAVA_HOME is set and that the bin directory is added to the PATH.
b. Installing Maven (if the project uses Maven):

Download the appropriate version of Maven from the official Maven website.
Unzip and configure the environment variables. Ensure that MAVEN_HOME or M2_HOME is set, and the bin directory is added to the PATH.
Using Maven:

bash:
mvn clean install
mvn spring-boot:run
Accessing the Application

Once the application starts successfully, you can access it in your browser at the default address http://localhost:8080, unless you've changed the default port in the configurations.
## Backend Libraries
- org.springframework.boot:spring-boot-starter-security
- **Version 2.7.15-SNAPSHOT**
- mysql:mysql-connector-java
- **Version 8.0.28**
- org.apache.commons:commons-lang3
- **Version 3.12.0**
- org.springframework.boot:spring-boot-starter-data-redis
- com.alibaba:fastjson
- **Version 1.2.67**
- org.springframework.boot:spring-boot-starter-mail
- **Version 2.6.3**
- io.jsonwebtoken:jjwt
- **Version 0.9.0**
- org.springframework.boot:spring-boot-starter-data-jpa
- org.springframework.boot:spring-boot-starter-thymeleaf
- org.springframework.boot:spring-boot-starter-web
- org.thymeleaf.extras:thymeleaf-extras-springsecurity5
- org.springframework.boot:spring-boot-devtools
- org.projectlombok:lombok
- org.springframework.boot:spring-boot-starter-test
- org.springframework.security:spring-security-test

## Working functionalities
- The system enables the user to register / log in / log out. 
- The system can present the case and hospital situation in the form of a map. 
- The system shows the clinic and case information. 
- The system provides a search function. 
- The system assists the users to type in addresses.
- The system allows the users to get the emergency notification through email. 
- The system provides some health tips. 
- The system provides some warning news. 
- The system allows the admin users to manage the cases.
- The system allows the admin users to publish the announcements.
- The system provides an intelligent chatbot.
- The system provides some guidance to the users about how to use the system.
- This system provides contact information for the operations team. 
