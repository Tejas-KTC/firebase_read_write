package com.example.firebase_read_write;

public class Helperclass {
    String username,password;

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

    public Helperclass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Helperclass(){

    }
}
