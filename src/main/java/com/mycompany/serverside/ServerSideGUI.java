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

    private JPanel pnlSouth, pnlCenter, pnlNorth;
    private JButton btnClose, btnCheck;
    private JTextArea logtxt;
    private JLabel toplbl;

    private ServerConnection serverConnection;

    public ServerSideGUI() {
        super("Server side");

        pnlSouth = new JPanel();
        pnlCenter = new JPanel();
        pnlNorth = new JPanel();
        
        toplbl = new JLabel("Activity Log");
        btnClose = new JButton("Close");
        btnCheck = new JButton("Check");
        
        logtxt = new JTextArea(10, 30);
        
        serverConnection = new ServerConnection(); // Create the server connection instance
        
        setGUI();
    }

    public void setGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        setLayout(new BorderLayout());

        pnlNorth.setLayout(new GridLayout(1,1));
        pnlNorth.add(toplbl);
        pnlCenter.setLayout(new BorderLayout());
        pnlCenter.add(new JScrollPane(logtxt), BorderLayout.CENTER);

        pnlSouth.setLayout(new GridLayout(1, 2));
        pnlSouth.add(btnClose);
        pnlSouth.add(btnCheck);

        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
        add(pnlNorth, BorderLayout.NORTH);
        this.setVisible(true);
        
        btnClose.addActionListener(this);
        btnCheck.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnClose){
            System.out.println("Button still under development (close)");
            System.exit(0);
        } else if(e.getSource() == btnCheck){
            logtxt.append("Checking server status...\n");
         
        }
    }

    public static void main(String[] args) {
        new ServerSideGUI();
    }
}
