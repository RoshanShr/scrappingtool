/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.scrappingTool.ui.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sagar
 */
public class GrabberProcess extends Thread {

    private JTextArea txtBrowser;
    ;
    private JTextField txtURL;

    public GrabberProcess(JTextArea txtBrowser, JTextField txtURL) {
        this.txtBrowser = txtBrowser;
        this.txtURL = txtURL;

    }

    @Override
    public void run() {
        try {
            URL url = new URL(txtURL.getText());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\r\n");
            }
            reader.close();
            String output = content.toString();
            txtBrowser.setText(output);

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
