# Account Listing and Transaction history functionality
* The end points can be checked through http://localhost:8080/swagger-ui.html
* http://localhost:8080/accounts should display the list of accounts for the given customer id, customer id should be part of request (in sql customer id 1001 added)
* http://localhost:8080/accounts/transaction should display transactions and balance for given account id which should be part of request(only 1000000007 have transaction data)
* No authentication implemented.
* Repository test cases works only after commenting SwaggerConfig.java and removing data.sql file.
* Since h2 database tables are created by springboot on run time, schema is not added.