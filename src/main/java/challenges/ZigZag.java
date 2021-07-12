package challenges;

import java.util.StringJoiner;

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);

Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:
Input: s = "A", numRows = 1
Output: "A"


Constraints:

    1 <= s.length <= 1000
    s consists of English letters (lower-case and upper-case), ',' and '.'.
    1 <= numRows <= 1000


 */
public class ZigZag {

    public String convert(String s, int numRows) {
        char[][] zigZagText = new char[numRows][s.length()];
        boolean changeDirection = false;
        char[] textInChar = s.toCharArray();
        int textLength = textInChar.length;

        for (int k = 0; k < textLength; k++){
            int roundFitInIndex = k / numRows;

            if (k % numRows == 0 && k > 1){
                changeDirection = !changeDirection;
            }

            if (changeDirection){
                //x steigt  +1 ; y sinkt -1
                zigZagText[(numRows -1) - (k % numRows) - 1][k % numRows + 1] = textInChar[k];
            } else {
                // x bleibt stat. ; y steigt +1
                zigZagText[k % numRows][roundFitInIndex] = textInChar[k];
            }

        }

        String convertedText = convertCharArr(zigZagText);
        return convertedText;
    }

    public String convertCharArr(char[][] fromZigZagText){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < fromZigZagText.length; i++){
            for (int j = 0; j < fromZigZagText[i].length; j++){
                stringBuilder.append(fromZigZagText[i][j]);
            }
        }
        return stringBuilder.toString();
    }
}
