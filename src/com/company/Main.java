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
        System.out.println(boxAppele1.getWeight());

        Box boxAppele2 = new Box();
        boxAppele2.putFruitsFromBox(boxAppele1);
        boxAppele2.putFruit(new Appele());

        System.out.println(boxAppele2.getWeight());
        System.out.println(boxAppele1.getWeight());

        Box boxOrange1 = new Box();
        boxOrange1.putFruit(new Orange());
        boxOrange1.putFruit(new Appele());

        System.out.println(boxOrange1.getWeight());
        System.out.println(boxOrange1.compare(boxAppele2));

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

    public <TT extends Fruit> void putFruit(TT someFruit){
        if (fruitsList.isEmpty() || fruitsList.get(0).getClass().isInstance(someFruit))
            fruitsList.add((T) someFruit);
        else
            System.out.println("Нельзя смешаать фрукты");
   }

    public void putFruitsFromBox(Box<T> box){
        for (T someFruit:box.getAllFruits()) putFruit(someFruit);
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
