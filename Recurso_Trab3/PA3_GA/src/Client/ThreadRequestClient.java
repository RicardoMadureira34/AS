/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author root
 */
public class ThreadRequestClient extends Thread {
    Socket connect;
   
    DataOutputStream outToServer;
    DataOutputStream infromClient2;
    String ser=new String();
    String SQL=new String();
    JTextField request;
    JTextField mostrarrequest;
    JButton enviarrequest;
    public String input;
    private Scanner scan;
    HashMap<Integer, String> requeste_pendentes = new HashMap<>();
    int requeste_id;
    
    JTextField ni;
    public ThreadRequestClient(String ser, Socket connect, int requeste_id, HashMap<Integer, String> requeste_pendentes, JTextField mostrarrequest, JTextField ni) {
        this.ser=ser;
        this.connect = connect;
        this.requeste_id = requeste_id;
        this.requeste_pendentes = requeste_pendentes;
        this.mostrarrequest = mostrarrequest;
        this.enviarrequest = enviarrequest;
        this.ni = ni;

       
    }
    
    public void run(){
        Socket socketConnection = connect;
        
                    
                    
                    //System.out.println("quantos request: ");
                    //input = "";
                    //scan = new Scanner(System.in);      
                
            try {
                
                outToServer = new DataOutputStream(socketConnection.getOutputStream());
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadRequestClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {    
                
                
                int id = Integer.parseInt(ser);
                String str_forrequest = "" + ser + "|" + "" + requeste_id + "|00" + "|01" + "|" + "" + ni.getText() +  "|" + "0" + "|"; 
                requeste_pendentes.put(requeste_id, str_forrequest);
                
                outToServer.writeUTF(str_forrequest);
                outToServer.flush();
                System.out.println(("" + ser + "|" + "" + requeste_id + "|00" + "|01" + "|" + "" + ni.getText() + "|" + "0" + "|"));
                mostrarrequest.setText(("" + ser + "|" + "" + requeste_id + "|00" + "|01" + "|" + "" + ni.getText() + "|" + "0" + "|"));
                    
                } catch (IOException ex) {
                    Logger.getLogger(ThreadRequestClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
    
    
    }

        
                
        
    


    
    

