package UC.Monitor;

import UC.LoadBalancer.LoadBalancer;
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

public class Monitor extends javax.swing.JFrame {
    private int portId_load;
    private int monitorPort;
    private Socket connectedSocket;
    private Socket load_Socket;
    HashMap<Integer, Socket> servers_conectados = new HashMap<>();
    HashMap<Integer, Integer> trabload_forservers = new HashMap<>();
    HashMap<Integer, ArrayList> request_cadaservidor = new HashMap<>();

    public Monitor() throws IOException {
        initComponents();
        CONNECTIONREADYLabel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startButton = new javax.swing.JButton();
        Label = new javax.swing.JLabel();
        Port_Load = new javax.swing.JTextField();
        CONNECTIONREADYLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Serveronline_swing = new javax.swing.JTextArea();
        TEXTFIELDMONITORS = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        process_forserver_swing = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MONITOR");

        startButton.setBackground(new java.awt.Color(255, 255, 255));
        startButton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        startButton.setText("Connect");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        Port_Load.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Port_Load.setText("6666");

        CONNECTIONREADYLabel.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        CONNECTIONREADYLabel.setForeground(new java.awt.Color(0, 100, 0));
        CONNECTIONREADYLabel.setText("ONLINE!");

        Serveronline_swing.setColumns(20);
        Serveronline_swing.setRows(5);
        jScrollPane1.setViewportView(Serveronline_swing);

        TEXTFIELDMONITORS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TEXTFIELDMONITORS.setText("1111");

        process_forserver_swing.setColumns(20);
        process_forserver_swing.setRows(5);
        jScrollPane4.setViewportView(process_forserver_swing);

        jLabel1.setText("Servers Online");

        jLabel4.setText("Processamento em cada Server");

        jLabel3.setText("Port Monitor");

        jLabel6.setText("Port Load");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Label, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 12, Short.MAX_VALUE)))
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TEXTFIELDMONITORS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CONNECTIONREADYLabel)
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Port_Load, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(CONNECTIONREADYLabel)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TEXTFIELDMONITORS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Port_Load, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(Label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        //Worker that connects the monitor with the LB and replies the LB with info when LB asks
        
        class Logic_lb extends Thread{
            
        public void run(){
            portId_load = parseInt(Port_Load.getText());
                Socket s = null;
                try {
                    s = new Socket("localhost", portId_load);
                    load_Socket = s;
                } catch (IOException ex) {
                    Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                }
                CONNECTIONREADYLabel.setForeground(new java.awt.Color(0, 100, 0));
                CONNECTIONREADYLabel.setText("ONLINE!");
                CONNECTIONREADYLabel.setVisible(true);
                connectedSocket = s;

                // get the output stream from the socket.
                OutputStream outputStream = null;
                try {
                    outputStream = s.getOutputStream();

                } catch (IOException ex) {
                    Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                }
                InputStream inputStream = null;
                try {
                    inputStream = s.getInputStream();
                } catch (IOException ex) {
                    Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                }

                // create a data output stream from the output stream so we can send data through it
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                String request = "monitorvivo";

                try {
                    dataOutputStream.writeUTF(request);
                    dataOutputStream.flush();
                    
                    
                    String str = dataInputStream.readUTF();
                    
                    
                    String[] array_for_srt = str.split(";", -2);

                } catch (IOException e) {
                }
                //Waiting of LB asks for info on the state and Requests per server
                while (true) {
                try {
                    String informacao_request = dataInputStream.readUTF();
                    String info = null;

                    StringBuilder newTextArea = new StringBuilder();
                    trabload_forservers.keySet().forEach(key -> {
                        newTextArea.append("").append(key).append(";").append(trabload_forservers.get(key)).append("|");
                    });
                    dataOutputStream.writeUTF(newTextArea.toString());
                } catch (IOException ex) {
                    Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        
        
        }
        
    }
        Logic_lb lb = new Logic_lb();
        lb.start();
        
        //Thread to comunicate with Servers
        SwingWorker worker2 = new SwingWorker<Boolean, Integer>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                monitorPort = parseInt(TEXTFIELDMONITORS.getText());
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(monitorPort);
                } catch (IOException ex) {
                    Logger.getLogger(LoadBalancer.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }

                CONNECTIONREADYLabel.setForeground(new java.awt.Color(0, 100, 0));
                CONNECTIONREADYLabel.setText("ONLINE!");
                CONNECTIONREADYLabel.setVisible(true);

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

                    String str = dataInputStream2.readUTF();
                    
                    String[] enviar_forserver = str.split(";", -2);
                    
                   
                    if ("ImAliveMonitor".equals(enviar_forserver[0])) { //monitor comunica com o servidor
//                        System.out.println("servidor vivo!" + arrOfStr[1]);
                        dataOutputStream.writeUTF("MonitorAc");
                        servers_conectados.put(parseInt(enviar_forserver[1]), connectedSocket);
                        StringBuilder newTextArea = new StringBuilder();
                        servers_conectados.keySet().forEach(key -> {
                            newTextArea.append("Server ID:").append(key).append(" = ").append(servers_conectados.get(key)).append("\n");
                        });
                        Serveronline_swing.setText(newTextArea.toString());
                        ThreadMonitor tm = new ThreadMonitor(s2, servers_conectados, parseInt(enviar_forserver[1]), Serveronline_swing, portId_load, trabload_forservers,request_cadaservidor, process_forserver_swing);
                        tm.start();
                    }
                }
            }
        };
        worker2.execute();
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
            java.util.logging.Logger.getLogger(Monitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Monitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Monitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Monitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Monitor().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CONNECTIONREADYLabel;
    private javax.swing.JLabel Label;
    private javax.swing.JTextField Port_Load;
    private javax.swing.JTextArea Serveronline_swing;
    private javax.swing.JTextField TEXTFIELDMONITORS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea process_forserver_swing;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
