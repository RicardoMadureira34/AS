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
    private final int SERVERIDINCASECRASH;
    HashMap<Integer, Socket> allClientsSocketsConnected;
    HashMap<Integer, Socket> allServersSocketsConnected;
    HashMap<Integer, ArrayList> allRequestsOnEachServer;

    public ThreadLB_Request(String request_cliente, HashMap<Integer, Socket> allServersSocketsConnected, Socket serverSocketMonitor, HashMap<Integer, Socket> allClientsSocketsConnected, HashMap<Integer, ArrayList> allRequestsOnEachServer, int SERVERIDINCASECRASH) {
        this.request_cliente = request_cliente;
        this.allServersSocketsConnected = allServersSocketsConnected;
        this.socket_Monitor = serverSocketMonitor;
        this.allClientsSocketsConnected = allClientsSocketsConnected;
        this.allRequestsOnEachServer = allRequestsOnEachServer;
        this.SERVERIDINCASECRASH = SERVERIDINCASECRASH;
    }

    /**
     * thread of load balancer, created when receive infos
     */
    @Override
    public void run() {
        try {
            //Ask monitor for information!
            DataOutputStream dataOutputStream4 = new DataOutputStream(this.socket_Monitor.getOutputStream());
            dataOutputStream4.writeUTF("NeedInfoPls");
            DataInputStream dataInputStream4 = new DataInputStream(this.socket_Monitor.getInputStream());
            String infoFromMonitor = dataInputStream4.readUTF();
            int serverWithLowestWork = 0;
            int lowestWork = 5;
            int ifServersAreAllFull = 0;
            //Split Servers
            String[] arrOfStr = infoFromMonitor.split("[|]", -2);

           //o que o thread deve fazer para escolher o server a quem mandar request
            if (SERVERIDINCASECRASH == 9999999) {
                for (String arrOfStr1 : arrOfStr) {
                    String[] arrOfStrData = arrOfStr1.split(";", -2);
                    if (arrOfStrData.length == 1) {
                        break;
                    }
                    if (parseInt(arrOfStrData[1]) <= lowestWork) {
                        lowestWork = parseInt(arrOfStrData[1]);
                        serverWithLowestWork = parseInt(arrOfStrData[0]);
                        if (parseInt(arrOfStrData[1]) == 5) {
                            ifServersAreAllFull++;
                        }
                    }
                }
                //Case if all servers are FULL!
                System.out.println("\n\n---SERVERS ARE FULL->"+ifServersAreAllFull+"--"+allServersSocketsConnected.size());
                if (ifServersAreAllFull == allServersSocketsConnected.size()) {
                    Integer[] keys = allServersSocketsConnected.keySet().toArray(new Integer[allServersSocketsConnected.size()]);
                    int key = keys[new Random().nextInt(keys.length)];
                    serverWithLowestWork = key;
                    System.out.println("\n\nDENTRO->"+serverWithLowestWork+"---"+keys);
                }
                System.out.println("SERVIDOR SELECIONADO! ->" + serverWithLowestWork);
            } else {
                serverWithLowestWork = SERVERIDINCASECRASH;
            }
            //Saving in a HashTable in case of a fail
            allRequestsOnEachServer.get(serverWithLowestWork).add(request_cliente);

            OutputStream outputStream = null;
            try {
                outputStream = this.allServersSocketsConnected.get(serverWithLowestWork).getOutputStream();
            } catch (IOException ex) {
                Logger.getLogger(ThreadLB_Request.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Enviar request para um server
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(request_cliente);
            dataOutputStream.flush();
            System.out.println("LOAD_BALANCER_REQUEST_ENVIADO->" + request_cliente + SOCKET_PORT);
        } catch (IOException ex) {
            Logger.getLogger(ThreadLB_Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
