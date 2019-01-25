package com.alimoghimi.ezkala.eztask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    EditText taskInfo;
    EditText taskYear;
    EditText taskMonth;
    EditText taskDay;
    EditText taskHour;
    EditText taskMinute;
    EditText taskSecond;
    Button submitBtn;
    Button toCalender;

    public boolean checkDatesEnterd(EditText year, EditText month, EditText day, EditText hour, EditText minute, EditText second){
        if (year.getText().toString().equals("")  && month.getText().toString().equals("")  && day.getText().toString().equals("")
                && hour.getText().toString().equals("") && minute.getText().toString().equals("")  && second.getText().toString().equals("") )
            return false;
        return true;
    }

    public void resetEditTexts(){

        taskDay.setText("");
        taskHour.setText("");
        taskInfo.setText("");
        taskMinute.setText("");
        taskSecond.setText("");
        taskYear.setText("");
        taskMonth.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskInfo = (EditText) findViewById(R.id.TaskInfoTxt);
        taskYear = (EditText) findViewById(R.id.YearTxt);
        taskMonth = (EditText) findViewById(R.id.MonthTxt);
        taskDay = (EditText) findViewById(R.id.DayTxt);
        taskHour = (EditText) findViewById(R.id.HourTxt);
        taskMinute = (EditText) findViewById(R.id.MinuteTxt);
        taskSecond = (EditText) findViewById(R.id.SecondTxt);
        submitBtn = (Button) findViewById(R.id.SubmitBtn);
        toCalender = (Button) findViewById(R.id.goTOCalender);


        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toList = new Intent(getApplicationContext(), TaskPage.class);

                if (!(taskInfo.getText().toString().equals("") && taskYear.getText().toString().equals("")
                        && taskMonth.getText().toString().equals("") && taskDay.getText().toString().equals("")
                        && taskHour.getText().toString().equals("") && taskMinute.getText().toString().equals("") &&
                        taskSecond.getText().toString().equals(""))) {

                    if (!checkDatesEnterd(taskYear, taskMonth, taskDay, taskHour, taskMinute, taskSecond)) {

                        if (!TaskPage.prepareData(new Tasks((String) taskInfo.getText().toString(), 2)));

                        Toast.makeText(getApplicationContext(), "This Task is Already in the list",
                                Toast.LENGTH_LONG).show();

                    } else {
                        TaskPage.prepareData(new Tasks((String) taskInfo.getText().toString(), taskYear.getText().toString(),
                                taskMonth.getText().toString(), taskDay.getText().toString(), taskHour.getText().toString(),
                                taskMinute.getText().toString(), taskSecond.getText().toString(), 2));

                        Toast.makeText(getApplicationContext(), "Task Added",
                                Toast.LENGTH_LONG).show();
                    }

                    startActivity(toList);

                    resetEditTexts();
                }

                // ****************else fill the blanks.

            }
        });

        toCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent tocal = new Intent(getApplicationContext(), AddWCalender.class);
                startActivity(tocal);
            }
        });



    }
}

