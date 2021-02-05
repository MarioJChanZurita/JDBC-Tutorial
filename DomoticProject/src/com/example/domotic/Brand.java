package com.example.domotic;

public enum Brand{

    LG("LG"),
    PANASONIC("Panasonic"),
    SONY("Sony"),
    DAEWO("Daewo"),
    CARRIER("Carrier"),
    YORK("York");

    public final String brandName;
    public final int brandCode;
    
    Brand (String brandName){
        this.brandName = brandName;
        this.brandCode = -1;
    }

    public String getBrandName(){
        return brandName;
    }

    public int getBrandCode(){
        return brandCode;
    }

}
