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

    public String[] getColor() {
        return color;
    }

    public String getTexture() {
        return texture;
    }
}
