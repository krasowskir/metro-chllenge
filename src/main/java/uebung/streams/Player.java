package uebung.streams;

import java.util.Arrays;

public class Player {
    private String name;
    private String[] orte;
    private int alter;

    public Player() {
    }

    public Player(String name, String[] orte, int alter) {
        this.name = name;
        this.orte = orte;
        this.alter = alter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public String[] getOrte() {
        return orte;
    }

    public void setOrte(String[] orte) {
        this.orte = orte;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", orte=" + Arrays.toString(orte) +
                ", alter=" + alter +
                '}';
    }
}