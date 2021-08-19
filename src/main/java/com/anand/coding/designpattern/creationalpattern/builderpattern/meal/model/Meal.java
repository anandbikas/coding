package com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Meal {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        return (float)items.stream().mapToDouble(Item::price).sum();
    }

    public void showItems(){
        items.forEach(Item::display);
    }
}