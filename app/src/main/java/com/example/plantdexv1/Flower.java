package com.example.plantdexv1;

class Flower {
    String[] color;
    boolean conspicuous;

    public Flower(String[] color, boolean conspicuous){
        this.color = color;
        this.conspicuous = conspicuous;
    }

    //Getters
    public String getColor() {
        StringBuilder sb = new StringBuilder();
        if(color != null){
            for(int i = 0; i < color.length; i++){
                sb.append(color[i]);
                if(i + 1 < color.length){
                    sb.append(", ");
                }
            }
        }else{
            return sb.append("No Info").toString();
        }
        return sb.toString();
    }

    public boolean isConspicuous() {
        return conspicuous;
    }
}
