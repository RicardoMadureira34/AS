package UC.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ServerRequest extends Thread {

    private final String request;
    private final int SOCKET_PORT;
    private Socket connectedSocket;
    private final int position_request;
    HashMap<Integer, String> controlar_request;
    OutputStream outputStream;
    private final int processo_Rejeitado;
    private final int serverId;
    private final JTextArea requeste_executado_swing;
    private final JLabel cont_reqexecutados_swing;
    private final ArrayList<String> request_executados;

    public ServerRequest(String request, int socketPort, HashMap<Integer, String> controlar_request, int position_request, OutputStream outputStream, int processo_Rejeitado, int serverId, JTextArea requeste_executado_swing, ArrayList<String> request_executados, JLabel cont_reqexecutados_swing) {
        this.SOCKET_PORT = socketPort;
        this.request = request;
        this.controlar_request = controlar_request;
        this.position_request = position_request;
        this.outputStream = outputStream;
        this.processo_Rejeitado = processo_Rejeitado;
        this.serverId = serverId;
        this.request_executados = request_executados;
        this.requeste_executado_swing = requeste_executado_swing;
        this.cont_reqexecutados_swing = cont_reqexecutados_swing;
    }

    @Override
    public void run() {
        OutputStream outputStream = null;
        outputStream = this.outputStream;

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        try {
            String[] val = request.split("[|]", 0);
            StringBuilder r = new StringBuilder();
            StringBuilder sb = new StringBuilder();

            String select;
            int niter = Integer.parseInt(val[4]);
            String na = "6.02214076";
            if (niter < 9) {
                select = na.substring(0, 2 + niter);
                sb.append(select).append(" x 10^23");
            } else {
                StringBuilder sup = new StringBuilder();
                sup.append(na);
                int count = 23;
                for (int j = 8; j < niter; j++) {
                    sup.append("0");
                }
                sb.append(sup.toString()).append(" x 10^").append(String.valueOf(count));
            }
            
            r.append(val[0]).append("|").append(val[1]).append("|").append(serverId).append("|").append("02|").append(val[4]).append("|").append(sb.toString()).append("|");
            sleep(5000 * niter);
            dataOutputStream.writeUTF(r.toString());
            dataOutputStream.flush();
            request_executados.add(r.toString());
            //ATUALIZAR GUI
            StringBuilder text_swing = new StringBuilder();
        for (int i = 0; i < request_executados.size(); i++) {
            text_swing.append("Request-").append(request_executados.get(i)).append("\n");
        }
        requeste_executado_swing.setText(text_swing.toString());
        cont_reqexecutados_swing.setText(String.valueOf(request_executados.size()));
            
            controlar_request.remove(position_request);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
