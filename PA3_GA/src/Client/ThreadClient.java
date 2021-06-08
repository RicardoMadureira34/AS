/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author root
 */
public class ThreadClient extends Thread {
    JLabel teste;
    public final static int SOCKET_PORT = 8888;
    String ser=new String();
    Socket connect;
    
    
    public ThreadClient(Socket connect) {
        this.connect = connect;

       
    }
    
   public void run() {
       
       try {

        //Socket socketConnection = new Socket("127.0.0.1", 11111);
        Socket socketConnection = connect;

  } catch (Exception e) {System.out.println(e); }
       
          
     
      
    }
    
}
    

