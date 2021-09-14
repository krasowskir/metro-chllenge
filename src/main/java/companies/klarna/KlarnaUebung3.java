package companies.klarna;

import java.util.Stack;
import java.util.StringTokenizer;

public class KlarnaUebung3 {

    //    private Stack<Float> operands = new Stack<>();
//    private StringTokenizer strTok; // 5 1 2 + 4 * + 3 -  => 14
//
//    public String evaluate(String expr) {
//        return expr.equals("") ? "" : handleValidInput(expr).toString();
//    }
//
//    public Float handleValidInput(String expr){
//        strTok = new StringTokenizer(expr, " ");
//        while (strTok.hasMoreTokens()) {
//            String elem = strTok.nextToken();
//            operands = handleElement(operands, elem);
//        }
//        return operands.pop();
//    }
//
//    private Stack<Float> handleElement(Stack<Float> operand, String elem) {
//        try {
//            float floatNumber = Float.parseFloat(elem);
//            operand.push(floatNumber);
//        } catch (NumberFormatException ne) {
//            float operand2 = operand.pop();
//            float operand1 = operand.pop();
//            switch (elem) {
//                case "+":
//                    operand.push(operand1 + operand2);
//                    break;
//                case "-":
//                    operand.push(operand1 - operand2);
//                    break;
//                case "*":
//                    operand.push(operand1 * operand2);
//                    break;
//                case "/":
//                    operand.push(operand1 / operand2);
//                    break;
//            }
//        }
//        return operand;
//    }




//    private Stack<Float> operand = new Stack<>();
//    private StringTokenizer strTok; // 5 1 2 + 4 * + 3 -  => 14
//
//    public String evaluate(String expr) {
//        return expr.equals("") ? "0" : handleValidInput(expr).toString();
//    }
//
//    public Float handleValidInput(String expr) {
//        strTok = new StringTokenizer(expr, " ");
//        while (strTok.hasMoreTokens()) {
//            String elem = strTok.nextToken();
//            operand = handleElement(operand, elem);
//        }
//        return operand.pop();
//    }
//
//    private Stack<Float> handleElement(Stack<Float> operand, String elem) {
//        try {
//            float floatNumber = Float.parseFloat(elem);
//            operand.push(floatNumber);
//        } catch (NumberFormatException ne) {
//            float operand2 = operand.pop();
//            float operand1 = operand.pop();
//            switch (elem) {
//                case "+":
//                    operand.push(operand1 + operand2);
//                    break;
//                case "-":
//                    operand.push(operand1 - operand2);
//                    break;
//                case "*":
//                    operand.push(operand1 * operand2);
//                    break;
//                case "/":
//                    operand.push(operand1 / operand2);
//                    break;
//            }
//        }
//        return operand;
//    }


    private Stack<Float> operand = new Stack<>();
    private StringTokenizer strTok;

    public Float calculate(String expr) {
        return expr.equals("") ? 0.0f : handleValidInput(expr);
    }

    public Float handleValidInput(String expr){
        strTok = new StringTokenizer(expr, " ");
        while (strTok.hasMoreTokens()) {
            String elem = strTok.nextToken();
            operand = handleElement(operand, elem);
        }
        return operand.pop();
    }

    private Stack<Float> handleElement(Stack<Float> operand, String elem) {
        try {
            float floatNumber = Float.parseFloat(elem);
            operand.push(floatNumber);
        } catch (NumberFormatException ne) {
            float operand2 = operand.pop();
            float operand1 = operand.pop();
            switch (elem) {
                case "+":
                    operand.push(operand1 + operand2);
                    break;
                case "-":
                    operand.push(operand1 - operand2);
                    break;
                case "*":
                    operand.push(operand1 * operand2);
                    break;
                case "/":
                    operand.push(operand1 / operand2);
                    break;
            }
        }
        return operand;
    }
}
