/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Monitor.ThreadMonitor;
import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author root
 */
public class ThreadServer extends Thread {
    Socket socketserver;
    int id_servidor;
    
    public ThreadServer(Socket socketserver, int id_servidor) {
        this.socketserver = socketserver;
        this.id_servidor = id_servidor;
       
    }
    
    public void run(){

        while(true){
        
        try {
            //System.out.println("asdnahdkasnd");
            
            InputStream receber_fromload = null;
            receber_fromload = socketserver.getInputStream();
            
            DataInputStream datareceber_fromload = new DataInputStream(receber_fromload);
            String requestfromload = datareceber_fromload.readUTF();
            System.out.println("from load: " + requestfromload);
            String[] process = requestfromload.split("[|]", 0);
//            for (String a : process)
//                System.out.println("process" + a);
            String str_processed = new String();
            int deadline = Integer.parseInt(process[6]);
            int niter = Integer.parseInt(process[4]);
            System.out.println("niter: " + niter);
            System.out.println("deadline: " + deadline);
            String pi = "3.1415926589793";
            if (niter < 14) {
                str_processed = pi.substring(0, 2 + niter);
                //System.out.println("str_processed" + str_processed);
            }
            
            
            StringBuilder processed = new StringBuilder();
            processed.append(process[0]).append("|").append(process[1]).append("|").append(id_servidor).append("|")
            .append("02").append("|").append(process[4]).append("|").append(str_processed).append("|").append(deadline)
            .append("|");
            System.out.println("process: " + processed.toString());
        
            try {
                sleep(5000 * niter);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(ThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            
            DataOutputStream data_enviarforclient = new DataOutputStream(socketserver.getOutputStream());
            data_enviarforclient.writeUTF(processed.toString());
            data_enviarforclient.flush();
            System.out.println("ACORDOU E ENVIOU!");
                
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }   
    }
        



            
            
                
    }
    
}
