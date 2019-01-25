package com.alimoghimi.ezkala.eztask;

import android.app.admin.SystemUpdatePolicy;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import static android.content.ContentValues.TAG;

public class Client extends AsyncTask<Vector<Users>, Void, Vector<Users>> {


    String massage = "";
    TextView textMassage;
    String inputMassage;
    EditText inputMassageTxt;


    Vector<Users> inputUsers;
    Vector<Users> outputUsers;
    Vector<Users> temp;

    Socket clientSocket;

    Client(Vector<Users> inputUsers, Vector<Users> outputUsers){

        this.inputUsers = inputUsers;
        this.outputUsers = outputUsers;
    }

    @Override
    protected Vector<Users> doInBackground(Vector<Users>... vectorsUsers) {

            inputUsers = vectorsUsers[0];

        try {

            clientSocket = new Socket("192.168.43.172", 3345);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            //inputMassage = textMassage.getText().toString();

            // System.out.println("Tag" + " inja hasstim  !!!");
            //out.writeUTF("client sayed: "+ inputMassage);
            out.writeObject(inputUsers);
            out.writeUTF("new list has been added.");
            out.flush();

            try {

                outputUsers = (Vector<Users>) in.readObject();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }

        }

        catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException i){
            i.printStackTrace();
        }
        finally {

            try {
                if (clientSocket != null)
                    clientSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return outputUsers;
    }

    @Override
    protected void onPostExecute(Vector<Users> outputUsers)  {
        ///----------------------to ye UI etefagh mofthii

        // static Vector in loading activity  = outputUsers
        //outputUsers.setText(outputUsers);

        super.onPostExecute(outputUsers);
    }
}
