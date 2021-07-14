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
import static java.lang.Integer.min;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
    int min = 0;
    //String req;
    HashMap<String, Integer> controlar_dealine = new HashMap<>();
    HashMap<Integer, DataHolder.Data> controlar_req = new HashMap<>();
    HashMap<Integer, DataHolder.Data> req = new HashMap<>();
    HashMap<String, Integer> entry = new HashMap<>();
    ArrayList<String> exe_array = new ArrayList<>();
    ArrayList<Integer> exe_array2 = new ArrayList<>();
    int count;

    static class Data {

        String key;
        Integer value;

        public Data(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Data{"
                    + "key='" + key + '\''
                    + ", value=" + value
                    + '}';
        }
    }

    public ThreadServer(HashMap<Integer, DataHolder.Data> req, Socket socketserver, int id_servidor, int count, HashMap<Integer, DataHolder.Data> controlar_req) {
        this.socketserver = socketserver;
            this.id_servidor = id_servidor;
        this.req = req;
        this.count = count;
        this.controlar_req = controlar_req;

    }

    public void run() {

        try {

//                InputStream receber_fromload = null;
//                receber_fromload = socketserver.getInputStream();
//
//                DataInputStream datareceber_fromload = new DataInputStream(receber_fromload);
//                String requestfromload = datareceber_fromload.readUTF();
            //exe_array.add(requestfromload);
            
            //String[] process = req.values([1]);
            
            
            String[] process = req.split("[|]", 0);
//                System.out.println("from load: " + requestfromload);
//
//                String[] process = requestfromload.split("[|]", 0);
//                for (int i = 0; i < exe_array.size(); i++) {
//                    String[] process2 = exe_array.get(i).split("[|]", 0);
//                    int deadline2 = Integer.parseInt(process2[6]);
//                    exe_array2.add(deadline2);
//                    min = Collections.min(exe_array2);
//
//                }
//            for (String a : process)
//                System.out.println("process" + a);
            String str_processed = new String();
            int deadline = Integer.parseInt(process[6]);
            int niter = Integer.parseInt(process[4]);
            System.out.println("niter: " + niter);
            System.out.println("deadline: " + deadline);
            //controlar_dealine.put(requestfromload, deadline);
            //System.out.println("haspmap: " + controlar_dealine);
            //Integer minValue = controlar_dealine.entrySet().stream().min(Map.Entry.comparingByValue()).get().getValue();
            //System.out.println("minvalue" + minValue);

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
            System.out.println("ACORDOU E ENVIOU!");

            DataOutputStream data_enviarforclient = new DataOutputStream(socketserver.getOutputStream());
            data_enviarforclient.writeUTF(processed.toString());
            data_enviarforclient.flush();
            controlar_req.remove(count);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
