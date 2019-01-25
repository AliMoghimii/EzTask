package com.alimoghimi.ezkala.eztask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.chrono.MinguoChronology;

public class MainPage extends AppCompatActivity {

    int progressCounter = 0;

    static ObjectInputStream inputStream;
    static ObjectOutputStream outputStream;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new Connection().execute();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressCounter < 100)
                {
                    progressCounter++;
                    android.os.SystemClock.sleep(25);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressCounter);

                            if(progressCounter == 100)
                            {

                                Intent SignIn = new Intent(getApplicationContext(),SignIn.class);
                                startActivity(SignIn);
                            }

                        }
                    });

                }

            }
        }).start();


    }

    class Connection  extends AsyncTask<Void, Void, Void>{

        Socket client;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;
        boolean connected = false;

        boolean canRead = false;
        // must added can write

        @Override
        protected Void doInBackground(Void... voids) {


            try{

                client = new Socket("192.168.43.215", 5442);

                if (client.isConnected()){
                    connected = true;
                }

                outputStream = new ObjectOutputStream(client.getOutputStream());
                inputStream = new ObjectInputStream(client.getInputStream());


            }catch (UnknownHostException e){
                System.out.println(e.toString());
            }
            catch (IOException o){
                System.out.println(o.toString());
        }
            return null;
    }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (connected){
                Toast.makeText(getApplicationContext(), "connected to server", Toast.LENGTH_LONG).show();

                MainPage.inputStream = this.inputStream;
                MainPage.outputStream = this.outputStream;

            }
            super.onPostExecute(aVoid);
        }

        }
    }
