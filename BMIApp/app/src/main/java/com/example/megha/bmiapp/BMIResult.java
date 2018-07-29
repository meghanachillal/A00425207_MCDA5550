package com.example.megha.bmiapp;

public class BMIResult {
    private double height ;
    private  double weight;
    private  String userName;
    private  String date;
    // TO do add the Date

    public  BMIResult(){

    }
    public  BMIResult(double height , double weight){
        this.height = height;
        this.weight = weight;

    }

    public  double getHeight(){
        return  height;
    }
    public  void  setHeight (double height){
        this.height = height;
    }

    public  double getWeight(){
        return  weight;
    }
    public  void  setWeight (double weight){
        this.weight = weight;
    }

    public  String getUserName(){
        return  userName;
    }
    public  void  setUserName (String  userName){
        this.userName = userName;
    }

    public  String getDate(){
        return  date;
    }
    public  void  setDate (String  date){
        this.date = date;
    }


    public  double getResult()
    {
        return  weight/(height*height);
    }

    @Override
    public String toString() {
        return String.valueOf(getResult());
    }
}

