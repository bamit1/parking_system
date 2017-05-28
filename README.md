# parking_system

This is a parking lot management system.
It handles basic parking session where a vehicle checkin’s and checkouts are being tracked,it also calculates cost of the parking session.

About :

This system contains a total of 3 API's : 

1.  "{host}/parking/scheme" : This is aims to be a Admin section api so that the cost calution algorithm can be set by admin.
Price Scheme is same for all days and is updated whenever admin updates the system
Its request body is of format :

{
 "vType":"TWO_WHEELER",
 "cType":[{"type":"ft","hours":2,"price":25.0},{"type":"ft","hours":3,"price":20.0},{"type":"vt","hours":null,"price":5.0}]
}

where 'vType' is vehicle type (Accepted Values : "TWO_WHEELER" , "FOUR_WHEELER") ,
     'cType' is the scheme definition. It is a list of definitions for cost calculation. 
     Here, type tells whether it is of fixed hour charges type or per hour charges type (Accepted Values : "ft" , "vt")

eg: 
Body: "cType":[{"type":"ft","hours":2,"price":25.0},{"type":"ft","hours":3,"price":20.0},{"type":"vt","hours":null,"price":5.0}]
Meaning:"Rs 25 for first 2 hours then Rs 20 for next 3 hours, then Rs 5 every hour"

Body: "cType":[{"type":"ft","hours":null,"price":25.0}]
Meaning:"Fixed Rs 25 charge irrespective of hours of parking"

Body: "cType":[{"type":"vt","hours":null,"price":25.0}]
Meaning:"Per hour charges of Rs 25"

Body: "cType":[{"type":"vt","hours":3,"price":20.0},{"type":"vt","hours":null,"price":5.0}]
Meaning:"Rs 20 per hour for first 3 hours, then Rs 5 every hour"

Body: "cType":[{"type":"ft","hours":3,"price":20.0},{"type":"vt","hours":null,"price":5.0}]
Meaning: Rs 20 for first 3 hours then Rs 5 every hour


2. "{host}/parking/checkin?vNum=10HR19&vType=TWO_WHEELER&checkIn=2016-04-13 12:00:01"

Date Format should be : yyyy-MM-dd HH:mm:ss

This api accepts vehicle number and its type whether "TWO_WHEELER" or "FOUR_WHEELER" and the check in time

It makes an entry in the db for the vehicle and the payslip table. Marks vehicle in IN state.
Response contains data for the payslip 


3. "{host}/parking/checkout?vNum=10HR19&checkOut=2016-04-13 19:00:01"

This api accepts vehicle number and the check out time

It updates entry in the db for the payslip table. Marks vehicle in OUT state.
Response contains data for the payslip 

------------------------------------------------

Parking.postman_collection.json is the postman collection of the APi's

------------------------------------------------

Setup : 

1. Run commands on mysql db from : mysql-inst.sql

2. Git clone project in a directory. Move to that directory in terminal and run below commands

3. This is a Maven project and runs on tomcat server with rumtime parameters as mysql-username, mysql-host, mysql-password

Build command : mvn clean install

Run Command : mvn clean install tomcat7:run-war -Dmysql-username={username} -Dmysql-password={password} -Dmysql-host={host of database}

4. Project can be imported in an IDE by importing as Maven project.


