package com.example.plantdexv1;

public class Plants {
    String scientific_name;
    int id;
    String common_name;
    Images[] images;

    public Plants(String scientific_name, int id, String common_name,Images[] images){
        this.scientific_name = scientific_name;
        this.id = id;
        this.common_name = common_name;
        this.images = images;
    }
}
