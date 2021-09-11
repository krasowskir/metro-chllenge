package challenges;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/*
    Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first out" basis.
    People must adopt either the"oldest" (based on arrival time) of all animals at the shelter,
    or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type).
    They cannot select which specific animal they would like.
    Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat.
    You may use the built-in Linked list data structure.
*/
public class AnimalShelter {

    public enum AnimalType{
        CAT, DOG
    };

    public static class Animal{
        private AnimalType type;
        private String name;

        public Animal(AnimalType type, String name) {
            this.type = type;
            this.name = name;
        }

        public AnimalType getType() {
            return type;
        }

        public void setType(AnimalType type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Animal{" +
                    "type=" + type +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private LinkedList<Animal> animalStorage;

    public AnimalShelter() {
        this.animalStorage = new LinkedList<>();
    }

    public void enqueue(Animal animal){
        this.animalStorage.add(animal);
    }

    public Animal dequeueAny(){
        ListIterator<Animal> iter = this.animalStorage.listIterator();
        Animal giveAway = iter.next();
        iter.previous();
        iter.remove();
        return giveAway;
    }

    public Animal dequeueDog(){
        ListIterator<Animal> iter = this.animalStorage.listIterator();
        while (iter.hasNext()){
            Animal currentElem = iter.next();
            if (currentElem.getType() == AnimalType.DOG){
                iter.previous();
                iter.remove();
                return currentElem;
            }
        }
        throw new RuntimeException("no dog available");
    }

    public Animal dequeueCat(){
        ListIterator<Animal> iter = this.animalStorage.listIterator();
        while (iter.hasNext()){
            Animal currentElem = iter.next();
            if (currentElem.getType() == AnimalType.CAT){
                iter.previous();
                iter.remove();
                return currentElem;
            }
        }
        throw new RuntimeException("no cat available");
    }
}
