package uebung;

import java.util.Arrays;

public class Player {

    private String name;
    private int alter;
    private String[] orte;
    private String birthPlace;

    public Player() {
    }

    public Player(String name, int alter, String[] orte, String birthPlace) {
        this.name = name;
        this.alter = alter;
        this.orte = orte;
        this.birthPlace = birthPlace;
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

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", alter=" + alter +
                ", orte=" + Arrays.toString(orte) +
                ", birthPlace='" + birthPlace + '\'' +
                '}';
    }
}
