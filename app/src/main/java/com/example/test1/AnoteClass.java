package com.example.test1;

public class AnoteClass {
    private long ID;
    private String title;
    private String text;
    private String date;
    private String time;

    AnoteClass(){}

    AnoteClass(String title,String text, String date, String time){
        this.title=title;
        this.text=text;
        this.date=date;
        this.time=time;
    }

    AnoteClass(long ID,String title,String text, String date, String time){ //This one takes also the id for editting note
        this.ID=ID;
        this.title=title;
        this.text=text;
        this.date=date;
        this.time=time;

    }

    //auto generated setters-getters
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
