package com.mycompany.serverside;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Engetelo
 */
public class ServerSideGUI extends JFrame implements ActionListener {

    private JPanel pnlSouth, pnlCenter;
    private JButton btnClose, btnCheck;
    private JTextArea logtxt;

    ServerSide sr = new ServerSide();

    public ServerSideGUI() {
        super("Server side");

        pnlSouth = new JPanel();
        pnlCenter = new JPanel();

        btnClose = new JButton("Close");
        btnCheck = new JButton("Check");
        
        
        logtxt = new JTextArea(10, 30);
        
        
        setGUI();
        
    }

    public void setGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        setLayout(new BorderLayout());

        
        pnlCenter.setLayout(new BorderLayout());
        pnlCenter.add(new JScrollPane(logtxt), BorderLayout.CENTER);

        pnlSouth.setLayout(new GridLayout(1, 2));
        pnlSouth.add(btnClose);
        pnlSouth.add(btnCheck);

        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);

        this.setVisible(true);
        btnClose.addActionListener(this);
        btnCheck.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    
    
    }

    public static void main(String[] args) {
        new ServerSideGUI();
    }
}
