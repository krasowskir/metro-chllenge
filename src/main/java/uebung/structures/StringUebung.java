package uebung.structures;

import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class StringUebung {

    public void testStrTokenizer(){
        String myText = "bei Z zu arbeiten, wäre cool!";
        String substr = myText.substring(8);
        System.out.println("subString: " + substr);
        System.out.println("substring mit index: " + myText.substring(10,16));

        StringTokenizer tokenizer = new StringTokenizer(myText, " ,!", true);
        StringTokenizer tokenizerWithout = new StringTokenizer(myText, " ,!");
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        while (tokenizer.hasMoreElements()){
            System.out.println("Element: " + tokenizer.nextElement());
        }
        System.out.println("======= without delims ============");
        while (tokenizerWithout.hasMoreElements()){
            System.out.println("Element: " + tokenizerWithout.nextElement());
        }
    }

    public void testStrBuilder(){
        System.out.println(System.currentTimeMillis());
        System.out.println("=====================");
        StringBuilder strB = new StringBuilder();
        String textStr = new String("test");
        for (int i = 0; i < 10000000; i++){
            strB.append("test-");
//            textStr = textStr + "test-";
        }
        String subStr = strB.toString().substring(0,10);
        System.out.println();
        System.out.println(System.currentTimeMillis());

        for (char c : subStr.toCharArray()){
            System.out.println("char: " + c);
        }
    }

    public void reverseWords(){
        String myText = "bei Zalando zu arbeiten, wäre cool!";
        System.out.println("Text: " + myText);
//        System.out.println("Reverse text: " + new StringBuilder(myText).reverse());
        Stack<String> myStack = new Stack<>();
        StringTokenizer textTokens = new StringTokenizer(myText, " ,!");
        while (textTokens.hasMoreElements()){
            myStack.push(textTokens.nextToken());
        }

        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> textIterator = myStack.iterator();
        int amountOfWords = 0;
//        while (textIterator.hasNext()){
//            stringBuilder.append(textIterator.next()).append(" ");
//            amountOfWords++;
//        }
        int textLength = myStack.size();
        for (int i = 0; i < textLength; i++){
            stringBuilder.append(myStack.pop()).append(" ");
            amountOfWords++;
        }

        System.out.println("stack reversed sentence: " + stringBuilder.toString() + " : with words: " + amountOfWords);
    }
}
