package uebung;

import java.util.Arrays;

public class Player {

    private String name;
    private int alter;
    private String[] orte;

    public Player() {
    }

    public Player(String name, int alter, String[] orte) {
        this.name = name;
        this.alter = alter;
        this.orte = orte;
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
                ", alter=" + alter +
                ", orte=" + Arrays.toString(orte) +
                '}';
    }
}
