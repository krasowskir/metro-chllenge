package uebung.multithreading.challenges;

public class FooRunnable implements Runnable {
    private int method;
    private Foo foo;

    public FooRunnable(Foo foo, int method) {
        this.foo = foo;
        this.method = method;
    }

    @Override
    public void run() {
        if (method == 0) {
            foo.first();
        } else if (method == 1) {
            foo.second();
        } else {
            foo.third();
        }
    }
}
