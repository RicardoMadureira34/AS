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
    String request;
    JTextField re;
    ArrayList<String> list = new ArrayList<String>();
    int counter = 0;
    public ThreadServer(String request, JTextField re, int counter) {
        this.request = request;
        this.re = re;
        this.counter = counter;
       
    }
    
    public void run(){
                    System.out.println("server");

 

                System.out.println("request vindo do load: " + request);


            
            
                
    }
    
}
