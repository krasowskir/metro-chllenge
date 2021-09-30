package uebung.multithreading.executorService;

public class Result {
    private int einzahlung;
    private int ergebnis;

    public Result(int einzahlung, int ergebnis) {
        this.einzahlung = einzahlung;
        this.ergebnis = ergebnis;
    }

    public int getEinzahlung() {
        return einzahlung;
    }

    public void setEinzahlung(int einzahlung) {
        this.einzahlung = einzahlung;
    }

    public int getErgebnis() {
        return ergebnis;
    }

    public void setErgebnis(int ergebnis) {
        this.ergebnis = ergebnis;
    }

    @Override
    public String toString() {
        return "Result{" +
                "einzahlung=" + einzahlung +
                ", ergebnis=" + ergebnis +
                '}';
    }
}
