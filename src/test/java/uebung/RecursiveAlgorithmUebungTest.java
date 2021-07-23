package uebung;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveAlgorithmUebungTest {

    @Test
    void testFakultaet() {
        RecursiveAlgorithmUebung uebung = new RecursiveAlgorithmUebung();
        int result = uebung.facultaetCalc(5);
        assert result == 120;
    }
}