package uebung.multithreading.immutability;

public class MutableClass {
    private int prop;

    public MutableClass(int prop) {
        this.prop = prop;
    }

    public int getProp() {
        return prop;
    }

    public void setProp(int prop) {
        this.prop = prop;
    }
}
