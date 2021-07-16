/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Integer.min;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author root
 */
public class ThreadServer extends Thread {

    Socket socketserver;
    int id_servidor;
    int min = 0;
    StringBuilder mostrar_str_final = new StringBuilder();
    ArrayList<String> mostrar_array_final = new ArrayList<>();
    Set<String> set = new LinkedHashSet<>(mostrar_array_final);
    JTextArea req_pen; //SWING
    JTextArea req_EXECUTADOS; //SWING
    HashMap<String, Integer> controlar_dealine = new HashMap<>();
    HashMap<Integer, DataHolder.Data> controlar_req = new HashMap<>();
    HashMap<Integer, DataHolder.Data> req = new HashMap<>();
    HashMap<String, Integer> entry = new HashMap<>();
    ArrayList<String> exe_array = new ArrayList<>();
    ArrayList<Integer> exe_array2 = new ArrayList<>();
    int count2;

    public ThreadServer(HashMap<Integer, DataHolder.Data> controlar_req, Socket socketserver, int id_servidor, int count2, JTextArea req_EXECUTADOS) {
        this.socketserver = socketserver;
        this.id_servidor = id_servidor;
        this.count2 = count2;
        this.controlar_req = controlar_req;
        this.req_EXECUTADOS = req_EXECUTADOS;

    }

    public void print(HashMap<Integer, DataHolder.Data> example) {
        example.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }

    public void run() {

        try {
            req_EXECUTADOS.append("skhfakjdskajdhajsh");
            System.out.println("Print1 -> ");
            print(controlar_req);

            String[] process = controlar_req.get(count2).key.split("[|]", 0);
            System.out.println("Process -> " + Arrays.toString(process));

            String str_processed = new String();
            int deadline = Integer.parseInt(process[6]);
            int niter = Integer.parseInt(process[4]);

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
            mostrar_array_final.clear();
            mostrar_array_final.addAll(set);
            mostrar_array_final.add(processed.toString());
            for (int i = 0; i < mostrar_array_final.size(); i++) {
                System.out.println("exe: " + mostrar_array_final.get(i));
                mostrar_str_final.append("Request-").append(mostrar_array_final.get(i)).append("\n");
            }
            req_EXECUTADOS.setText(mostrar_str_final.toString());

            DataOutputStream data_enviarforclient = new DataOutputStream(socketserver.getOutputStream());
            data_enviarforclient.writeUTF(processed.toString());
            data_enviarforclient.flush();
            System.out.println("ACORDOU E ENVIOU!");

            // Remove from queue
            controlar_req.remove(count2);
            System.out.println("Print2 ->");
            print(controlar_req);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
