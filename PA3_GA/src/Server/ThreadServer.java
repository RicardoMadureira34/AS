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
Socket connect;
    DataInputStream infromClient;
    DataOutputStream outToServer;
    String ser=new String();
    String SQL=new String();
    public String input;
    JTextField re;
    ArrayList<String> list = new ArrayList<String>();
    public ThreadServer(Socket connect, JTextField re) {
        this.connect = connect;
        this.re = re;

       
    }
    
    public void run(){
                    Socket socketConnection = connect;
                    //System.out.println("quantos request: ");
                    System.out.println("connect from LoadServer");
                    //input = "";
                    //scan = new Scanner(System.in);
                    
            try {
                infromClient = new DataInputStream(socketConnection.getInputStream());
                outToServer = new DataOutputStream(socketConnection.getOutputStream());
                
            } catch (IOException ex) {

            }
 
            try {
                    while(true){
                    ser = infromClient.readUTF();
                    System.out.println("" + ser);
                    re.setText("" + ser);
                    StringTokenizer tokenizer = new StringTokenizer(ser, "|");
                    while (tokenizer.hasMoreElements()) {
                        list.add(tokenizer.nextToken());
                    }
                    String NA = " 6.02214076 x 10²³";
                    String element = list.get(4);
                    //String element2 = list.get(5);
                    //System.out.println("NI: " + element);
                    //if (element.equals("1")){
                        String ni1 = "NA: 6.0 x 10²³";
                        String ne = ser.replace(list.get(5), ni1);
                        System.out.println("exe: " + ne);
                        outToServer.writeUTF(ne);
                       
                    //}
                    list.clear();
                    }
                    

                    
                } catch (IOException ex) {

                }
            
            
            
                
    }
    
}
