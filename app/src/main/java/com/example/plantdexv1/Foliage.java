package com.example.plantdexv1;

class Foliage {
    String[] color;
    String texture;
    boolean leaf_retention;

    public Foliage(String[] color, String texture, boolean leaf_retention) {
        this.color = color;
        this.texture = texture;
        this.leaf_retention = leaf_retention;
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
    public boolean isLeaf_retention() {
        return leaf_retention;
    }

    public String getTexture() {
        return texture;
    }
}
