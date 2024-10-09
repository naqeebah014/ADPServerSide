/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serverside;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author Engetelo
 */
public class ServerSideGUI extends JFrame {

    private JPanel pnlSouth, pnlCenter;
    private JButton btnClose, btncheck;
    private JTextArea logtxt;

    public ServerSideGUI() {

        pnlSouth = new JPanel();
        pnlCenter = new JPanel();

        btnClose = new JButton("Close");
        btncheck = new JButton("Check");

        logtxt = new JTextArea(4, 4);

        setGUI();

    }

    public void setGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setVisible(true);
        
        setLayout(new FlowLayout());
        pnlSouth.setLayout(new FlowLayout());
        pnlSouth.add(btnClose);
        pnlSouth.add(btncheck);

        pnlCenter.add(logtxt);
        

    }
    
    

}
