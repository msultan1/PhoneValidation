# PhoneValidation
number Validation WebServices Made in JAVA SpringBoot

steps to Build :
	1- build the package with unit tests through maven [mvn package] [there is a sample build in the target file]
	2- After package Generated in tragert folder , Build Docker Image through this commands : 
	  [docker build -t phone_validation:latest .]
	  

Steps To Run in 2 different forms : 
    
   A- [UI webpage] 
	1- Deploy Docker image through this command : [docker run -p 8080:8080 phone_validation]
	3- open these url in your browser : localhost:8080
	
   B- [postman webservices] "support pagination"
      1- Deploy Docker image through this command : [docker run -p 8080:8080 phone_validation]
      2- CRUD services can be used through this base url : [localhost:8080\customers]
      3- CRUD services can be used through this base url : [localhost:8080\countries]
      4- Also Added new filters for customers webservices : 
         url : http://localhost:8080/customers/search/{FILTER_NAME}?{PARAMTERS}
        -findByCountry : parameters : country
        -findByState : parameters : state
        -findByCode : parameters : code
        -findByCountryAndState : parameters : country & state
        -findByCountryAndCode : parameters : country & code
        -findByStateAndCode : parameters : state & code
        -findByStateAndCodeAndCountry : parameters : state & code & country
      5- pagination is supported in the webservices , each page has default size of 20 elements ,
    	you can change the size for each page or the page number through parameters of : 
    	[size = {records_number_per_page}] [page={page_number}] 
    	ex : [localhost:8080\customers?size=40&page=1] : that means requested get page 1 with size of 40 records/page
	
		
- unit test location is in : ..\phoneValidation\src\test\java\com\sumo\phoneValidation 
  Note : unit test already run in the JAR packaging Phase 
