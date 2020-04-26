package com.example.plantdexv1;

class Flower {
    String color;
    String conspicuous;

    public Flower(String color, String conspicuous){
        this.color = color;
        this.conspicuous = conspicuous;
    }

    //Getters
    public String getColor() {
        if(color != null){
            return color;
        }else{
            return color = "NoInfo";
        }

    }

    public String getConspicuous() {
        return conspicuous;
    }
}
