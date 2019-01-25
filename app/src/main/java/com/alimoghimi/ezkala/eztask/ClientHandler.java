package com.alimoghimi.ezkala.eztask;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ClientHandler extends Thread implements Serializable {


    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    Socket clientSoc;
    Users users;

    FileOutputStream fileOutputStream;
    ObjectOutputStream writeTofile;

    ObjectInputStream readFromFile;
    FileInputStream fileInputStream;
    File myfile = new File("D:\\data.txt");


    ClientHandler(Socket clientSoc, ObjectInputStream inputStream, ObjectOutputStream outputStream){

        this.clientSoc = clientSoc;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        try {

            if (!myfile.exists())
                myfile.createNewFile();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

            fileOutputStream = new FileOutputStream(myfile);
            writeTofile = new ObjectOutputStream(fileOutputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        String recevedMassage = null;
        String sendmassage;
        Scanner s = new Scanner(System.in);

        try {

            while (true) {

                try {

                    try {
                            users =  (Users) inputStream.readObject();
                            // writeTofile.writeObject((User)users);
                    }
                    catch (ClassNotFoundException e){
                        System.out.println(e);
                    }


                }catch (EOFException e){
                    System.out.println(e.toString());
                }

                System.out.println("Account made: \n" + users);

                writeTofile.writeObject(users);
                writeTofile.flush();

//                try {
//                    fileInputStream = new FileInputStream(myfile);
//                    readFromFile = new ObjectInputStream(fileInputStream);
//
//                    try {
//
//                        users = (User) readFromFile.readObject();
//                    }
//                    catch (ClassNotFoundException e){
//                        e.printStackTrace();
//                    }
//
//                    outputStream.writeObject(users);
//                    outputStream.flush();
//
//                }catch (IOException e){
//                    e.printStackTrace();
//                }

/*                System.out.println("Server enter your massage: "); // write kardan.

                sendmassage = s.next();
                outputStream.writeUTF(sendmassage);
                outputStream.flush();*/
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
