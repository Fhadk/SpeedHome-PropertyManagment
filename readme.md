# SpeedHome-PropertyManagment Spring Boot Security

# Architecture

	- Spring Boot
	- Spring security
	- Spring JWT (Json token)
	- Log4j

# Authentication / JWT Token

First, you need to get JWT token by calling /authenticate REST interface. It is POST method so set(using POSTMAN to get token):
    Header: Content-type = application/json
    Body: 
    {
      "username" : "foo",
      "password" : "foo"
    }

	- 200 mins timeout

# REST intefaces

Secondly, To call rest interface we have to set:

    Header: Content-type = application/json
            Authorization: Bearer "JWT Token" 

To get list of users currently accessing the api /getActiveUser
create property  /createProperty/type<String>/address<String>
serach property /searchProperty/address<String>
Approve property /approveProperty/address<String>
update property /updateProperty/type<String>/address<String>


# Spring Boot Test

Create three test cases:

1- getActiveUser 	// returns all the active users who are currently using that API
2- ApproveProperty 	// returns flag = "Approved !!" else failure 
	Assumption: If address contains USA than it will be approved else ignored.
	
3- createProperty  	// returns HashMap in case of success



