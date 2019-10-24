I did not get the time to manage to run this application from the command line. Instead, I have been running it from IntelliJ using Jetty. The configuration can be seen in JettyConfiguration.png. I believe the key to running this from the command line would be to use the Jetty start.jar file, as indicated here:

https://www.eclipse.org/jetty/documentation/current/quickstart-deploying-webapps.html

However, I was not able to get it working, so currently it runs within IntelliJ only. Once the configuration is set up as indicated above, the application can then be run by clicking on the "Play" button as indicated in HowToRun.png. Upon completion of deployment, Chrome will open with a page that displays the list of products.

-----------------

***Interacting with the application***

Once the application is up and running, you can hit the endpoints using Postman:

 GET /api/products (get a list of products) 	    		  	

 GET /api/products/1 (get one product from the list)

 PUT /api/products/1 (update a single product)

 POST /api/products (create a product)	

You can also hit these endpoints from the command line. For example, to create a product:

curl -H "Content-Type: application/json" -d '{"name" : "peter", "currentPrice" : "10."}' http://127.0.0.1:8080/mock-web-app/api/products

-----------------

***Improvements***

The database-access object (DAO) currently in place is really a mock that stores all the information in memory, as I didn't have time to set up a database. However, I created a class RealBackendDao where I explain how I would have implemented the communication with the database. The database itself would have been implemented with Postgres.

The other major improvement would be to use some standard library to parse and sanitise requests, to avoid having to work with the strings, and also to facilitate checking for the necessary values.
