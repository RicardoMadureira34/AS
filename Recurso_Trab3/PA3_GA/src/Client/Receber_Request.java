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
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author root
 */
public class Receber_Request extends Thread {
    Socket receive;
    String id;
    JTextArea mostrarrequest_final;
    StringBuilder mostrar_str_final = new StringBuilder();
    ArrayList<String> mostrar_array_final = new ArrayList<>();
    String receber_str = new String();
    HashMap<String, String> swing = new HashMap<>();
    DataInputStream data_fromserver = null;
    Set<String> set = new LinkedHashSet<>(mostrar_array_final);
    
    public Receber_Request(String id, Socket receive, JTextArea mostrarrequest_final){
        this.receive = receive; 
        this.mostrarrequest_final = mostrarrequest_final;
        this.id = id;
        
    }
    
    public void run (){
        while (true){
            try {
                data_fromserver = new DataInputStream(receive.getInputStream());
           
            } catch (IOException ex) {
                Logger.getLogger(Receber_Request.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                receber_str = data_fromserver.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Receber_Request.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("receber request final: " + receber_str);
            
            mostrar_array_final.clear();
            mostrar_array_final.addAll(set);
            mostrar_array_final.add(receber_str);
            for(int i = 0; i < mostrar_array_final.size(); i++){
                System.out.println("exe: " + mostrar_array_final.get(i));
                mostrar_str_final.append("Request-").append(mostrar_array_final.get(i)).append("\n");         
            }
            mostrarrequest_final.setText(mostrar_str_final.toString());
            
            
            
        }
        
    }
}
