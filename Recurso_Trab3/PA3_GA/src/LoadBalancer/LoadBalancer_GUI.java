/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadBalancer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author omp
 */
public class LoadBalancer_GUI extends javax.swing.JFrame {
    int port_forclient=0;
    int port_formonitor=0;
    int port_forserver=0;
    int id_client = 0;
    ArrayList<String> list = new ArrayList<String>();
    HashMap<Integer, Socket> hash_clientes_connect = new HashMap<>();
    HashMap<Integer, Socket> allServerSocketsConnected = new HashMap<>();
    HashMap<Integer, Socket> all_clientessocket_conectados = new HashMap<>();
    HashMap<Integer, ArrayList> allRequestsOnEachServer = new HashMap<>();
    

    /**
     * Creates new form LoadBalancer_GUI
     */
    public LoadBalancer_GUI()  {
        initComponents();
        
        
        
        
        
    }
    public void Start_LoadBalancer(){
        
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        numberport = new javax.swing.JTextField();
        StartButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        port_monito = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Port_Server_Swing = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOAD_BALANCER");

        jLabel2.setText("Port_Client:");

        numberport.setText("11111");
        numberport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberportActionPerformed(evt);
            }
        });

        StartButton.setText("Start");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Port_Monitor:");

        port_monito.setText("11112");
        port_monito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                port_monitoActionPerformed(evt);
            }
        });

        jLabel4.setText("Port_Server");

        Port_Server_Swing.setText("11113");
        Port_Server_Swing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Port_Server_SwingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(port_monito, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(Port_Server_Swing))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                        .addComponent(StartButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numberport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StartButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(port_monito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Port_Server_Swing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(207, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numberportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberportActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed

          ServerSocket ss_forServer = null;
          ServerSocket ss_forclient = null;
          InputStream inputfromclient = null;
          OutputStream outforclient = null;
          
                  
          
        try {
            int counter = 0;
            
            
            port_forclient = Integer.parseInt(numberport.getText());
            port_formonitor = Integer.parseInt(port_monito.getText());
            port_forserver = Integer.parseInt(Port_Server_Swing.getText());
            ServerSocket ss_formonitor=new ServerSocket(port_formonitor);
            Socket s_formonitor=ss_formonitor.accept();
            ss_forServer=new ServerSocket(port_forserver);
            ss_forclient=new ServerSocket(port_forclient);
            
            
            
            
            
            while(true){
                try {
                    Connect_servidor connect_server = new Connect_servidor(ss_forServer, allServerSocketsConnected, allRequestsOnEachServer);
                    connect_server.start();
                    //ligacao cliente-load
                    Socket s_forclient=ss_forclient.accept();
                    inputfromclient = s_forclient.getInputStream();
                    outforclient = s_forclient.getOutputStream();
                    DataInputStream data_inputfromclient = new DataInputStream(inputfromclient);
                    DataOutputStream data_outforclient = new DataOutputStream(outforclient);
                    String mensagem_client = data_inputfromclient.readUTF();
                    if("cliente".equals(mensagem_client)){
                        System.out.println("enviar id para client " + id_client);
                        data_outforclient.writeUTF(String.valueOf(id_client));
                        all_clientessocket_conectados.put(id_client, s_forclient);
                        id_client++;
                    }else{
                        String request = data_inputfromclient.readUTF();
                        Enviar_paraServidor send_server = new Enviar_paraServidor(request, allServerSocketsConnected,
                        all_clientessocket_conectados, allRequestsOnEachServer);
                        //send_server.start();
                    }
                    
                    ThreadLoadBalancer t= new ThreadLoadBalancer(s_forclient,s_formonitor);
                    t.start();
                    
                    ss_formonitor.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoadBalancer_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }   } catch (IOException ex) {
            Logger.getLogger(LoadBalancer_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ss_forclient.close();
            ss_forServer.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadBalancer_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }//GEN-LAST:event_StartButtonActionPerformed
    
    private void port_monitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_port_monitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_port_monitoActionPerformed

    private void Port_Server_SwingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Port_Server_SwingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Port_Server_SwingActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException, InterruptedException {
        LoadBalancer_GUI exe = new LoadBalancer_GUI();
                    exe.setVisible(true);
        
                        
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
            java.util.logging.Logger.getLogger(LoadBalancer_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadBalancer_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadBalancer_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadBalancer_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Port_Server_Swing;
    private javax.swing.JButton StartButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField numberport;
    private javax.swing.JTextField port_monito;
    // End of variables declaration//GEN-END:variables
}
