package com.example.plantdexv1;

public class Main_Species {

    String complete_data;
    Flower flower;
    Foliage foliage;
    FruitSeed fruit_or_seed;
    Growth growth;
    Seed seed;
    Specification specifications;

    public Main_Species(String complete_data, Flower flower, Foliage foliage, FruitSeed fruit_or_seed, Growth growth, Seed seed, Specification specifications) {
        this.complete_data = complete_data;
        this.flower = flower;
        this.foliage = foliage;
        this.fruit_or_seed = fruit_or_seed;
        this.growth = growth;
        this.seed = seed;
        this.specifications = specifications;
    }

    //Getters
    public String getComplete_data() {
        return complete_data;
    }
    public Flower getFlower() {
        return flower;
    }

    public Foliage getFoliage() {
        return foliage;
    }

    public FruitSeed getFruit_or_seed() {
        return fruit_or_seed;
    }

    public Growth getGrowth() {
        return growth;
    }

    public Seed getSeed() {
        return seed;
    }

    public Specification getSpecifications() {
        return specifications;
    }

}
