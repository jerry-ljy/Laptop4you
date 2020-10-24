package com.laptop4you.utils;

public class Laptop {
    public String uuid;
    public String name;
    public String price;
    public String processor;
    public int memory;
    public String hard_drive;
    public String graphics;
    public String img;

    public  Laptop(String uuid,String name,String price,String processor,int memory,String hard_drive,String graphics,String img){
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.processor = processor;
        this.memory=memory;
        this.hard_drive=hard_drive;
        this.graphics=graphics;
        this.img=img;
    }
}
