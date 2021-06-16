package com.example.alqudsapp.models;

public class Question {
// title,desc
    private String text, answer;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    public Question() {
    }
}
