package com.example.plantdexv1;

class FruitSeed {
    String[] color;
    boolean conspicuous;
    boolean seed_persistence;
    String shape;

    public FruitSeed(String[] color, boolean conspicuous, boolean seed_persistence, String shape) {
        this.color = color;
        this.conspicuous = conspicuous;
        this.seed_persistence = seed_persistence;
        this.shape = shape;
    }

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

    public boolean isSeed_persistence() {
        return seed_persistence;
    }

    public String getShape() {
        return shape;
    }
}
