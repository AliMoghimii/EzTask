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
    static List<Tasks> taskList;
    Adapter adapter;
    FloatingActionButton addTaskBtn;

    public static boolean prepareData(Tasks task) {

        if (taskList == null) {  // if it was empty list create it
            taskList = new Vector<Tasks>();
        }

        if (taskList.contains(task)){
            return false;
        }

       //SignIn.current.taskList.add(task); //current = users.get(i);
        taskList.add(task);


//        taskList.add(new Task("not def",1));
//        taskList.add(new Task("not def", "2018" ,"12" ,"3" , "12" ,"30" ,"0",1));
//        taskList.add(new Task("not def", "2014" ,"12" ,"3" , "12" ,"30" ,"0",1));
//        taskList.add(new Task("other", "2019" ,"12" ,"3" , "12" ,"30" ,"0",2));
//        taskList.add(new Task("high", "2018" ,"12" ,"3" , "12" ,"30" ,"0",3));
//        taskList.add(new Task("high", "2018" ,"12" ,"3" , "11" ,"30" ,"0",3));
//        taskList.add(new Task("high",3));
//        taskList.add(new Task("other", "2018" ,"12" ,"3" , "12" ,"30" ,"0",2));
//        taskList.add(new Task("low", "2018" ,"12" ,"3" , "12" ,"30" ,"0",5));
//        taskList.add(new Task("low", "2019" ,"12" ,"3" , "12" ,"30" ,"0",5));
//        taskList.add(new Task("low", "2018" ,"12" ,"3" , "11" ,"30" ,"0",5));

        //taskList.add(task);
        Collections.sort(taskList);  // sorting ba time ha.
        return true;
    }

    void showData() {

        adapter = new Adapter(taskList);
        myRecView.setLayoutManager(new LinearLayoutManager(this)); // this acticity
        myRecView.setItemAnimator(new DefaultItemAnimator());
        myRecView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_page);

        addTaskBtn = (FloatingActionButton) findViewById(R.id.addbtn);

        myRecView = (RecyclerView) findViewById(R.id.rview);
        showData();

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent AddTaskIntent = new Intent(getApplicationContext(), AddTask.class);
                startActivity(AddTaskIntent);


            }

        });
    }
}
