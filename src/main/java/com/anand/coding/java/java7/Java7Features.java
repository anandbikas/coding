package com.anand.coding.java.java7;

/**
 *
 */
public class Java7Features {

    public void stringInSwitch(String s){
        switch (s){
            case "anand":
            case "bikas":
            case "anandbikas":
                System.out.println("Bikas Anand");
                break;
            default:
                System.out.println(s);
        }
    }

    public static void main(String [] args){
        Java7Features java7Features = new Java7Features();

        java7Features.stringInSwitch("anand");
    }

}
