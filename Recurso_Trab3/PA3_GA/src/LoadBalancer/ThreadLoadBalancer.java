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
import static java.lang.Integer.parseInt;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;




public class ThreadLoadBalancer extends Thread{
    private Socket s_forclient=null;
    private Socket s_formonitor=null;
    private Socket s_forServer;
    DataInputStream infromClient;
    HashMap<Integer, String> map = new HashMap<>();
    DataOutputStream forclient;
    DataOutputStream formonitor;
    OutputStream for_servidor = null;
    int counter = 0;
    JTextField exe1;
    ArrayList<String> list = new ArrayList<String>();
    String SQL=new String();
    HashMap<Integer, Socket> allServerSocketsConnected = new HashMap<>();
    HashMap<Integer, ArrayList> allRequestsOnEachServer = new HashMap<>();
    HashMap<Integer, Socket> all_clientessocket_conectados = new HashMap<>();

public ThreadLoadBalancer(Socket s_forclient, Socket s_formonitor, Socket s_forServer,
        HashMap<Integer, Socket> all_clientessocket_conectados,
        HashMap<Integer, Socket> allServerSocketsConnected,  
        HashMap<Integer, ArrayList> allRequestsOnEachServer){
    this.s_forclient=s_forclient;
    this.s_formonitor=s_formonitor;
    this.s_forServer = s_forServer;
    this.all_clientessocket_conectados = all_clientessocket_conectados;
    this.allServerSocketsConnected = allServerSocketsConnected;
    this.allRequestsOnEachServer = allRequestsOnEachServer;
    
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
        int serverfull = 0;
        try {
            //ler do cliente
            String str_client = infromClient.readUTF();
            System.out.println("request_client: "+ str_client);
            formonitor.writeUTF(str_client);
            formonitor.flush();
            OutputStream outputStream = null;
            if (serverfull == allServerSocketsConnected.size()) {
                    Integer[] keys = allServerSocketsConnected.keySet().toArray(new Integer[allServerSocketsConnected.size()]);
                    int key = keys[new Random().nextInt(keys.length)];
            }
            outputStream = allServerSocketsConnected.get(serverfull).getOutputStream();
            
            //Enviar request para um server
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(str_client);
            dataOutputStream.flush();
            //-----------------------------
            //enviar para o client
            Receber_request.execute();
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadLoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    
    
    } 
    
    
   }

//class Receber_request extends Thread{
//    String str_fromserver;
//    
//    public void Receber_request(){
//        
//    }
//    
//    public void run(){
//        
//        while(true){
//            try {
//                DataInputStream data_fromserver = new DataInputStream(s_forServer.getInputStream());
//                str_fromserver = data_fromserver.readUTF();
//
//                String[] process_str = str_fromserver.split("[|]", -2);
//                //enviar para cliente
//                OutputStream enviar_forclient = null;
//                enviar_forclient = all_clientessocket_conectados.get(parseInt(process_str[0])).getOutputStream();
//                DataOutputStream data_forclient = new DataOutputStream(enviar_forclient);
//                System.out.println("request vindo do servidor: " + str_fromserver);
//                data_forclient.writeUTF(str_fromserver);
//
//
//
//
//                } catch (IOException ex) {
//
//                }
//
//
//            }
//        
//        
//        
//    }
//}

    SwingWorker Receber_request = new SwingWorker<Boolean, Integer>() {
                
                @Override
                protected Boolean doInBackground() throws Exception {

                String str_fromserver = null;
                String[] process_str = null;
                

                
        
                while(true){
                    try {
                        DataInputStream data_fromserver = new DataInputStream(s_forServer.getInputStream());
                        str_fromserver = data_fromserver.readUTF();
                        process_str = str_fromserver.split("[|]", -2);
                         } catch (IOException ex) {
  
                        }
                        //enviar para cliente
                        OutputStream enviar_forclient = null;
                        enviar_forclient = all_clientessocket_conectados.get(parseInt(process_str[0])).getOutputStream();
                        DataOutputStream data_forclient = new DataOutputStream(enviar_forclient);
                        System.out.println("request vindo do servidor: " + str_fromserver);
                        data_forclient.writeUTF(str_fromserver);
                    }
        
        
                }
                
            
            };
            

}

