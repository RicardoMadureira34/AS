package UC.Monitor;

import UC.LoadBalancer.ThreadLB_Request;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ThreadMonitor extends Thread {

    private final HashMap<Integer, Socket> see_serveronline;
    private final HashMap<Integer, Integer> trab_onEachThreadServer;
    private final Socket serverSocket;
    private final int Socket_lb_porta;
    private final int id;
    JTextArea server_online_swing;
    JTextArea process_forserver;
    HashMap<Integer, ArrayList> request_inservers;

    
    public ThreadMonitor(Socket s2, HashMap<Integer, Socket> see_serveronline, int id, JTextArea server_online_swing, int Socket_lb_porta, HashMap<Integer, Integer> trab_onEachThreadServer, HashMap<Integer, ArrayList> request_inservers, JTextArea process_forserver) {
        this.serverSocket = s2;
        this.Socket_lb_porta = Socket_lb_porta;
        this.id = id;
        this.see_serveronline = see_serveronline;
        this.server_online_swing = server_online_swing;
        this.trab_onEachThreadServer = trab_onEachThreadServer;
        this.request_inservers = request_inservers;
        this.process_forserver = process_forserver;
    }

    /**
    * thread 
    */
    @Override
    public void run() {

        OutputStream outputStream = null;
        try {
            outputStream = this.serverSocket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(ThreadMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        InputStream inputStream = null;
        try {
            inputStream = this.serverSocket.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(ThreadMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        try {
            while (true) {
                sleep(50);
                dataOutputStream.writeUTF("AreYouAlive?");
                dataOutputStream.flush();
                String str = dataInputStream.readUTF();
                String[] array_receive_servers = str.split(";", -2);
                String[] requests_array_receive_servers = array_receive_servers[1].split(",", -2);

                ArrayList<String> temp = new ArrayList<>();
                temp.addAll(Arrays.asList(requests_array_receive_servers));
                
                
                int position = 0;
                if (request_inservers.containsKey(id)) {
                    if (temp.equals(request_inservers.get(id))) {
                        position++;
                    } else {
                        request_inservers.replace(id, temp);
                    }
                } else {
                    request_inservers.put(id, temp);
                }

                if (position == 0) {
                    StringBuilder show_onswing = new StringBuilder();
                    request_inservers.keySet().stream().map(key -> {
                        show_onswing.append("Server ID:").append(key).append(" = ");
                        return key;
                    }).map(key -> {
                        for (int i = 0; i < request_inservers.get(key).size(); i++) {
                            show_onswing.append(request_inservers.get(key).get(i).toString())
                                    .append("-");
                        }
                        return key;
                    }).forEachOrdered(_item -> {
                        show_onswing.append("\n");
                    });
                    process_forserver.setText(show_onswing.toString());
                }

                if (!trab_onEachThreadServer.containsKey(id)) {
                    trab_onEachThreadServer.put(id, parseInt(array_receive_servers[0]));
                } else {
                    trab_onEachThreadServer.replace(id, parseInt(array_receive_servers[0]));
                }
            }

        } catch (IOException ex) {
            this.see_serveronline.remove(id);
            this.request_inservers.remove(id);
            this.trab_onEachThreadServer.remove(id);
            StringBuilder newTextArea = new StringBuilder();
            see_serveronline.keySet().forEach(key -> {
                newTextArea.append("Server ID:").append(key).append(" = ").append(see_serveronline.get(key)).append("\n");
            });
            server_online_swing.setText(newTextArea.toString()); //ver servers online

    }   catch (InterruptedException ex) {
            Logger.getLogger(ThreadMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
}
