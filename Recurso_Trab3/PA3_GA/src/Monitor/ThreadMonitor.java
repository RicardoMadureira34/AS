/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author root
 */
public class ThreadMonitor extends Thread {
    JLabel teste;
    Socket connect;
    DataInputStream fromLoadBalancer;
    String ser=new String();
    JTextField pen_request;
    
    
    public ThreadMonitor(Socket connect, JTextField pen_request) {
        this.connect = connect;
        this.pen_request = pen_request;
        
        
       
    }
    
    
    
    
    public void run(){
        System.out.println("connect from LoadMOnitor");
        
        
        try {
            fromLoadBalancer = new DataInputStream(connect.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while (true){
            ser = fromLoadBalancer.readUTF();
            //System.out.println("connect from LoadMOnitor2");
            System.out.println("" + ser);
            pen_request.setText("" + ser);
            }
            
            //System.out.println(("id: " + ser + "|" + "request: " + "|00" + "|00(estado)" + "|N"));
        } catch (IOException ex) {
            Logger.getLogger(ThreadMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        
        
        
    }
    
}
