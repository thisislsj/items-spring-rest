package com.lahirusandeepa.spd1.springbootdemoitemscrud;

public class Item {
    private final long id;
    private String name;
    private double price;
    private int count;

    public Item(long id,String name,double price,int count){
        this.id=id;
        this.name=name;
        this.price=price;
        this.count=count;
    }

    public long getId(){
        return this.id;
    }

    public  String getName(){
        return this.name;
    }

    public double getprice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price=price;
    }

    public int getCount(){
        return this.count;
    }

    public void setCount(int count){
        this.count=count;
    }


    
}
