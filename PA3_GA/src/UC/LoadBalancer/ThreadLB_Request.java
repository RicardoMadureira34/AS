package UC.LoadBalancer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadLB_Request extends Thread {

    private Socket SOCKET_PORT;
    private final Socket socket_Monitor;
    private final String request_cliente;
    private final int ser_crash;
    HashMap<Integer, Socket> clientes_connect;
    HashMap<Integer, Socket> server_connect;
    HashMap<Integer, ArrayList> req_cadaservidor;

    public ThreadLB_Request(String request_cliente, HashMap<Integer, Socket> server_connect, Socket serverSocketMonitor, HashMap<Integer, Socket> clientes_connect, HashMap<Integer, ArrayList> req_cadaservidor, int ser_crash) {
        this.request_cliente = request_cliente;
        this.server_connect = server_connect;
        this.socket_Monitor = serverSocketMonitor;
        this.clientes_connect = clientes_connect;
        this.req_cadaservidor = req_cadaservidor;
        this.ser_crash = ser_crash;
    }
    @Override
    public void run() {
        try {
            DataOutputStream dataOutputStream4 = new DataOutputStream(this.socket_Monitor.getOutputStream());
            dataOutputStream4.writeUTF("precisa de informaçao");
            DataInputStream dataInputStream4 = new DataInputStream(this.socket_Monitor.getInputStream());
            String infoFromMonitor = dataInputStream4.readUTF();
            int servidor_menortrab = 0;
            int menortrab = 5;
            int server_full = 0;
            //Split info servidores
            String[] rec = infoFromMonitor.split("[|]", -2);

           //o que o thread deve fazer para escolher o server a quem mandar request
            if (ser_crash == 9999999) {
                for (String arrOfStr1 : rec) {
                    String[] arrOfStrData = arrOfStr1.split(";", -2);
                    if (arrOfStrData.length == 1) {
                        break;
                    }
                    if (parseInt(arrOfStrData[1]) <= menortrab) {
                        menortrab = parseInt(arrOfStrData[1]);
                        servidor_menortrab = parseInt(arrOfStrData[0]);
                        if (parseInt(arrOfStrData[1]) == 5) {
                            server_full++;
                        }
                    }
                }
                //Se os servidores tiverem cheios
                
                if (server_full == server_connect.size()) {
                    Integer[] keys = server_connect.keySet().toArray(new Integer[server_connect.size()]);
                    int key = keys[new Random().nextInt(keys.length)];
                    servidor_menortrab = key;
                }
            } else {
                servidor_menortrab = ser_crash;
            }
           
            req_cadaservidor.get(servidor_menortrab).add(request_cliente);

            OutputStream outputStream = null;
            try {
                outputStream = this.server_connect.get(servidor_menortrab).getOutputStream();
            } catch (IOException ex) {
                Logger.getLogger(ThreadLB_Request.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Enviar request para um server
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(request_cliente);
            dataOutputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(ThreadLB_Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
