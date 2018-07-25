package com.example.megha.bmiapp;

public class BMIResult {
    private double height = 1;
    private  double weight = 1;
    // TO do add the Date

    public  BMIResult(double height , double weight ){
        this.height = height;
        this.weight = weight;
    }

    public  double getHeight(){return  height;}
    public  void  setHeight (double height){this.height = height;}

    public  double getWeight(){return  weight;}
    public  void  setWeight (double weight){this.weight = weight;}

    public  double getResult(){
        return  weight/(height*height);
    }

    @Override
    public String toString() {
        return String.valueOf(getResult());}
}

