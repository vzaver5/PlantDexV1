package com.example.plantdexv1;

class Foliage {
    String color;
    String porosity_summer;
    String porosity_winter;
    String texture;

    public Foliage(String color, String porosity_summer, String porosity_winter, String texture) {
        this.color = color;
        this.porosity_summer = porosity_summer;
        this.porosity_winter = porosity_winter;
        this.texture = texture;
    }

    public String getColor() {
        return color;
    }

    public String getPorosity_summer() {
        return porosity_summer;
    }

    public String getPorosity_winter() {
        return porosity_winter;
    }

    public String getTexture() {
        return texture;
    }
}
