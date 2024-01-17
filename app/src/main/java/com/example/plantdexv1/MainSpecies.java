package com.example.plantdexv1;
// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */


public class MainSpecies{
    public String status;
    public String rank;
    public Object family_common_name;
    public int genus_id;
    public String observations;
    public Object vegetable;
    public String genus;
    public String family;
    public Object duration;
    public Object edible_part;
    public Object edible;
    public ImagesPlants images;
    public Distributions distributions;
    public Flower flower;
    public Foliage foliage;
    public FruitSeed fruit_or_seed;
    public Specification specifications;
    public Growth growth;

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
    public Specification getSpecifications() {
        return specifications;
    }
    public ImagesPlants getImages() {
        return images;
    }
}

class Distributions{

    public Object[] Native;
    public Object[] introduced;
    public Object[] doubtful;
    public Object[] absent;
    public Object[] extinct;
}





