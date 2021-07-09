/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadBalancer;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author root
 */
public class Enviar_paraServidor extends Thread{
    String request_fromCLient;
    HashMap<Integer, Socket> server_connect= new HashMap<>();
    HashMap<Integer, Socket> clientes_connect = new HashMap<>();
    HashMap<Integer, ArrayList> req_cadaservidor;
    
    
    public Enviar_paraServidor(String request_fromCLient, HashMap<Integer, Socket> server_connect,
    HashMap<Integer, Socket> clientes_connect, HashMap<Integer, ArrayList> req_cadaservidor){
        this.request_fromCLient = request_fromCLient;
        this.server_connect = server_connect;
        this.clientes_connect = clientes_connect;
        this.req_cadaservidor = req_cadaservidor;
        
        
    }
    
    public void run(){
        
        
        
        
    }
    
}
