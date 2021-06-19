package UC.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Send_Request extends Thread {

    private final int socketPort;
    private final int id_cliente;
    private final int id_requeste;
    private final int iteracoes;
    private Socket connectedSocket;
    HashMap<Integer, String> requeste_pendentes;
    JTextArea pendentes_swing;

    public Send_Request(int id_requeste, int id_cliente, int iteracoes, int socketPort, HashMap<Integer, String> requeste_pendentes, JTextArea pendentes_swing) {
        this.id_requeste = id_requeste;
        this.id_cliente = id_cliente;
        this.socketPort = socketPort;
        this.iteracoes = iteracoes;
        this.requeste_pendentes = requeste_pendentes;
        this.pendentes_swing = pendentes_swing;
    }
    
    @Override
    public void run() {
        try {
            this.connectedSocket = new Socket("localhost", socketPort);
        } catch (IOException ex) {
            Logger.getLogger(Send_Request.class.getName()).log(Level.SEVERE, null, ex);
        }
        OutputStream outputStream = null;
        try {
            outputStream = connectedSocket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(Send_Request.class.getName()).log(Level.SEVERE, null, ex);
        }

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        String str =  String.valueOf(id_cliente) + "|" + String.valueOf(this.id_requeste) + "|00|01|" + String.valueOf(this.iteracoes) + "|0|";
        requeste_pendentes.put(id_requeste, str);
        StringBuilder String_mod = new StringBuilder(); //criar strings modificaveis
        requeste_pendentes.keySet().forEach(key -> {
            String_mod.append("Request-").append(key).append(" : ").append(requeste_pendentes.get(key)) //valor da nossa key
                    .append("\n");
        });
        pendentes_swing.setText(String_mod.toString());
        try {
            dataOutputStream.writeUTF(str);
            dataOutputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(Send_Request.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
