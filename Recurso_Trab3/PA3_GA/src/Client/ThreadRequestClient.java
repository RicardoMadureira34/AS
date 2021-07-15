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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author root
 */
public class ThreadRequestClient extends Thread {

    Socket connect;
    StringBuilder mostrar_str = new StringBuilder();
    DataOutputStream outToServer;
    DataOutputStream infromClient2;
    String ser = new String();
    String SQL = new String();
    JTextField request;
    JTextArea mostrarrequest;
    JButton enviarrequest;
    public String input;
    private Scanner scan;
    HashMap<Integer, String> requeste_pendentes = new HashMap<>();
    int requeste_id;
    int deadline;
    ArrayList<String> mostrar_str_array = new ArrayList<>();
    JTextField ni;

    public ThreadRequestClient(String ser, int deadline, Socket connect, int requeste_id, HashMap<Integer, String> requeste_pendentes, JTextArea mostrarrequest, JTextField ni) {
        this.ser = ser;
        this.deadline = deadline;
        this.connect = connect;
        this.requeste_id = requeste_id;
        this.requeste_pendentes = requeste_pendentes;
        this.mostrarrequest = mostrarrequest;
        this.enviarrequest = enviarrequest;
        this.ni = ni;

    }

    public void run() {
        Socket socketConnection = connect;

        try {

            outToServer = new DataOutputStream(socketConnection.getOutputStream());

        } catch (IOException ex) {
            Logger.getLogger(ThreadRequestClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            int id = Integer.parseInt(ser);
            String str_forrequest = "" + ser + "|" + "" + requeste_id + "|00" + "|01" + "|" + "" + ni.getText() + "|" + "00" + "|" + deadline;
            requeste_pendentes.put(requeste_id, str_forrequest);
            outToServer.writeUTF(str_forrequest);
            outToServer.flush();
            System.out.println(("" + ser + "|" + "" + requeste_id + "|00" + "|01" + "|" + "" + ni.getText() + "|" + "00" + "|" + deadline));
            mostrar_str_array.add(str_forrequest);

            //mostrarrequest.setText(("" + ser + "|" + "" + requeste_id + "|00" + "|01" + "|" + "" + ni.getText() +  "|" + "00" + "|" + deadline));
            for (int i = 0; i < mostrar_str_array.size(); i++) {
                mostrar_str.append("Request: ").append(mostrar_str_array.get(i)).append("\n");

            }
            mostrarrequest.append(mostrar_str.toString());

        } catch (IOException ex) {
            Logger.getLogger(ThreadRequestClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
