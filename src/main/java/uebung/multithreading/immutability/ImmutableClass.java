package uebung.multithreading.immutability;

public final class ImmutableClass {
    private final int prop;
    private final MutableClass mutableClass;

    public ImmutableClass(int prop, MutableClass mutableClass) {
        this.prop = prop;
        this.mutableClass = new MutableClass(mutableClass.getProp());
    }

    public int getProp() {
        return prop;
    }

    public MutableClass getMutableClass() {
        return mutableClass;
    }
}
