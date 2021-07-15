/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author root
 */
public class ThreadMonitor extends Thread {

    Socket socketfromload;
    StringBuilder mostrar_str_final = new StringBuilder();
    ArrayList<String> mostrar_array_final = new ArrayList<>();
    Set<String> set = new LinkedHashSet<>(mostrar_array_final);
    DataInputStream fromLoadBalancer;
    String ser = new String();
    JTextArea req_pen;

    public ThreadMonitor(Socket socketfromload, JTextArea req_pen) {
        this.socketfromload = socketfromload;
        this.req_pen = req_pen;

    }

    public void run() {
        System.out.println("connect from LoadMOnitor");

        while (true) {
            try {
                fromLoadBalancer = new DataInputStream(socketfromload.getInputStream());

            } catch (IOException ex) {
                Logger.getLogger(ThreadMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ser = fromLoadBalancer.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Monitor_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            //System.out.println("ser : " + ser);
            mostrar_array_final.clear();
            mostrar_array_final.addAll(set);
            mostrar_array_final.add(ser);
            for (int i = 0; i < mostrar_array_final.size(); i++) {
                System.out.println("exe: " + mostrar_array_final.get(i));
                mostrar_str_final.append("Request-").append(mostrar_array_final.get(i)).append("\n");
            }
            req_pen.setText(mostrar_str_final.toString());

        }
    }
}
