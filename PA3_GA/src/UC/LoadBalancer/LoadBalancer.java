package UC.LoadBalancer;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;


public class LoadBalancer extends javax.swing.JFrame {
    private int numero_clientes = 0;
    private int numberOfServers = 0;
    private int client_port;
    private int serverPortServer;
    private int PortMonitor;
    private Socket SocketClient;
    private Socket serverSocketMonitor;
    HashMap<Integer, Socket> allServerSocketsConnected = new HashMap<>();
    HashMap<Integer, ThreadLB_ReceberRequest> allServerReceiverThreads = new HashMap<>();
    HashMap<Integer, Socket> all_clientessocket_conectados = new HashMap<>();

    //HashTable que contem os requests de cada servidor
    HashMap<Integer, ArrayList> allRequestsOnEachServer = new HashMap<>();

    public LoadBalancer() throws IOException {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Button_Connect = new javax.swing.JButton();
        Client_Port_Swing = new javax.swing.JTextField();
        Servidor_Port_Swing = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Servidores_connect_Swing = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        Client_Connect_Swing = new javax.swing.JTextArea();
        Monitor_Port_Swing = new javax.swing.JTextField();
        nServers = new javax.swing.JLabel();
        numero_Clients_Swing = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ONLINE_SWING = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOAD_BALANCER");

        Button_Connect.setBackground(new java.awt.Color(255, 255, 255));
        Button_Connect.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        Button_Connect.setText("Connect");
        Button_Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ConnectActionPerformed(evt);
            }
        });

        Client_Port_Swing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Client_Port_Swing.setText("7777");
        Client_Port_Swing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Client_Port_SwingActionPerformed(evt);
            }
        });

        Servidor_Port_Swing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Servidor_Port_Swing.setText("8888");
        Servidor_Port_Swing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Servidor_Port_SwingActionPerformed(evt);
            }
        });

        Servidores_connect_Swing.setColumns(20);
        Servidores_connect_Swing.setRows(5);
        jScrollPane2.setViewportView(Servidores_connect_Swing);

        Client_Connect_Swing.setColumns(20);
        Client_Connect_Swing.setRows(5);
        jScrollPane4.setViewportView(Client_Connect_Swing);

        Monitor_Port_Swing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Monitor_Port_Swing.setText("6666");

        nServers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nServers.setText("0");

        numero_Clients_Swing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numero_Clients_Swing.setText("0");

        jLabel6.setText("Porta Cliente");

        jLabel1.setText("Porta Servidor");

        jLabel2.setText("Porta Monitor");

        jLabel3.setText("Servidores Conectados");

        jLabel7.setText("Clientes Conectados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Servidor_Port_Swing, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Client_Port_Swing, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Monitor_Port_Swing, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(numero_Clients_Swing, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(nServers, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(160, 160, 160)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Button_Connect, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(ONLINE_SWING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 59, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Client_Port_Swing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Servidor_Port_Swing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(36, 36, 36))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(Monitor_Port_Swing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nServers)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(Button_Connect)
                        .addGap(32, 32, 32)
                        .addComponent(ONLINE_SWING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numero_Clients_Swing)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void Button_ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ConnectActionPerformed

            client_port = parseInt(Client_Port_Swing.getText());
            serverPortServer = parseInt(Servidor_Port_Swing.getText());
            PortMonitor = parseInt(Monitor_Port_Swing.getText());
         
            //SERVIDOR
        SwingWorker Receive_from_server = new SwingWorker<Boolean, Integer>() {
            
            @Override
            protected Boolean doInBackground() throws Exception {
                ONLINE_SWING.setVisible(false);
                
                    ServerSocket serverSocket = null;
                    try {
                        serverSocket = new ServerSocket(serverPortServer);
                    } catch (IOException ex) {
                        Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    while (true) {
                        Socket s2 = null;
                        try {
                            s2 = serverSocket.accept();
                        } catch (IOException ex) {
                            Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                            System.exit(1);
                        }


                        InputStream inputStream2 = null;
                        try {
                            inputStream2 = s2.getInputStream();
                        } catch (IOException ex) {
                            Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        DataInputStream dataInputStream2 = new DataInputStream(inputStream2);
                        OutputStream outputStream = s2.getOutputStream();
                        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                        try {
                            String str = dataInputStream2.readUTF();
                            if ("Servidorvivo".equals(str)) {
                                System.out.println("mandar id do servidor->" + numberOfServers);
                                dataOutputStream.writeUTF(String.valueOf(numberOfServers) + ";Server");
                                dataOutputStream.flush();
                                //Salvar os novos servidores
                                allServerSocketsConnected.put(numberOfServers, s2);
                                allRequestsOnEachServer.put(numberOfServers, new ArrayList<String>());
                                ThreadLB_ReceberRequest receiveRequests = new ThreadLB_ReceberRequest(all_clientessocket_conectados, s2, allRequestsOnEachServer);
                                receiveRequests.start();
           
                                StringBuilder text_swing = new StringBuilder();
                                allServerSocketsConnected.keySet().forEach(key -> {
                                    text_swing.append("Servidor ID:").append(key).append(" = ").append(allServerSocketsConnected.get(key)).append("\n");
                                });
                                Servidores_connect_Swing.setText(text_swing.toString());
                                numberOfServers++;
                                nServers.setText(String.valueOf(numberOfServers));
                            } 

                        } catch (IOException ex) {
                            Logger.getLogger(LoadBalancer.class.getName()).log(Level.INFO, null, ex);
                            try {
                                s2.close();
                            } catch (IOException ex1) {
                                Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        }
                    }
                
            }


        };
        Receive_from_server.execute();

        //CLIENT
        SwingWorker Logic_LoadClient = new SwingWorker<Boolean, Integer>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                {
                    ServerSocket serverSocket = null;
                    try {
                        serverSocket = new ServerSocket(client_port);
                    } catch (IOException ex) {
                        Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ONLINE_SWING.setForeground(new java.awt.Color(0, 100, 0));
                    ONLINE_SWING.setText("ONLINE!");
                    ONLINE_SWING.setVisible(true);
                    while (true) {
                        Socket s2 = null;
                        try {
                            s2 = serverSocket.accept();
                            SocketClient = s2;
                        } catch (IOException ex) {
                            Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                            System.exit(1);
                        }

                        InputStream inputStream2 = null;
                        try {
                            inputStream2 = s2.getInputStream();
                        } catch (IOException ex) {
                            Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        DataInputStream dataInputStream2 = new DataInputStream(inputStream2);
                        OutputStream outputStream = s2.getOutputStream();
                        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                        try {
                            String str = dataInputStream2.readUTF();
                            if ("Clientevivo!".equals(str)) {
                                System.out.println("mandar id do cliente->" + numero_clientes);
          
                                dataOutputStream.writeUTF(String.valueOf(numero_clientes) + ";Client");
                                all_clientessocket_conectados.put(numero_clientes, s2);
                                StringBuilder text_swing = new StringBuilder();
                                all_clientessocket_conectados.keySet().forEach(key -> {
                                    text_swing.append("Client ID:").append(key).append(" = ").append(all_clientessocket_conectados.get(key)).append("\n");
                                });
                                Client_Connect_Swing.setText(text_swing.toString());
                                numero_clientes++;
                                numero_Clients_Swing.setText(String.valueOf(numero_clientes));
                            } else {
                                ThreadLB_Request loadBalancerRequest = new ThreadLB_Request(str, allServerSocketsConnected, serverSocketMonitor, all_clientessocket_conectados, allRequestsOnEachServer, 9999999);
                                loadBalancerRequest.start();
                            }

                        } catch (IOException ex) {
                            Logger.getLogger(LoadBalancer.class.getName()).log(Level.INFO, null, ex);
                            
                            try {
                                s2.close();

                            } catch (IOException ex1) {
                                Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        }
                    }
                }
            }

        };
        Logic_LoadClient.execute();

        //MONITOR
        SwingWorker Logic_Monitor = new SwingWorker<Boolean, Integer>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                {
                    ServerSocket serverSocket = null;
                    try {
                        serverSocket = new ServerSocket(PortMonitor);
                    } catch (IOException ex) {
                        Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }

                    while (true) {
                        Socket s2 = null;
                        try {
                            s2 = serverSocket.accept();
                            if (serverSocketMonitor == null) {
                                serverSocketMonitor = s2;
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                            System.exit(1);
                        }

                        InputStream inputStream2 = null;
                        try {
                            inputStream2 = s2.getInputStream();
                        } catch (IOException ex) {
                            Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        DataInputStream dataInputStream2 = new DataInputStream(inputStream2);
                        OutputStream outputStream = s2.getOutputStream();
                        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                        try {
                            String str = dataInputStream2.readUTF();
                            //se o monitor se quiser ligar
                            if ("monitorvivo".equals(str)) {
                                dataOutputStream.writeUTF("999;Monitor");

                            }

                        } catch (IOException ex) {
                            Logger.getLogger(LoadBalancer.class.getName()).log(Level.INFO, null, ex);
                            try {
                                s2.close();
                            } catch (IOException ex1) {
                                Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        }
                    }
                }
            }
        };
        Logic_Monitor.execute();
    }//GEN-LAST:event_Button_ConnectActionPerformed

    private void Client_Port_SwingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Client_Port_SwingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Client_Port_SwingActionPerformed

    private void Servidor_Port_SwingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Servidor_Port_SwingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Servidor_Port_SwingActionPerformed

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
            java.util.logging.Logger.getLogger(LoadBalancer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadBalancer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadBalancer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadBalancer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new LoadBalancer().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Connect;
    private javax.swing.JTextArea Client_Connect_Swing;
    private javax.swing.JTextField Client_Port_Swing;
    private javax.swing.JTextField Monitor_Port_Swing;
    private javax.swing.JLabel ONLINE_SWING;
    private javax.swing.JTextField Servidor_Port_Swing;
    private javax.swing.JTextArea Servidores_connect_Swing;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel nServers;
    private javax.swing.JLabel numero_Clients_Swing;
    // End of variables declaration//GEN-END:variables
}
