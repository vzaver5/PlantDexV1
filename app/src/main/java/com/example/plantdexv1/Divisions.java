package com.example.plantdexv1;

public class Divisions {
    Subkingdoms subkingdoms;
    String slug;
    String name;
    Kingdoms kingdom;
    int id;
    Division_Classes[] dc;

    public Divisions( Subkingdoms subkingdoms,String slug, String name, Kingdoms kingdom, int id, Division_Classes[] dc){
        this.subkingdoms = subkingdoms;
        this.slug = slug;
        this.name = name;
        this.kingdom = kingdom;
        this.id = id;
        this.dc = dc;
    }
}
