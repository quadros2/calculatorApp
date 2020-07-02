package com.example.calculatorapp;

public class Note {

    String title, text, date;

    Note(String setTitle, String setText, String setDate) {
        title = setTitle;
        text = setText;
        date = setDate;
    }

    Note() {

    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

}
