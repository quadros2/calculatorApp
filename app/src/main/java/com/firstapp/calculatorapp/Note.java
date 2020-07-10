package com.firstapp.calculatorapp;

public class Note {

    String title, text, date;

    Note(String setTitle, String setText, String setDate) {
        title = setTitle;
        text = setText;
        date = setDate;
    }

    Note() {

    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
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
