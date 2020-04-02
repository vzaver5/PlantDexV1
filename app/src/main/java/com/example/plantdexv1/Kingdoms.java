package com.example.plantdexv1;

public class Kingdoms {
    Subkingdoms[] subkingdoms;
    String slug;
    String name;
    int id;

    public Kingdoms(Subkingdoms[] subkingdoms, String slug, String name, int id){
        this.subkingdoms = subkingdoms;
        this.slug = slug;
        this.name = name;
        this.id = id;
    }

}
