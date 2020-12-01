package com.kazemieh.www.dictionary;

public class DataModel {
    String En;
    String Fa;
    int id;
    DataModel(String en,String fa,int id){
        this.En=en;
        this.Fa=fa;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getEn() {
        return En;
    }

    public String getFa() {
        return Fa;
    }
}
