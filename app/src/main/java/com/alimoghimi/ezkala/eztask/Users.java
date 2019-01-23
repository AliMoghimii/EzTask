package com.alimoghimi.ezkala.eztask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable {

    static int NumberUsers = 0;

    private String username;
    private String password;
    private String email;
    private String name;
    private String familyName;
    public List<Tasks> userTask;

    public Users(String username, String password, String email, String name, String familyName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.familyName = familyName;
        userTask = new ArrayList<>();
        NumberUsers++;
    }

    public void createTask(Tasks task, int priority){
        //task.setPriority(priority);
        userTask.add(task);
    }

    public int getNumberUsers() {
        return NumberUsers;
    }

    public void setNumberUsers(int NumberUsers) { this.NumberUsers = NumberUsers; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public List<Tasks> getUserTask() {
        return userTask;
    }

    public void setUserTask(List<Tasks> userTask) {
        this.userTask = userTask;
    }

    /*public boolean equals(Object obj)
    {

    }*/
}


class Silver extends Users
{
     public Silver(String username, String password, String email, String name, String familyName) {
        super(username , password , email , name , familyName);
        userTask = new ArrayList<>();
    }

    public void createSubTask(Tasks subTask)
    {
        //subTask.setPriority(1); //prio is 1
        userTask.add(subTask);
    }
}


class Gold extends Silver{

    public Gold(String username, String password, String email, String name, String familyName) {
        super(username, password, email, name, familyName);
    }

    public void createTaskForOther(Users destUser , Tasks task)
    {
        destUser.userTask.add(task);
    }

}



