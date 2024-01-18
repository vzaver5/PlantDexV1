package com.example.plantdexv1;

class Specification {
    public String ligneous_type;
    public String growth_form;
    public String growth_habit;
    public String growth_rate;
    public AverageHeight average_height;
    public MaximumHeight maximum_height;
    public String nitrogen_fixation;
    public String shape_and_orientation;
    public String toxicity;

    public Specification(String ligneous_type, String growth_form, String growth_habit, String growth_rate, AverageHeight average_height, MaximumHeight maximum_height, String nitrogen_fixation, String shape_and_orientation, String toxicity) {
        this.ligneous_type = ligneous_type;
        this.growth_form = growth_form;
        this.growth_habit = growth_habit;
        this.growth_rate = growth_rate;
        this.average_height = average_height;
        this.maximum_height = maximum_height;
        this.nitrogen_fixation = nitrogen_fixation;
        this.shape_and_orientation = shape_and_orientation;
        this.toxicity = toxicity;
    }
    class MaximumHeight{
        public Integer cm;

        public Integer getCm() {
            return cm;
        }
    }
    class AverageHeight{
        public Integer cm;

        public Integer getCm() {
            return cm;
        }
    }

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

    public AverageHeight getAverage_height() {
        return average_height;
    }

    public MaximumHeight getMaximum_height() {
        return maximum_height;
    }
}
