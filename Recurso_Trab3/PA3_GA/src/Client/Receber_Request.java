/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Receber_Request extends Thread {
    Socket receive;
    String receber_str;
    
    public Receber_Request(Socket receive){
        this.receive = receive; 
        
    }
    
    public void run (){
        while (true){
            try {
                DataInputStream data_fromserver = new DataInputStream(receive.getInputStream());
                receber_str = data_fromserver.readUTF();
                System.out.println("receber request final: " + receber_str);
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(Receber_Request.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
}
