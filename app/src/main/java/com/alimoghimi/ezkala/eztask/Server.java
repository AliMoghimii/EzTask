package com.alimoghimi.ezkala.eztask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {

    static Vector<ClientHandler> clientList = new Vector<>();
    static int numberOfClients = 0;

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(5442);
        Socket socketClient = null;

        ObjectInputStream clientToServer = null;
        ObjectOutputStream ServerToClient = null;
        DataOutputStream out;
        DataInputStream in;

        System.out.println("server activated.");

        while (true){

            try {

                socketClient = server.accept();

                if(socketClient.isConnected()){
                    System.out.println("client Connected to server.");
                    numberOfClients++;
                }

                System.out.println("Online Clients " + numberOfClients);

                in = new DataInputStream(socketClient.getInputStream());

                out = new DataOutputStream( socketClient.getOutputStream());
                clientToServer = new ObjectInputStream(in);
                ServerToClient = new ObjectOutputStream(out);


                ClientHandler handler = new ClientHandler(socketClient, clientToServer, ServerToClient);

                Thread HandlerThread = new Thread(handler);
                clientList.add(handler);
                System.out.println("handler added.");


                HandlerThread.start();


            }

            catch (IOException e){
                System.out.println(e.toString());
            }

        }
    }

}
