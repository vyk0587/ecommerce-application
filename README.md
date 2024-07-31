# ecommerce-application


### To create service
Group: com.onejava  
Name and Artifact: ecommerce-application  
Package name: com.onejava  
JDK17, Gradle, 3.3.2, Jar  
Dependencies > Spring Data JPA, Validation(Hibernate), Web, Flyway, Lombok, MySQL Driver, Spring Configuration Processor  
Other Dependencies:  
compileOnly 'org.apache.commons:commons-lang3'  

QueryDSL
implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
annotationProcessor 'com.querydsl:querydsl-apt:5.0.0:jakarta'
annotationProcessor 'jakarta.persistence:jakarta.persistence-api:3.1.0'

### Tools used
We have used JPA Buddy plugin to create Entities, Repositories, DTOs, Query DSL methods and Queries, Flyway Migration, adding validation for entities

### Steps

1. Project setup
2. Configuration added to connect to MYSQL ecommerce DB
3. Added SQL scripts V1 and V2 files in db migration folder and ran the application - It created DB tables in MYSQL DB
4. Generated entities using Intellij Idea DB option, we can use JPA Buddy too
   DB > ecommerce > RC on Tables > Create JPA entities from DB
5. Created ProductController to find and filter products
   ProductSpecification - Dynamic filtering
   ModelMapper Utility to convert Product to ProductDTO
6. Created Read me file - this file
7. Created AddressController to filter addresses
   Address Specification and Query DSL - Dynamic Filtering
   Added dependencies for queryDSL

### Topics covered

1. JPA buddy tool usage for generating Entities, Repositories, DTOs, Query DSL methods and Queries, Flyway Migration, adding validation for entities
2. ModelMappers and Dto support for APIs
3. Flyway migration scripts for DB
4. Simple RestController with CRUD operations
5. Projection - To return only required fields in an object. QueryDSL alternative for Specification. StrategyFactory Pattern to dynamically pick the service implementations

### Endpoints

/products/filter  
/products/name  
/products/nameContaining  

http://localhost:8070/products/filter?name=laptop&description=256gb&minPrice=100&maxPrice=1500  
http://localhost:8070/products/filter?description=wireless&minPrice=50&maxPrice=1500  
http://localhost:8070/products/name?name=laptop  
http://localhost:8070/products/nameContaining?name=smart  

http://localhost:8070/addresses?city=Springfield&state=IL&country=USA&postalCode=62701&userId=1&userName=john_doe&service=qdsl  
http://localhost:8070/addresses?city=Springfield&state=IL&country=USA&postalCode=62701&userId=1&userName=john_doe&service=jpa


