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

        } finally {
            closeConnections();
        }
    }

    private void sendData(String myMsg) {
        try {
            out.writeObject(myMsg);
        } catch (IOException e) {
           
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
        }
    }

    private void closeConnections() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {

        }
    }

    @Override
    public void run() {
        try {
            listener = new ServerSocket(6666);
            System.out.println("Server is listening on port 6666...");
            listenForClients();  // Start listening for clients
        } catch (IOException e) {
            System.err.println("Error initializing server: " + e.getMessage());
        }
    }

    public String getServerStatus() {

        return "Server is running.";
    }

    public static void main(String[] args) {
        new Thread(new ServerConnection()).start();
    }
}
