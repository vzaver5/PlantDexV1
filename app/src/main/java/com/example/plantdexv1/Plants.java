package com.example.plantdexv1;

public class Plants {
    String scientific_name;
    int id;
    String common_name;
    String image_url;
    MainSpecies main_species;

    public Plants(String scientific_name, int id, String common_name, String image_url, MainSpecies main_species){
        this.scientific_name = scientific_name;
        this.id = id;
        this.common_name = common_name;
        this.image_url = image_url;
        this.main_species = main_species;
    }

    //Getters
    public String getImages() {
        return image_url;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public int getId() {
        return id;
    }

    public String getCommon_name() {
        return common_name;
    }

    public MainSpecies getMain_species() {
        return main_species;
    }
}
