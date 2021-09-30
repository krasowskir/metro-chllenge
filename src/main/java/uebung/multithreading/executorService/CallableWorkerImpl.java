package uebung.multithreading.executorService;

import uebung.multithreading.aufgaben.GeldAutomat;

import java.util.concurrent.Callable;

public class CallableWorkerImpl implements Callable<Result> {
    private int ergebnis;
    private int einzahlung;
    private GeldAutomat geldAutomat;

    public CallableWorkerImpl(int einzahlung, GeldAutomat geldAutomat) {
        this.ergebnis = geldAutomat.getGuthaben();
        this.einzahlung = einzahlung;
        this.geldAutomat = geldAutomat;
    }

    public int getErgebnis() {
        return ergebnis;
    }

    public void setErgebnis(int ergebnis) {
        this.ergebnis = ergebnis;
    }

    public int getEinzahlung() {
        return einzahlung;
    }

    public void setEinzahlung(int einzahlung) {
        this.einzahlung = einzahlung;
    }

    public GeldAutomat getGeldAutomat() {
        return geldAutomat;
    }

    public void setGeldAutomat(GeldAutomat geldAutomat) {
        this.geldAutomat = geldAutomat;
    }

    @Override
    public Result call() throws Exception {
        this.ergebnis = geldAutomat.einzahle(this.einzahlung);
        Thread.sleep(500);
        return new Result(einzahlung, ergebnis);
    }
}
