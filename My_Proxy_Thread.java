package proxy;

import java.net.*;
import java.io.*;
import java.util.*;

public class My_Proxy_Thread extends Thread {  
    private Socket socket = null;
    My_Cache web_Cache;
    boolean page_Is_Cached = false;
    private static final int BUFFER_SIZE = 32768;
    public My_Proxy_Thread(Socket socket, My_Cache cache) {			
        super("ProxyThread");
        this.socket = socket;
        this.web_Cache = cache;
    }
    
    //Variable declaration
    
    My_LogIt logger = new My_LogIt();
    String reqlen;
    long tStart,tEnd,tDelta;	
    String method;
    double elapsedSeconds;
    String ipAddress;

    
    /**This method handles  
        input from user
        sends request to server
        gets response from server
        sends response to user **/
    
    public void run() {
        

        try {
            DataOutputStream out =
		new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(
		new InputStreamReader(socket.getInputStream()));

            String input_Line;
            int cnt = 0;
            String urlToCall = "";
            String method = null;
           
            //start with the GET request from client
            while ((input_Line = in.readLine()) != null) {

                try {
                    StringTokenizer tok = new StringTokenizer(input_Line);
                    tok.nextToken();
                } catch (Exception e) {

                }
                //parsing first line to find url
                if (cnt == 0) {
                	String[] tokens = input_Line.split(" ");
                	method = tokens[0];
                    urlToCall = tokens[1];
                    urlToCall.length();
                    // Display the request and client address
                    tStart = System.currentTimeMillis();
                    System.out.println("Request for : " + urlToCall);
                    System.out.println("Accepted Client Address - " + socket.getInetAddress().getHostName());
                    
                }
                if(input_Line.startsWith("Content-Length")){
                    String[] tokens = input_Line.split(" ");
                    reqlen = tokens[1];
                    System.out.println("RequestLength is : " + urlToCall.length());
                }
                cnt++;
            }	
            
            //Check for the GET method
            if(method.equals("GET")){
            	
            if(web_Cache.get(urlToCall) != null)
            {
                System.out.println("Web_page " + urlToCall + " available in cache.");
                page_Is_Cached = true;
            }
            else
            {
                System.out.println("Web_page " + urlToCall + " not available in cache.");
                page_Is_Cached = false;
            }
            
            
     /****Send request and fetch response from server****/

            BufferedReader rd = null;
   
                
                URL url = new URL(urlToCall);
                URLConnection con = url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(false);
                
                int respolen = 0;
                InputStream is = null;
                HttpURLConnection huc = (HttpURLConnection)con;
                if (con.getContentLength() > 0) {
                    
                        is = con.getInputStream();
                        rd = new BufferedReader(new InputStreamReader(is));
                        respolen = con.getContentLength();
                        System.out.println("ResponseLength is : " + respolen);
                        
                }
                
                //display the elapsed time
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Elapsed time is  : " + elapsedSeconds);
                System.out.println("ResponseLength is : " + respolen);
                
                //Update the web_Cache
                web_Cache.put(urlToCall, is);

                //send response to client
                InputStream is2 = (InputStream) web_Cache.get(urlToCall);
                
                InetAddress address = InetAddress.getByName(new URL(urlToCall).getHost());
                String ip = address.getHostAddress();
                System.out.println(ip.toString());
                
                
                // call to logit function to display data in log.txt
                logger.logRequest(urlToCall, socket.getLocalAddress().getHostAddress().toString(),""+urlToCall.length(),""+respolen,elapsedSeconds,ip);
                out.flush();	

           
            
	            //close all the resources
	            if (rd != null) {
	                rd.close();
	            }
	            if (out != null) {
	                out.close();
	            }
	            if (in != null) {
	                in.close();
	            }
	            if (socket != null) {
	                socket.close();
	            }
            }

        } catch (Exception e) {
        }
    }
}