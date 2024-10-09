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
        // Server socket is initialized in the run method to avoid blocking the GUI
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
            // Basic handling, just move on
        } finally {
            closeConnections(); // Ensure streams and sockets are closed
        }
    }

    private void sendData(String myMsg) {
        try {
            out.writeObject(myMsg);
        } catch (IOException e) {
            // Basic handling, just move on
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
            // Basic handling, just move on
        }
    }

    private void closeConnections() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (client != null) client.close();
        } catch (IOException e) {
            // Keeping this basic; no output needed
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
        // Return a simple status message for demonstration purposes
        return "Server is running."; 
    }

    public static void main(String[] args) {
        new Thread(new ServerConnection()).start(); // Start server connection in a new thread
    }
}
