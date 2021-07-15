/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ConnectMonitor extends Thread {
    Socket socketMonitor;
    OutputStream enviar_forMonitor = null;
    int id_servidor;
    
    public ConnectMonitor(Socket socketMonitor, int id_servidor){
        this.socketMonitor = socketMonitor;
        this.id_servidor = id_servidor;
        
    }
    
    public void run(){
        try {
            enviar_forMonitor = socketMonitor.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(ConnectMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataOutputStream dataenviar_forMonitor = new DataOutputStream(enviar_forMonitor);
        try {
            dataenviar_forMonitor.writeUTF(String.valueOf(id_servidor));
            dataenviar_forMonitor.flush();
        } catch (IOException ex) {
            Logger.getLogger(ConnectMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
    }
    
}
