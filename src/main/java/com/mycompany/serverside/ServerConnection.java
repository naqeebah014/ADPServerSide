package com.mycompany.serverside;

import java.net.*;
import java.io.*;

public class ServerConnection implements Runnable {

    private ServerSocket listener;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket client;
    private String response = "";

    public ServerConnection() {
        System.out.println("i had to start it all on a new thread to avoid a infinite loop before the connection is established");
    }

    private void getStreams() throws ClassNotFoundException, IOException {
        in = new ObjectInputStream(client.getInputStream());
        out = new ObjectOutputStream(client.getOutputStream());
    }

    private void processClient() throws ClassNotFoundException {
        try {
            do {
                response = (String) in.readObject();
                if (!"Exit".equalsIgnoreCase(response)) {
                    sendData(response.toUpperCase());
                }
            } while (!"Exit".equalsIgnoreCase(response));
        } catch (IOException e) {
            System.out.println("Error on ProcessC,lient");
            e.printStackTrace();
        } finally {
            closeConnections(); 
        }
    }

    private void sendData(String myMsg) {
        try {
            out.writeObject(myMsg);
        } catch (IOException e) {
            System.out.println("Error on send Data");
            e.printStackTrace();
        }
    }

    private void listenForClients() {
        try {
            while (true) {
                client = listener.accept();
                System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
                getStreams();
                processClient();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error on ListenForClients methoud Conection");
            e.printStackTrace();
        }
    }

    private void closeConnections() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (client != null) client.close();
        } catch (IOException e) {
            System.out.println("Error on close Conection");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            listener = new ServerSocket(6666);
            System.out.println("Server is listening on port 6666...");
            listenForClients();  
        } catch (IOException e) {
            System.err.println("Error initializing server: " + e.getMessage());
        }
    }

    public String getServerStatus() {
       
        System.out.println("Server is running");
        return "Server is running."; 
    }

    public static void main(String[] args) {
        new Thread(new ServerConnection()).start(); // Start server connection in a new thread
    }
}
