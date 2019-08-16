# Call taxation
by João Carlos (https://www.linkedin.com/in/joaocarlosvale/)

This project consists of an application to calculate the total cost for calls registered in a 
file of calls and following some rules:
- The first 5 minutes of each call are billed at 5 cents per minute
- The remainer of the call is billed at 2 cents per minute
- The caller with the highest total call duration of the day will not be charged 
(i.e., the caller that has the highest total call duration among all of its calls)

## Technologies used:
* Java
* Lombok
* JUnit
* Maven 

## Example:

File: 

  Example in the _resources_ folder.
  
Run:

    java -jar target/billCalls-0.1.jar <path>/calls.txt

Input file:

    09:11:30;09:15:22;+351914374373;+351215355312
    15:20:04;15:23:49;+351217538222;+351214434422
    16:43:02;16:50:20;+351217235554;+351329932233
    17:44:04;17:49:30;+351914374373;+351963433432

Output:

    0.51

## Commands:

To generate JAR:

    mvn clean package

To run:

    java -jar target/billCalls-0.1.jar [file_path]
    
To run tests:

    mvn test
