/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author root
 */
public class Receber_Request extends Thread {
    Socket receive;
   
    JTextArea mostrarrequest_final;
    StringBuilder mostrar_str_final = new StringBuilder();
    ArrayList<String> mostrar_array_final = new ArrayList<>();
    
    public Receber_Request(Socket receive, JTextArea mostrarrequest_final){
        this.receive = receive; 
        this.mostrarrequest_final = mostrarrequest_final;
        
    }
    
    public void run (){
        while (true){
            try {
                DataInputStream data_fromserver = new DataInputStream(receive.getInputStream());
                String receber_str = new String();
                receber_str = data_fromserver.readUTF();
                mostrar_array_final.add(receber_str);
                System.out.println("receber request final: " + receber_str);
                
                for(int i = 0; i < mostrar_array_final.size(); i++){
                    System.out.println("array: " + mostrar_array_final.get(i));
                    mostrar_str_final.append("Request Final: ").append(mostrar_array_final.get(i)).append("\n");
                        
                    }
                //mostrarrequest_final.append(mostrar_str_final.toString());
                mostrarrequest_final.setText(mostrar_str_final.toString());
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(Receber_Request.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
}
