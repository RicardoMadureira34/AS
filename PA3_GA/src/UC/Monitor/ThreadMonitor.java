package UC.Monitor;

import UC.LoadBalancer.LoadBalancerRequest;
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
    private final HashMap<Integer, Integer> numberOfWorkLoadEachThreadServer;
    private final Socket serverSocket;
    private final int Socket_lb_porta;
    private final int id;
    JTextArea server_online_swing;
    JTextArea process_forserver;
    HashMap<Integer, ArrayList> request_inservers;

    
    public ThreadMonitor(Socket s2, HashMap<Integer, Socket> see_serveronline, int id, JTextArea server_online_swing, int Socket_lb_porta, HashMap<Integer, Integer> numberOfWorkLoadEachThreadServer, HashMap<Integer, ArrayList> request_inservers, JTextArea process_forserver) {
        this.serverSocket = s2;
        this.Socket_lb_porta = Socket_lb_porta;
        this.id = id;
        this.see_serveronline = see_serveronline;
        this.server_online_swing = server_online_swing;
        this.numberOfWorkLoadEachThreadServer = numberOfWorkLoadEachThreadServer;
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
                String[] arrOfStr = str.split(";", -2);
                String[] requestsArrOfStr = arrOfStr[1].split(",", -2);

                ArrayList<String> temp = new ArrayList<>();
                temp.addAll(Arrays.asList(requestsArrOfStr));
                int flagRepetition = 0;
                if (request_inservers.containsKey(id)) {
                    if (temp.equals(request_inservers.get(id))) {
                        flagRepetition++;
                    } else {
                        request_inservers.replace(id, temp);
                    }
                } else {
                    request_inservers.put(id, temp);
                }

                if (flagRepetition == 0) {
                    StringBuilder newTextArea3 = new StringBuilder();
                    request_inservers.keySet().stream().map(key -> {
                        newTextArea3.append("Server ID:")
                                .append(key)
                                .append(" = ");
                        return key;
                    }).map(key -> {
                        for (int i = 0; i < request_inservers.get(key).size(); i++) {
                            newTextArea3.append(request_inservers.get(key).get(i).toString())
                                    .append("-");
                        }
                        return key;
                    }).forEachOrdered(_item -> {
                        newTextArea3.append("\n");
                    });
                    process_forserver.setText(newTextArea3.toString());
                }

                if (!numberOfWorkLoadEachThreadServer.containsKey(id)) {
                    numberOfWorkLoadEachThreadServer.put(id, parseInt(arrOfStr[0]));
                } else {
                    numberOfWorkLoadEachThreadServer.replace(id, parseInt(arrOfStr[0]));
                }
            }

        } catch (IOException ex) {
            this.see_serveronline.remove(id);
            this.request_inservers.remove(id);
            this.numberOfWorkLoadEachThreadServer.remove(id);
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
