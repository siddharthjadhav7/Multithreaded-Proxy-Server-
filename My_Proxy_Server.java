package proxy;

import java.net.*;
import java.io.*;
import java.nio.*;

        

public class My_Proxy_Server {
    public static void main(String[] args) throws IOException {
        
        // This creates a webpage cache object
        My_Cache<InputStream> webCache = new My_Cache<InputStream>();
        
        //Variable Declaration 
        ServerSocket serverSocket = null;
        boolean listening = true;

        int port_num = 8080;	//default port_num number
        try {
            port_num = Integer.parseInt(args[0]);
        } catch (Exception e) {
            
        }

        try {
            serverSocket = new ServerSocket(port_num);
            System.out.println("Started on: " + port_num);
        } catch (IOException e) {
        }

        while (listening) {
            new My_Proxy_Thread(serverSocket.accept(), webCache).start();
        }
        serverSocket.close();
    }
}
