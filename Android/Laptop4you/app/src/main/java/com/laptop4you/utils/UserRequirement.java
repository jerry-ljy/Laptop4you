package com.laptop4you.utils;

public class UserRequirement {
    /*get answer from front-end
    represent answer into laptop configuration*/
    public float price;
    private int processor;
    private int graphics;
    private int hard_drive;
    private int memory;
    private int[] config = {processor,graphics,hard_drive,memory};

    public UserRequirement(){
        this.price = 0;
        this.processor = 0;
        this.graphics = 0;
        this.hard_drive = 0;
        this.memory = 0;
    }

    public void setPrice(float price){
        if(price>0){
            this.price = price;
        }
    }

    public int[] getConfig(){    //return the user's requirement after survey
        return this.config;
    }

    public void calConfig(String ans){
        int[] new_config;
        int low = 0;
        int mid = 1;
        int high = 2;
        switch (ans) {
            case "moba":{
                new_config = new int[]{mid,low,mid,mid};
                break;
            }
            case "mmorpg": case "code":{
                new_config = new int[]{high,high,high,high};
                break;
            }
            case "smallgame":{
                new_config = new int[]{low,low,mid,low};
                break;
            }
            case "design":{
                new_config = new int[]{mid,high,high,mid};
                break;
            }
            case "office":{
                new_config = new int[]{low,low,mid,mid};
                break;
            }
            case "video" :{
                new_config = new int[]{low,low,low,mid};
                break;
            }
            default: {
                new_config = new int[]{low,low,low,low};
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            if(new_config[i]>=this.config[i]){
                this.config[i] = new_config[i];
            }
        }
    }
}
