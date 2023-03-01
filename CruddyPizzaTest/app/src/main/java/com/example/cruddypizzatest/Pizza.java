package com.example.cruddypizzatest;

public class Pizza {

    private int rowID;
    private String custInfo;
    private int size;
    private int cheese;
    private int pepperoni;
    private int olives;
    private int sausage;
    private String date;


    public Pizza() {
    }

    public Pizza (String custInfo, int size, int cheese, int pepperoni, int olives, int sausage, String date){

        this.custInfo = custInfo;
        this.size = size;
        this.cheese = cheese;
        this.pepperoni = pepperoni;
        this.olives = olives;
        this.sausage = sausage;
        this.date = date;

    }
    public Pizza (int rowid, String custInfo, int size, int cheese, int pepperoni, int olives, int sausage, String date){

        this.rowID = rowid;
        this.custInfo = custInfo;
        this.size = size;
        this.cheese = cheese;
        this.pepperoni = pepperoni;
        this.olives = olives;
        this.sausage = sausage;
        this.date = date;

    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public String getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(String custInfo) {
        this.custInfo = custInfo;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCheese() {
        return cheese;
    }

    public void setCheese(int cheese) {
        this.cheese = cheese;
    }

    public int getPepperoni() {
        return pepperoni;
    }

    public void setPepperoni(int pepperoni) {
        this.pepperoni = pepperoni;
    }

    public int getOlives() {
        return olives;
    }

    public void setOlives(int olives) {
        this.olives = olives;
    }

    public int getSausage() {
        return sausage;
    }

    public void setSausage(int sausage) {
        this.sausage = sausage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
