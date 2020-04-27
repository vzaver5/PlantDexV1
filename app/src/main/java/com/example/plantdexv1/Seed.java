package com.example.plantdexv1;

class Seed {
    String bloom_period;
    String commercial_availability;
    String seed_spread_rate;
    Float seeds_per_pound;

    public Seed(String bloom_period, String commercial_availability, String seed_spread_rate, Float seeds_per_pound) {
        this.bloom_period = bloom_period;
        this.commercial_availability = commercial_availability;
        this.seed_spread_rate = seed_spread_rate;
        this.seeds_per_pound = seeds_per_pound;
    }
    //Getters
    public String getBloom_period() {
        return bloom_period;
    }

    public String getCommercial_availability() {
        return commercial_availability;
    }

    public String getSeed_spread_rate() {
        return seed_spread_rate;
    }

    public Float getSeeds_per_pound() {
        return seeds_per_pound;
    }
}
