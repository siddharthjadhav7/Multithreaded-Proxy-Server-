******************** TITLE: Multithreaded Proxy Server with Caching ********************


////////Contents////////
 
1)Introduction  
2)System Requirements
3)How to configure the proxy settings 
4)How to run the program
5)References 


1)Introduction:

This is a program which acts as a proxy server in which the client makes a request to the proxy server. The proxy server then checks its local cache for the request.
If the requested page or file is present in its cache then it directly returns it back to the requesting client.If the request is not present its cache then the proxy
server forwards this request to the web server.It fetches the response back from the web server and sends it to the client. This program runs in a multi threaded
environment i.e. each request from the client runs in a separate thread.The proxy server is created to process only GET requests.    



2)System Requirements:

a)A executable version of JDK(Java Development Kit) or JRE(Java Runtime Environment).
b)A running version of Eclipse.(Used: Eclipse Mars)



3)How to configure the proxy settings:

a)Go to Firefox browser and open the menu. 
b)Click on options.
c)Select the Advanced option.
d)Choose the Network tab and clear the cached web content by clicking on clear now. 
e)Then click on settings.
f)Select the Manual proxy settings option.
g)Enter the HTTP proxy as 127.0.0.1 and the port number as 8080.  

 

4)How to run the program:

a)Import the project in a running version of Eclipse.
b)Right Click on the project folder and run the program as a java application.
c)Then enter the url in the browser as follows-  http://www.yahoo.com/ or  http://www.firefox.com/ or https://facebook.com/
d)Then right click on the project and choose refresh option.
e)The logRequests.txt folder appears below JRE System Library.
f)Double click on this file to open it.
g)This file contains the following information:
 
 Date and Time
 Host Name 
 CLIENT IP ADDRESS
 REQUEST LENGTH 
 RESPONSE LENGTH 
 TIME ELAPSED 
 HOST IP ADDRESS 
 HOST PORT NUMBER 

i)If the request is not available in cache then a message is printed in console.
g)Else if it is present in cache it prints available in cache in console.



5)References:

a)http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html
b)http://www.tutorialspoint.com/javaexamples/net_multisoc.htm
c)http://www.java2s.com/Tutorial/Java/0320__Network/AmultithreadedSocketServer.htm
d)https://abet.soe.ucsc.edu/sites/default/files/ce155-middle.pdf
e)http://www.christianschenk.org/blog/implementing-a-simple-cache-with-java/







