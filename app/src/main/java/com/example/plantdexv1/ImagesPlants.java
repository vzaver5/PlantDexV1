package com.example.plantdexv1;

import android.media.Image;

import java.util.Objects;

public class ImagesPlants {
    public ImageSet[] flower;
    public ImageSet[] leaf;
    public ImageSet[] habit;
    public ImageSet[] fruit;
    public ImageSet[] bark;
    public ImageSet[] other;

    public ImagesPlants(ImageSet[] flower, ImageSet[] leaf, ImageSet[] habit, ImageSet[] fruit, ImageSet[] bark, ImageSet[] other) {
        this.flower = flower;
        this.leaf = leaf;
        this.habit = habit;
        this.fruit = fruit;
        this.bark = bark;
        this.other = other;
    }

    public ImageSet[] getFlower() {
        return flower;
    }

    public ImageSet[] getHabit() {
        return habit;
    }

    public ImageSet[] getFruit() {
        return fruit;
    }

    public ImageSet[] getBark() {
        return bark;
    }

    public ImageSet[] getOther() {
        return other;
    }

    public ImageSet[] getLeaf() {
        return leaf;
    }

    class ImageSet{
        String image_url;

        public String getImage_url() {
            return image_url;
        }
    }

}
