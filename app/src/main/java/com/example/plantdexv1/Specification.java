package com.example.plantdexv1;

class Specification {
    String growth_form;
    String growth_habit;
    String growth_rate;
    String shape_and_orientation;

    public Specification(String growth_form, String growth_habit, String growth_rate, String shape_and_orientation) {
        this.growth_form = growth_form;
        this.growth_habit = growth_habit;
        this.growth_rate = growth_rate;
        this.shape_and_orientation = shape_and_orientation;
    }
    //Getters
    public String getGrowth_form() {
        return growth_form;
    }

    public String getGrowth_habit() {
        return growth_habit;
    }

    public String getGrowth_rate() {
        return growth_rate;
    }

    public String getShape_and_orientation() {
        return shape_and_orientation;
    }

}
