/*LOADBALANCER
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadBalancer;

/**
 *
 * @author root
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class ThreadLoadBalancer extends Thread{
    private Socket s_forclient=null;
    private Socket s_formonitor=null;
    private Socket s_forServer=null;
    DataInputStream infromClient;
    DataInputStream fromServer;
    DataOutputStream forclient;
    DataOutputStream formonitor;
    DataOutputStream forServer;
    int counter = 0;
    JTextField exe1;

public ThreadLoadBalancer(Socket s_forclient, Socket s_formonitor, Socket s_forServer, int counter){
    this.s_forclient=s_forclient;
    this.s_forServer=s_forServer;
    this.s_formonitor=s_formonitor;
    this.counter = counter;
    
}

public void run(){  
    
    
        try {
            infromClient = new DataInputStream(s_forclient.getInputStream());
            forclient = new DataOutputStream(s_forclient.getOutputStream());
            formonitor = new DataOutputStream(s_formonitor.getOutputStream());
            forServer = new DataOutputStream(s_forServer.getOutputStream());
            fromServer = new DataInputStream(s_forServer.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadLoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
        }

        
     
    
    while(true){
        String SQL=new String();
    try {
        
        String ser="";
        forclient.writeUTF(ser + counter);
        SQL = infromClient.readUTF();
        System.out.println("" + SQL); 
        formonitor.writeUTF(SQL);
        forServer.writeUTF(SQL);
        String exe_from_server = new String();
        exe_from_server = fromServer.readUTF();
        System.out.println("exe_from_server: " + exe_from_server);
        String exe_for_client = new String();
        forclient.writeUTF("" + exe_from_server);
        
    } catch (IOException ex) {

    }
    
    }
    
    
    
    
    
    
    
    
    
    
    

    
   } 



}
