package com.example.ningning.basicapp;

// Name: Lesley Huang and Trishla Pokharna
// Program file: Entries.java
// Class Description: This class is like a helper class for other activities  that use the database.
// It essentially has many getter and setter methods to get text, date, and ratings.
public class Entries {

    //private instance variables for the Entries class
    private int id;
    private String date;
    private String text;
    private String rating;

    public Entries() {}
    //constructor for Entries object
    public Entries(String d, String t, String r){
        date = d;
        text = t;
        rating = r;
    }
    //getter method for id of entry
    public int getId(){
        return id;
    }

    //setter method for id of entry
    public void setId(int i){
        id = i;
    }

    //getter method for date of entry
    public String getDate(){
        return date;
    }

    //getter method for text of events of the day in the entry
    public String getText(){
        return text;
    }

    //getter method for the ratings in the entry
    public String getRating(){
        return rating;
    }

    //sets the date of the entry
    public void setDate(String d){
        date = d;
    }

    //sets the new text of events of the day in the entry
    public void setText(String t){
        text = t;
    }

    //changes the rating of the entry
    public void setRating(String r){
        rating = r;
    }

    @Override
    public String toString(){
        return "Entry [date= " + date + ", text= " + ", rating= " + rating + "]";
    }
}
