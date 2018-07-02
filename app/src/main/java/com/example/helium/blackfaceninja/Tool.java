package com.example.helium.blackfaceninja;

/**
 * Created by helium on 2018/6/21.
 */

public class Tool {
    private String name;

    private int imageId;

    public Tool(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

}
