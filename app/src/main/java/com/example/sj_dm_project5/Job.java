package com.example.sj_dm_project5;

import java.util.Locale;

public class Job {

    private int ID;
    private String position;
    private double salary;

    public Job (int newID, String newPosition, double newSalary) {
        setID(newID);
        setPosition(newPosition);
        setSalary(newSalary);
    }

    public void setID(int newID){
        ID = newID;
    }

    public void setPosition(String newPosition){
        position = newPosition;
    }

    public void setSalary(double newSalary){
        salary = newSalary;
    }

    public int getID(){
        return ID;
    }

    public String getPosition(){
        return position;
    }

    public double getSalary(){
        return salary;
    }

    public String toString(){
        //return ID + ": " + position + "; $" + salary;
        return String.format(Locale.US,"%d: %s; $%.2f", ID, position, salary);
    }

}
