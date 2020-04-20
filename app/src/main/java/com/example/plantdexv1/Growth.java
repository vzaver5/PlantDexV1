package com.example.plantdexv1;

class Growth {
    float ph_maximum;
    float ph_minimum;
    PrecipitationMax precipitation_maximum;
    PrecipitationMin precipitation_minimum;
    TemperatureMin temperature_minimum;

    public Growth(float ph_maximum, float ph_minimum, PrecipitationMax precipitation_maximum, PrecipitationMin precipitation_minimum, TemperatureMin temperature_minimum) {
        this.ph_maximum = ph_maximum;
        this.ph_minimum = ph_minimum;
        this.precipitation_maximum = precipitation_maximum;
        this.precipitation_minimum = precipitation_minimum;
        this.temperature_minimum = temperature_minimum;
    }

    //Getters
    public float getPh_maximum() {
        return ph_maximum;
    }

    public float getPh_minimum() {
        return ph_minimum;
    }

    public PrecipitationMax getPrecipitation_maximum() {
        return precipitation_maximum;
    }

    public PrecipitationMin getPrecipitation_minimum() {
        return precipitation_minimum;
    }

    public TemperatureMin getTemperature_minimum() {
        return temperature_minimum;
    }
}
