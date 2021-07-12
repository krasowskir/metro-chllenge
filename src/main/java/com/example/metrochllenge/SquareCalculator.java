package com.example.metrochllenge;

public class SquareCalculator {

    public void calculateSquares(){
        int result = calculateSquares(new String[][]{
                {"A", "B", "C", "J", "Q"},
                {"D", "E", "F", "K", "R"},
                {"G", "H", "I", "L", "S"},
                {"M", "N", "O", "P", "T"},
                {"A", "B", "C", "J", "U"},
        });
        System.out.println("the result is: " + result);
    }

    public int calculateSquares(String[][] points){
        int square = 0, k = 0;
        for (int i = 0; i < points.length; i++){
            for (int j = 0; j < points[i].length; j++){
                if (i+1 < points.length && j + 1 < points[i].length){
                    square += 1;
                    if (j == i){
                        k += 1;
                        if (k > 1){
                            square += 1;
                        }
                    }
                }
            }
        }
        return square;
    }
}
