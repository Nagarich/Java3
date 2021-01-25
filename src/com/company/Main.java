package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static <T> void changeElementsInArray(T[] array, int elementIndexOne, int elementIndexTwo){
        T tempValue = array[elementIndexOne];
        array[elementIndexOne]=array[elementIndexTwo];
        array[elementIndexTwo]=tempValue;
    }

    public static void main(String[] args) {

        Box boxAppele1 = new Box();
        boxAppele1.putFruit(new Appele());
        boxAppele1.putFruit(new Appele());
        boxAppele1.putFruit(new Appele());
        boxAppele1.putFruit(new Appele());
        System.out.println(boxAppele1.getWeight());

        Box boxAppele2 = new Box();
        boxAppele2.putFruitsFromBox(boxAppele1);
        boxAppele2.putFruit(new Appele());

        System.out.println(boxAppele2.getWeight());
        System.out.println(boxAppele1.getWeight());

        Box boxOrange1 = new Box();
        boxOrange1.putFruit(new Orange());
        boxOrange1.putFruit(new Appele());
        boxOrange1.putFruit(new Orange());

        System.out.println(boxOrange1.getWeight());
        System.out.println(boxOrange1.compare(boxAppele2));

        boxAppele2.putFruitsFromBox(boxOrange1);
        System.out.println(boxOrange1.getWeight());

    }

}

class Fruit {
    protected float weight;

    public float getWeight() {
        return weight;
    }
}

class Appele extends Fruit {
    public Appele() { weight = 1.0f; }
}
class Orange extends Fruit {
    public Orange() { weight = 1.5f; }
}

class Box <T extends Fruit> {
    private List<T> fruitsList;

    public Box() { fruitsList = new ArrayList<>(); }

    private <TT extends Fruit> boolean compareBoxType(TT someFruit){
        if (fruitsList.isEmpty() || fruitsList.get(0).getClass().isInstance(someFruit))
            return true;
        else
            return false;
    }

    public <TT extends Fruit> boolean putFruit(TT someFruit){
        if (!compareBoxType(someFruit)) {
            System.out.println("Нельзя смешивать фрукты!");
            return false;
        } else {
            fruitsList.add((T) someFruit);
            return true;
        }
   }

    public <TT extends Fruit> void putFruitsFromBox(Box<TT> box){
         for (TT someFruit:box.getAllFruits())
             if (!putFruit(someFruit)) return;

         box.clearBox();
    }

    public void clearBox(){
        fruitsList.clear();
    }

    public List<T> getAllFruits(){
        return fruitsList;
    }

    public float getWeight(){
        float totalWeight=0;
        for (T someFruit:fruitsList)
            totalWeight+=someFruit.getWeight();
        return totalWeight;
    }

    public <TT extends Fruit> boolean compare(Box<TT> box){
        return box.getWeight() == getWeight();
    }

}
