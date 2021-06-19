package UC.Server;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

public class Server extends javax.swing.JFrame {
    private int id = 9999999;
    private int portId_server;
    private int monitorPortId;
    private int contar_request;
    private final ArrayList<String> queue;
    HashMap<Integer, String> controlar_request = new HashMap<>();
    private Socket connectedSocket;
    private Socket s = null;
    private OutputStream enviar_LB;
    private final ArrayList<String> request_executados = new ArrayList<>();
    private final ArrayList<String> requeste_iniciais = new ArrayList<>();

    public Server() throws IOException {
        this.queue = new ArrayList<>();
        initComponents();
        STATUSLabel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startButton = new javax.swing.JButton();
        Label = new javax.swing.JLabel();
        Port_server_swing = new javax.swing.JTextField();
        STATUSLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Requeste_recebidos_swing = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        requeste_executado_swing = new javax.swing.JTextArea();
        monitorPort = new javax.swing.JTextField();
        ID_server_swing = new javax.swing.JLabel();
        queue_sizeswing = new javax.swing.JLabel();
        controlar_reqswing = new javax.swing.JLabel();
        cont_req_recebidos_swing = new javax.swing.JLabel();
        cont_reqexecutados_swing = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SERVER");

        startButton.setBackground(new java.awt.Color(255, 255, 255));
        startButton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        startButton.setText("Connect");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        Port_server_swing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Port_server_swing.setText("8888");

        STATUSLabel.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        STATUSLabel.setForeground(new java.awt.Color(0, 100, 0));
        STATUSLabel.setText("ONLINE!");

        Requeste_recebidos_swing.setColumns(20);
        Requeste_recebidos_swing.setRows(5);
        jScrollPane1.setViewportView(Requeste_recebidos_swing);

        requeste_executado_swing.setColumns(20);
        requeste_executado_swing.setRows(5);
        jScrollPane2.setViewportView(requeste_executado_swing);

        monitorPort.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        monitorPort.setText("1111");

        ID_server_swing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ID_server_swing.setText("Waiting");

        queue_sizeswing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        queue_sizeswing.setText("0");

        controlar_reqswing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        controlar_reqswing.setText("0");

        cont_req_recebidos_swing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cont_req_recebidos_swing.setText("0");

        cont_reqexecutados_swing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cont_reqexecutados_swing.setText("0");

        jLabel1.setText("Id_Server");

        jLabel9.setText("Porta Server");

        jLabel10.setText("Porta Monitor");

        jLabel11.setText("Request Recebidos");

        jLabel12.setText("Request Processados");

        jLabel2.setText("Tamanho_Queue");

        jLabel3.setText("Controlar Request");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(monitorPort, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Port_server_swing, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(controlar_reqswing, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(queue_sizeswing, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(ID_server_swing))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cont_req_recebidos_swing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Label, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(STATUSLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cont_reqexecutados_swing, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ID_server_swing)
                            .addComponent(jLabel1))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(queue_sizeswing)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(controlar_reqswing)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(STATUSLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton)
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(Port_server_swing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(monitorPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(Label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cont_req_recebidos_swing)
                            .addComponent(cont_reqexecutados_swing)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed


        //Thread that comunicates with the LoadBalancer
        SwingWorker Logic_server = new SwingWorker<Boolean, Integer>() {

            @Override
            public Boolean doInBackground() throws Exception {
                portId_server = parseInt(Port_server_swing.getText());
                //monitor
                try {
                    s = new Socket("localhost", portId_server);

                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                STATUSLabel.setForeground(new java.awt.Color(0, 100, 0));
                STATUSLabel.setText("ONLINE!");
                STATUSLabel.setVisible(true);
                connectedSocket = s;

                // get the output stream from the socket.
                OutputStream outputStream = null;
                try {
                    outputStream = s.getOutputStream();
                    enviar_LB = outputStream;
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                InputStream inputStream = null;
                try {
                    inputStream = s.getInputStream();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                // create a data output stream from the output stream so we can send data through it
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                String request = "Servidorvivo";
                System.out.println("Sending Servidorvivo para LoadBalancer");

                try {
                    dataOutputStream.writeUTF(request);
                    dataOutputStream.flush();
                    String str = dataInputStream.readUTF();
                    String[] arrOfStr = str.split(";", -2);
                    if (!arrOfStr[1].equals("Server")) {
                        connectedSocket = null;
                        return false;
                    }
                    id = parseInt(arrOfStr[0]);
                    System.out.println("My Id is->" + id);
                    ID_server_swing.setText(arrOfStr[0]);
                } catch (IOException e) {
                }
                while (true) {
                    String requestInfo = dataInputStream.readUTF();
                    requeste_iniciais.add(requestInfo);
                    //ATULIZAR A GUI
                    StringBuilder newTextArea = new StringBuilder();
                for (int i = 0; i < requeste_iniciais.size(); i++) {
                    newTextArea.append("Request-")
                            .append(requeste_iniciais.get(i))
                            .append("\n");
                }
                Requeste_recebidos_swing.setText(newTextArea.toString());
                cont_req_recebidos_swing.setText(String.valueOf(requeste_iniciais.size()));
 
                    
                    if (controlar_request.size() < 3) {
                        //Process Request
                        contar_request++;
                        controlar_request.put(contar_request, requestInfo);
                        //ATUALIZAR GUI
                        queue_sizeswing.setText(String.valueOf(queue.size()));
                        controlar_reqswing.setText(String.valueOf(controlar_request.size()));
                 
                        ServerRequest serverRequest = new ServerRequest(requestInfo, portId_server, controlar_request, contar_request, enviar_LB, 0, id, requeste_executado_swing, request_executados, cont_reqexecutados_swing);
                        serverRequest.start();
                    } else if (queue.size() < 2) {
                        //Queue
                        queue.add(requestInfo);
                        //ATUALIZAR GUI
                        queue_sizeswing.setText(String.valueOf(queue.size()));
                        controlar_reqswing.setText(String.valueOf(controlar_request.size()));
                        
                    } else {
                        //Vamos rejeitar
                        String[] val = requestInfo.split("[|]", 0);
                        System.out.println(val.length);
                        String rejectedRequest = val[0] + "|" + val[1] + "|" + String.valueOf(0) + id + "|" + String.valueOf(0) + String.valueOf(3) + "|" + val[4] + "|" + val[5] + "|";
                        try {
                            dataOutputStream.writeUTF(rejectedRequest);
                        } catch (IOException ex) {
                            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        };
        Logic_server.execute();

        //Monitor Thread to comunicate with the Monitor
        SwingWorker worker2 = new SwingWorker<Boolean, Integer>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                monitorPortId = parseInt(monitorPort.getText());;
                //monitor
                Socket s2;
                try {
                    s2 = new Socket("localhost", monitorPortId);

                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                STATUSLabel.setForeground(new java.awt.Color(0, 100, 0));
                STATUSLabel.setText("ONLINE!");
                STATUSLabel.setVisible(true);

                // get the output stream from the socket.
                OutputStream outputStream = null;
                try {
                    outputStream = s2.getOutputStream();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                InputStream inputStream = null;
                try {
                    inputStream = s2.getInputStream();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                String request = "Monitorvivo";

                try {
                    while (true) {
                        if (id != 9999999) {
                            //make sure there is an ip on this server, otherwise just wait
                            break;
                        }
                    }
                    dataOutputStream.writeUTF(request + ";" + String.valueOf(id));
                    dataOutputStream.flush();
                    String str = dataInputStream.readUTF();
                    System.out.println(str + monitorPortId);
                    if (!"MonitorAc".equals(str)) {
                        connectedSocket = null;
                        return false;
                    }
                } catch (IOException e) {
                }
                while (true) {
                    //Sending Response to Monitor
                    String requestInfo = dataInputStream.readUTF();
                    StringBuilder sendInfoToMonitor = new StringBuilder();
                    controlar_request.keySet().forEach(key -> {
                        sendInfoToMonitor
                                .append(controlar_request.get(key))
                                .append(",");
                    });
                    System.out.println(sendInfoToMonitor);
                    dataOutputStream.writeUTF(String.valueOf(controlar_request.size() + queue.size()) + ";" + sendInfoToMonitor);
                }
            }
        };
        worker2.execute();

        //Thread to see when a thread finishes the work
        SwingWorker Logic_dist_req = new SwingWorker<Boolean, Integer>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                while (true) {
                    //ATUALIZAR GUI
                    queue_sizeswing.setText(String.valueOf(queue.size()));
                    controlar_reqswing.setText(String.valueOf(controlar_request.size()));
                    
                    if (controlar_request.size() < 3 && queue.size() > 0) {
                        contar_request++;
                        ServerRequest serverRequest = new ServerRequest(queue.get(0), portId_server, controlar_request, contar_request, enviar_LB, 0, id, requeste_executado_swing, request_executados, cont_reqexecutados_swing);
                        serverRequest.start();
                        controlar_request.put(contar_request, queue.get(0));
                        queue.remove(0);
                        
                        //ATUALIZAR GUI
                        queue_sizeswing.setText(String.valueOf(queue.size()));
                        controlar_reqswing.setText(String.valueOf(controlar_request.size()));
                    }
                }
            }
        };
        Logic_dist_req.execute();
    }//GEN-LAST:event_startButtonActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Server().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID_server_swing;
    private javax.swing.JLabel Label;
    private javax.swing.JTextField Port_server_swing;
    private javax.swing.JTextArea Requeste_recebidos_swing;
    private javax.swing.JLabel STATUSLabel;
    private javax.swing.JLabel cont_req_recebidos_swing;
    private javax.swing.JLabel cont_reqexecutados_swing;
    private javax.swing.JLabel controlar_reqswing;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField monitorPort;
    private javax.swing.JLabel queue_sizeswing;
    private javax.swing.JTextArea requeste_executado_swing;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
