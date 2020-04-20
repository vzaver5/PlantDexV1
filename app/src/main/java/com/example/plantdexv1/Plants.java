package com.example.plantdexv1;

public class Plants {
    String scientific_name;
    int id;
    String common_name;
    Images[] images;
    Main_Species main_species;

    public Plants(String scientific_name, int id, String common_name, Images[] images, Main_Species main_species){
        this.scientific_name = scientific_name;
        this.id = id;
        this.common_name = common_name;
        this.images = images;
        this.main_species = main_species;
    }

    //Getters
    public Images[] getImages() {
        return images;
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

    public Main_Species getMain_species() {
        return main_species;
    }
}
