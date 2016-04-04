package com.example.ningning.basicapp;

import java.io.Serializable;

/**
 * Created by Ningning on 5/11/2015.
 */
public class Text implements Serializable{
    String textInput;

    public Text(){
        textInput = "";
    }

    public void setTextI(String s) {
        textInput = s;
    }

    public String getTextI(){
        return textInput;
    }

    public String toString() {
        return textInput;
    }
}
