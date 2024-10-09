/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serverside;

import java.net.*;
import java.io.*;

/**
 *
 * @author naqee
 */
public class ServerConnection {

    private ServerSocket listener;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket client;
    private String myMsg;
    private String response = "";

    public ServerConnection() {
        try {
            listener = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Constructor ");
            e.printStackTrace();
        }

    }

    private void getStreams() throws ClassNotFoundException, IOException {
        in = new ObjectInputStream(client.getInputStream());
        out = new ObjectOutputStream(client.getOutputStream());
    }

    private void processClient() throws ClassNotFoundException{
         do {
            try {
                response = (String) in.readObject();
                sendData(response.toUpperCase());
            } catch (IOException e) {

            }
        } while ("Exit".equalsIgnoreCase(response));
        System.out.println("terminating communication");
    }

    private void sendData(String myMsg) {
        try {
            out.writeObject(myMsg);

        } catch (IOException e) {

        }
    }
    private void listenForClients() throws ClassNotFoundException {
        try {
            client = listener.accept();
            getStreams();
            processClient();

        } catch (IOException e) {

        }

    }
}
