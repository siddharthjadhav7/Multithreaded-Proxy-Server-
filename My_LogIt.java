/* This class is designed to log user requests when called for each ProxyThread.
 */
package proxy;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.util.Date;


public class My_LogIt 
{
    public My_LogIt()
    {
    }
    
    
    String logFile = "logRequests.txt";  // The file where log information is stored.
    Date date = new Date();              //used to display current time.  
    
    /** This method is used to display the output in log.txt file**/
    
    public synchronized void logRequest(String clientURL, String clientIP,String requestLength,String i,Double elapsedSeconds,String ip) throws IOException 
    {
        
        try
        {
        	
            PrintWriter outputStream = new PrintWriter(new FileWriter(logFile, true));  //writes information to log file.
            outputStream.write("\n***********************");
            outputStream.write("\n***********************");
            outputStream.write("\r\n " + date + "\r\n" + clientURL + "\r\n CLIENT IP ADDRESS IS : " + clientIP + "\r\n REQUEST LENGTH IS: " + requestLength + "\r\n RESPONSE LENGTH IS: " + i + "\r\n TIME ELAPSED IS: " + elapsedSeconds + "\r\n HOST IP ADDRESS IS : " +ip+"\r\n HOST PORT NUMBER IS : 80");
            
            outputStream.close();
        }
        catch(FileNotFoundException e)
        {
        }
        
    }
    
    
}
