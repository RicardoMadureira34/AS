/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadBalancer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Connect_servidor extends Thread {
    ServerSocket ss_forServer;
    HashMap<Integer, Socket> allServerSocketsConnected = new HashMap<>();
    HashMap<Integer, ArrayList> allRequestsOnEachServer = new HashMap<>();
    
    
    public Connect_servidor(ServerSocket ss_forServer, HashMap<Integer, Socket> allServerSocketsConnected,  HashMap<Integer, ArrayList> allRequestsOnEachServer){
        this.ss_forServer = ss_forServer;
        this.allServerSocketsConnected = allServerSocketsConnected;
        this.allRequestsOnEachServer = allRequestsOnEachServer;
    }
    
    public void run(){
        InputStream inputfromservidor = null;
        OutputStream outforservidor = null;
        int id_servidor = 0;
        
        while(true){
            try {
                //ligacao load-servidor
                Socket s_forServer = ss_forServer.accept();
                inputfromservidor = s_forServer.getInputStream();
                outforservidor = s_forServer.getOutputStream();
                DataInputStream data_inputfromservidor = new DataInputStream(inputfromservidor);
                DataOutputStream data_outforservidor = new DataOutputStream(outforservidor);
                String mensagem_servidor = data_inputfromservidor.readUTF();
                if("servidor".equals(mensagem_servidor)){
                    System.out.println("enviar id para servidor " + id_servidor);
                    data_outforservidor.writeUTF(String.valueOf(id_servidor));
                    allServerSocketsConnected.put(id_servidor, s_forServer);
                    allRequestsOnEachServer.put(id_servidor, new ArrayList<String>());
                    id_servidor++;
                }
            } catch (IOException ex) {
                Logger.getLogger(Connect_servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
    
    
}
