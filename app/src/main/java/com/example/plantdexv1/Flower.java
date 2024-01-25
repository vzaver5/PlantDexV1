package com.example.plantdexv1;

class Flower {
    String[] color;
    boolean conspicuous;

    public Flower(String[] color, boolean conspicuous){
        this.color = color;
        this.conspicuous = conspicuous;
    }

    //Getters
    public String[] getColor() {
        return color;
    }

    public boolean isConspicuous() {
        return conspicuous;
    }
}
