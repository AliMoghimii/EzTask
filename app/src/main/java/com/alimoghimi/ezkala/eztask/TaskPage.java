package com.alimoghimi.ezkala.eztask;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class TaskPage extends AppCompatActivity {

    RecyclerView myRecView;
    Adapter adapter;
    FloatingActionButton addTaskBtn;
    FloatingActionButton searchBtn;
    FloatingActionButton logoutBtn;

//---------------------------------------------------------------------prepares data and creates them

    public static boolean prepareData(Tasks task) {

        if (SignIn.current.userTask == null) {  // if it was empty list create it
            SignIn.current.userTask = new Vector<Tasks>();
        }


        if (SignIn.current.userTask.contains(task)){
            return false;
        }

         else {
            SignIn.current.userTask.add(task); //current = users.get(i);
        }

        Collections.sort(SignIn.current.userTask);  // sorting ba time ha.
        return true;
    }
//------------------------------------------------------------------------shows the prepared data

    void showData() {

            adapter = new Adapter(SignIn.current.userTask);
            myRecView.setLayoutManager(new LinearLayoutManager(this)); // this acticity
            myRecView.setItemAnimator(new DefaultItemAnimator());
            myRecView.setAdapter(adapter);

    }
    //-------------------------------------------------------------------------------------------------------Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_page);

        try {
            addTaskBtn = (FloatingActionButton) findViewById(R.id.addbtn);
            logoutBtn = (FloatingActionButton) findViewById(R.id.logoutbtn);
            searchBtn = (FloatingActionButton) findViewById(R.id.searchBtn);

            myRecView = (RecyclerView) findViewById(R.id.rview);
            showData();
            //-----------------------------------------------------------------------------------Search
            searchBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent searchPage = new Intent(getApplicationContext(), Search.class);
                    startActivity(searchPage);
                }

            });

            //-----------------------------------------------------------------------------------Add Task
            addTaskBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent AddTaskFree = new Intent(getApplicationContext(), AddTask.class);
                    Intent AddTaskSilver = new Intent(getApplicationContext(), AddTaskSilver.class);
                    Intent AddTaskGold = new Intent(getApplicationContext(), AddTaskGold.class);

                    //-----------------------------------------------------------------------------------Free
                    if (SignIn.current.getClass().equals(Gold.class))
                        startActivity(AddTaskGold);
                    //-----------------------------------------------------------------------------------Silver
                    if (SignIn.current.getClass().equals(Silver.class))
                        startActivity(AddTaskSilver);
                    //-----------------------------------------------------------------------------------Gold
                    if (SignIn.current.getClass().equals(Users.class))
                        startActivity(AddTaskFree);
                }

            });

            //-----------------------------------------------------------------------------------Logout
            logoutBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent Logout = new Intent(getApplicationContext(), SignIn.class);
                    startActivity(Logout);
                }

            });
        }
        catch(NullPointerException e)
        {
            System.out.println(e.toString());
        }
    }
}
