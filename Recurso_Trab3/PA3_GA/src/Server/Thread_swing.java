/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author root
 */
public class Thread_swing extends Thread {
    String str;
    DataInputStream datareceber_fromload = null;
    InputStream receber_fromload;
    JTextArea req_exe_swing;
    
    public Thread_swing(String str, InputStream receber_fromload, JTextArea req_exe_swing) {
        this.str = str;
        this.receber_fromload = receber_fromload;
        this.req_exe_swing = req_exe_swing;

    }

    public void run() {
        datareceber_fromload = new DataInputStream(receber_fromload);
        while (true) {
            try {
                str = datareceber_fromload.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Server_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            req_exe_swing.setText(str);

        }
    }
}
