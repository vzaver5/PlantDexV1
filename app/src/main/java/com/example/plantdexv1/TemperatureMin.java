package com.example.plantdexv1;

class TemperatureMin {
    float deg_c;
    float deg_f;

    public TemperatureMin(float deg_c, float deg_f) {
        this.deg_c = deg_c;
        this.deg_f = deg_f;
    }

    //Getters
    public float getDeg_c() {
        return deg_c;
    }

    public float getDeg_f() {
        return deg_f;
    }
}
