package UC.LoadBalancer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadLB_ReceberRequest extends Thread {

    private final HashMap<Integer, Socket> socket_client_connected;
    private final HashMap<Integer, ArrayList> request_fromserver;
    private final Socket so;
    private String str;
    

    public ThreadLB_ReceberRequest(HashMap<Integer, Socket> socket_client_connected, Socket so, HashMap<Integer, ArrayList> request_fromserver) {
        this.socket_client_connected = socket_client_connected;
        this.so = so;
        this.request_fromserver = request_fromserver;
    }

  
    @Override
    public void run() {
        //ler do servidor
        while (true) {
            try {
                
                DataInputStream dataInputStream = new DataInputStream(so.getInputStream());
                str = dataInputStream.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(ThreadLB_ReceberRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        String[] process_str = str.split("[|]", -2);

            //enviar para cliente
            OutputStream outputStreamClient = null;
            try {
                outputStreamClient = socket_client_connected.get(parseInt(process_str[0])).getOutputStream();
            } catch (IOException ex) {
                Logger.getLogger(ThreadLB_ReceberRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
            DataOutputStream dataOutputStreamClient = new DataOutputStream(outputStreamClient);
            try {
                dataOutputStreamClient.writeUTF(str);
            } catch (IOException ex) {
                Logger.getLogger(ThreadLB_ReceberRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
