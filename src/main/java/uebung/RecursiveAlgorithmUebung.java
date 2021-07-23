package uebung;

public class RecursiveAlgorithmUebung {

    public int facultaetCalc(int num){
        if (num > 1){
            return num * facultaetCalc(--num);
        } else {
            return num;
        }
    }
}
