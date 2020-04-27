package com.example.plantdexv1;

class TemperatureMin {
    Float deg_c;
    Float deg_f;

    public TemperatureMin(Float deg_c, Float deg_f) {
        this.deg_c = deg_c;
        this.deg_f = deg_f;
    }

    //Getters
    public Float getDeg_c() {
        return deg_c;
    }

    public Float getDeg_f() {
        return deg_f;
    }
}
