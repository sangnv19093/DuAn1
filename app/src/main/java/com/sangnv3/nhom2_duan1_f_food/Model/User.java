package com.sangnv3.nhom2_duan1_f_food.Model;

public class User {
    private int maUser;
    private String email;
    private String name;
    private String address;
    private String pass;
    private String role;

    public User(int maUser, String email, String name, String address, String pass, String role) {
        this.maUser = maUser;
        this.email = email;
        this.name = name;
        this.address = address;
        this.pass = pass;
        this.role = role;
    }

    public User(int maUser, String email, String name, String pass, String role) {
        this.maUser = maUser;
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.role = role;
    }

    public User() {
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
