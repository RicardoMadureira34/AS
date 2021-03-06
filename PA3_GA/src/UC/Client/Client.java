package UC.Client;

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
import java.util.regex.Pattern;
import javax.swing.SwingWorker;

public class Client extends javax.swing.JFrame {

    
    private int id;
    private int portId;
    private int numero_request_rejeitados;
    private Socket connectedSocket;
    private boolean bool_forwhile = true;
    HashMap<Integer, String> Requests_pendentes;
    HashMap<Integer, String> Requests_executados;
    ArrayList<String> RequestsList_forallexecuted = new ArrayList<>();
    public Client() throws IOException {
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

        StartBut = new javax.swing.JButton();
        Id_Client = new javax.swing.JLabel();
        Portfor_client = new javax.swing.JTextField();
        Requeste_Button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Request_executados_swing = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Request_pendent_Swing = new javax.swing.JTextArea();
        niter_swing = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ONLINE_SWING = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CLIENT");

        StartBut.setBackground(java.awt.Color.white);
        StartBut.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        StartBut.setText("Start");
        StartBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButActionPerformed(evt);
            }
        });

        Id_Client.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Id_Client.setText("Waiting");

        Portfor_client.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Portfor_client.setText("7777");

        Requeste_Button.setBackground(new java.awt.Color(255, 255, 255));
        Requeste_Button.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        Requeste_Button.setText("Request");
        Requeste_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Requeste_ButtonActionPerformed(evt);
            }
        });

        Request_executados_swing.setColumns(20);
        Request_executados_swing.setRows(5);
        jScrollPane1.setViewportView(Request_executados_swing);

        Request_pendent_Swing.setColumns(20);
        Request_pendent_Swing.setRows(5);
        jScrollPane2.setViewportView(Request_pendent_Swing);

        niter_swing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        niter_swing.setText("4");

        jLabel1.setText("Request Pendentes");

        jLabel4.setText("Requets executados");

        jLabel5.setText("Port");

        jLabel7.setText("Niter");

        jLabel3.setText("ID_Cliente");

        ONLINE_SWING.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(Id_Client))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(niter_swing, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 33, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Portfor_client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ONLINE_SWING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(StartBut, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Requeste_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(StartBut))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Portfor_client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(ONLINE_SWING)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Id_Client)
                    .addComponent(jLabel3))
                .addGap(8, 8, 8)
                .addComponent(Requeste_Button)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(niter_swing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void Requeste_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Requeste_ButtonActionPerformed
        int numero_request = 0;

        int niter = Integer.parseInt(niter_swing.getText());
            Send_Request clientRequest = new Send_Request(numero_request, this.id, niter, portId, Requests_pendentes, Request_pendent_Swing);
            clientRequest.start();

        numero_request++;
    }//GEN-LAST:event_Requeste_ButtonActionPerformed

    private void StartButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButActionPerformed

        
        class Logic_Client extends Thread{
            
            public void run(){
                ONLINE_SWING.setVisible(false);
                portId = parseInt(Portfor_client.getText());
                Socket s = null;
                try {
                    s = new Socket("localhost", portId);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    connectedSocket = null;
                }
                
                ONLINE_SWING.setForeground(new java.awt.Color(0, 100, 0));
                ONLINE_SWING.setText("ONLINE!");
                ONLINE_SWING.setVisible(true);
                connectedSocket = s;
                Requests_pendentes = new HashMap<>(); 
                Requests_executados = new HashMap<>();
                OutputStream outputStream = null;
                try {
                    outputStream = s.getOutputStream();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                InputStream inputStream = null;
                try {
                    inputStream = s.getInputStream();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                String mensagem_aviso = "Clientevivo!";

                try {
                    dataOutputStream.writeUTF(mensagem_aviso);
                    dataOutputStream.flush();
                    String str = dataInputStream.readUTF();
                    String[] string_rec = str.split(";", -2);
                    if (!string_rec[1].equals("Client")) {
                        connectedSocket = null;
                    }
                    id = parseInt(string_rec[0]);
                    Id_Client.setText(String.valueOf(id));
                } catch (IOException e) {
                }

                bool_forwhile = true;

                while (bool_forwhile) {
                    try {
                        //Receber Requests!
                        String request_executados = dataInputStream.readUTF();
                        String[] rec = request_executados.split(Pattern.quote("|"), -2);
                        Requests_pendentes.remove(parseInt(rec[1]));
                        System.out.println(Requests_pendentes.toString());
                        RequestsList_forallexecuted.add(request_executados);
                        Requests_executados.put(parseInt(rec[1]), request_executados);
                        if (!Requests_pendentes.isEmpty()) {
                            StringBuilder texte_swing = new StringBuilder();
                            Requests_pendentes.keySet().forEach(key -> {
                                texte_swing.append("Request-").append(key).append(" : ").append(Requests_pendentes.get(key)).append("\n");
                            });
                            
                            Request_pendent_Swing.setText(texte_swing.toString());
                        } else {
                            Request_pendent_Swing.setText("");
                        }
                        StringBuilder texte_swing2 = new StringBuilder();
                        for (int i = 0; i < RequestsList_forallexecuted.size(); i++) {
                            texte_swing2.append("Request-").append(RequestsList_forallexecuted.get(i)+"\n");
                        }
                        
                        Request_executados_swing.setText(texte_swing2.toString());
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
            
            }
            
            
        } 
            Logic_Client c = new Logic_Client();
            c.start();
        
    }//GEN-LAST:event_StartButActionPerformed

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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Client().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Id_Client;
    private javax.swing.JLabel ONLINE_SWING;
    private javax.swing.JTextField Portfor_client;
    private javax.swing.JTextArea Request_executados_swing;
    private javax.swing.JTextArea Request_pendent_Swing;
    private javax.swing.JButton Requeste_Button;
    private javax.swing.JButton StartBut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField niter_swing;
    // End of variables declaration//GEN-END:variables
}
