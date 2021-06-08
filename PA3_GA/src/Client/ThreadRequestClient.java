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
    DataInputStream infromClient;
    DataOutputStream outToServer;
    String ser=new String();
    String SQL=new String();
    JTextField request;
    JTextField mostrarrequest;
    JButton enviarrequest;
    public String input;
    private Scanner scan;
    int requeste_id = 0;
    JTextField ni;
    public ThreadRequestClient(Socket connect, JTextField mostrarrequest, JTextField ni) {
        this.connect = connect;
        this.request = request;
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
                infromClient = new DataInputStream(socketConnection.getInputStream());
                outToServer = new DataOutputStream(socketConnection.getOutputStream());
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadRequestClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                    ser = infromClient.readUTF();
                    int id = Integer.parseInt(ser);
                    requeste_id = (id * 1000);
                    requeste_id++;
                    //System.out.println("quantos request2: ");
                    outToServer.writeUTF("" + ser + "|" + "" + requeste_id + "|00" + "|01" + "|" + "" + ni.getText() +  "|" + "0" + "|");
                    System.out.println(("" + ser + "|" + "" + requeste_id + "|00" + "|01" + "|" + "" + ni.getText() + "|" + "0" + "|"));
                    mostrarrequest.setText(("" + ser + "|" + "" + requeste_id + "|00" + "|01" + "|" + "" + ni.getText() + "|" + "0" + "|"));
                    String exe_load = infromClient.readUTF();
                    System.out.println("from server: " + exe_load);
                    
                } catch (IOException ex) {
                    Logger.getLogger(ThreadRequestClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
                
    }
    }

        
                
        
    


    
    

