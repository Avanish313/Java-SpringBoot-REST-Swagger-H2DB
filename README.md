# Java-SpringBoot-REST-Swagger-H2DB
This is a sample project to develop REST API's using SpringBoot, Java and Rest protocols.

# Application Description
### API's
Exposes total five api's 

* itemEligible - Checks if the item is eligible for the new shipping program. Returns true if item meets the minimum 
price criteria and is from approved seller and category.
Minimum price configured in Application.properties is $20.00.

* item eligible
* Sample request
 {
  "category": 10,
  "price": 20,
  "seller": "A",
  "title": "A book"
 }

* Sample response
 {
  "eligible": true
 }

* seller empty - item not eligible 
* Sample request
  {
  "category": 10,
  "price": 20,
  "seller": "",
  "title": "A book"
  }

* Sample Response
  {
  "eligible": false,
  "error": {
    "message": "Invalid Data: Seller cannot be null or empty."
  }

* addSeller - Add a seller to the approved sellers list.

* deleteSeller - Delete a seller from the approved sellers list.

* addCategory - Add a category to the approved categories list.

* deleteCategory - Delete a category from the approved categories list.

## Requirements
* Java 8
* Maven 3.6.3
* Spring Boot 2.2.5

## Dependencies added in pom.xml
* Swagger2
* Swagger-ui
* Swagger annotations
* Swagger model
* h2 in memory database
* Junit
* Mockito

## Running project locally

**Step 1**: Compile the project from root folder
```
mvn clean install
```
**Step 2**: Use maven spring-boot command to run the application from root folder:
```
mvn spring-boot:run
```

### Using JBOSS or any other Java Servlet Container

Use Maven to get the WAR file:
```
mvn clean install
```
The WAR file will be located in `target` folder

### Application properties
* Application.properties - contains property for minimum price and properties to connect to h2 dn and h2 console.

### h2 DB
Have used in memory h2 db to create table and store list of approved sellers in table approved_sellers.
At bootstrap data will inserted in this table by reading data.sql file.

Once applications starts, h2 db console can be accessed at

* [h2_console](http://localhost:8080/h2-console/)

Provide below details for connecting to the h2 database
* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:testdb
* User name: admin
* Password: admin

Above information can also be found in Application.properties file

#### Swagger

Once application starts swagger, all api details can be accessed at

* [Swagger UI](http://localhost:8080/swagger-ui.html#/The_new_shipping_program_API)


