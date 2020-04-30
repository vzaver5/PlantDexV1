package com.example.plantdexv1;

import java.util.ArrayList;
import java.util.List;

public class User {
    String userId;
    String name;
    List<String> virtualGarden;
    //TODO
    //Images

    public User(){
        //Default constructor for DataSnapshot.getValue(User.class)
    }

    public User(String name, String userId) {
        this.userId = userId;
        this.name = name;
        this.virtualGarden = new ArrayList<String>();
    }

    //Getters
    public String getName() {
        return name;
    }
    public List<String> getVirtualGarden() {
        return virtualGarden;
    }
    public String getUserId(){
        return userId;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void addToVirtualGarden(String plant){
        this.virtualGarden.add(plant);
    }
    /*
    //Add to virtualGarden
    public void addToVirtualGarden(String plant){
        boolean nullFound = false;
        int nullLoc = 0;
        //Determine if there is space in array
        //nullFound = true : we can add to current array
        //nullFound = false : double size of the array
        for(int i =0; i < virtualGarden.length; i++){
            if(virtualGarden[i] == null){
                nullFound = true;
                nullLoc = i;
                break;
            }
        }
        //If nullFound true then we can add plant to array
        if(nullFound){
            System.out.println("Adding plant without resize");
            this.virtualGarden[nullLoc] = plant;
        }else{      //Else double size of the array to make space
            System.out.println("Resizing array");
            int newArrSize = virtualGarden.length * 2;
            String[] newArr = new String[newArrSize];
            for(int i =0; i < virtualGarden.length; i ++){
                newArr[i] = virtualGarden[i];
            }
            //Add plant to the array at first null position
            newArr[virtualGarden.length] = plant;

            //Assign
            this.virtualGarden = new String[newArrSize];
            for( int i = 0; i <  newArrSize; i++){
                this.virtualGarden[i] = newArr[i];
            }
        }
    }
     */
    public void setUserId(String userId){
        this.userId = userId;
    }

}
