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
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class ThreadLoadBalancer extends Thread{
    private Socket s_forclient=null;
    private Socket s_formonitor=null;
    DataInputStream infromClient;
    HashMap<Integer, String> map = new HashMap<>();
    DataOutputStream forclient;
    DataOutputStream formonitor;
    OutputStream for_servidor = null;
    
    int counter = 0;
    JTextField exe1;
    ArrayList<String> list = new ArrayList<String>();
    String SQL=new String();

public ThreadLoadBalancer(Socket s_forclient, Socket s_formonitor){
    this.s_forclient=s_forclient;
    this.s_formonitor=s_formonitor;
    
}

public void run(){  
    int counter_server = 0;
    
    
        try {
            infromClient = new DataInputStream(s_forclient.getInputStream());
            forclient = new DataOutputStream(s_forclient.getOutputStream());
            formonitor = new DataOutputStream(s_formonitor.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadLoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
        }
    while(true){
        try {
            //ler do cliente
            String str_client = infromClient.readUTF();
            System.out.println("request_client: "+ str_client);
            //-----------------------------
            //enviar para o servidor
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadLoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    
    
    } 
    
    
   }

}
