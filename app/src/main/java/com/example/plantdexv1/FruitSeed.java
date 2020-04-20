package com.example.plantdexv1;

class FruitSeed {
    String color;
    String conspicuous;
    String seed_abundance;
    String seed_period_begin;
    String seed_period_end;
    String seed_persistence;

    public FruitSeed(String color, String conspicuous, String seed_abundance, String seed_period_begin, String seed_period_end, String seed_persistence) {
        this.color = color;
        this.conspicuous = conspicuous;
        this.seed_abundance = seed_abundance;
        this.seed_period_begin = seed_period_begin;
        this.seed_period_end = seed_period_end;
        this.seed_persistence = seed_persistence;
    }

    //Getters
    public String getColor() {
        return color;
    }

    public String getConspicuous() {
        return conspicuous;
    }

    public String getSeed_abundance() {
        return seed_abundance;
    }

    public String getSeed_period_begin() {
        return seed_period_begin;
    }

    public String getSeed_period_end() {
        return seed_period_end;
    }

    public String getSeed_persistence() {
        return seed_persistence;
    }
}
