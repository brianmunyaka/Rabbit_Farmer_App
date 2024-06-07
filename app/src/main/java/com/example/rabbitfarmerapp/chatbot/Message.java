package com.example.rabbitfarmerapp.chatbot;

public class Message {
    private String text;
    private boolean isUser;

    public Message() {
        // Default constructor required for calls to DataSnapshot.getValue(Message.class)
    }

    public Message(String text, boolean isUser) {
        this.text = text;
        this.isUser = isUser;
    }

    public String getText() {
        return text;
    }

    public boolean isUser() {
        return isUser;
    }
}

