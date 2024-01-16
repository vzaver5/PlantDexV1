package com.example.plantdexv1;

import java.util.Objects;

public class ImagesPlants {
    public Objects[] flower;
    public Objects[] leaf;
    public Objects[] habit;
    public Objects[] fruit;
    public Objects[] bark;
    public Objects[] other;

    public ImagesPlants(Objects[] flower, Objects[] leaf, Objects[] habit, Objects[] fruit, Objects[] bark, Objects[] other) {
        this.flower = flower;
        this.leaf = leaf;
        this.habit = habit;
        this.fruit = fruit;
        this.bark = bark;
        this.other = other;
    }
}
