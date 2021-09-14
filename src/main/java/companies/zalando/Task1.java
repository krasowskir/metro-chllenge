package companies.zalando;

import java.util.*;

public class Task1 {
    class Solution {

        public int solution(String inputStr){
            HashMap<Character, Integer> lettersCount = countLettersInString(inputStr);
            HashMap<Character, Integer> filteredOutLettersMap = filterOutEmptyChars(lettersCount);
            return sortMap(filteredOutLettersMap);
        }

        public HashMap<Character, Integer>  countLettersInString(String input){
            char[] tmpStr = input.toCharArray();
            Arrays.sort(tmpStr);
            HashMap<Character, Integer> lettersCount = provideEmptyLettersCount();
            for (char elem : tmpStr){
                int amount = lettersCount.get(elem);
                lettersCount.put(elem,++amount);
            }
            return lettersCount;
        }

        HashMap<Character, Integer> provideEmptyLettersCount(){
            HashMap<Character, Integer> letterCount = new HashMap<>();
            for (char letter = 'a'; letter <= 'z'; letter++){
                letterCount.put(letter,0);
            }
            return letterCount;
        }

        HashMap<Character, Integer> filterOutEmptyChars(HashMap<Character, Integer> fromMap){
            HashMap<Character, Integer> flattenedCharArr = new HashMap<>();
            for (Map.Entry<Character, Integer> entry : fromMap.entrySet()){
                if (entry.getValue() > 0){
                    flattenedCharArr.put(entry.getKey(), entry.getValue());
                }
            }
            return flattenedCharArr;
        }


        int sortMap(HashMap<Character, Integer> unsortedMap){

            Object[] sortedArray = unsortedMap.values().toArray();
            Arrays.sort(sortedArray);

            List<Integer> uniqueNumbrs = new ArrayList<Integer>();
            int removeNum = 0;
            for (Object elem : sortedArray){
                Integer intVal = (Integer)elem;
                if (!containsElem(uniqueNumbrs, intVal)){
                    uniqueNumbrs.add(intVal);
                } else {
                    for (int i = 0; i < intVal; i++){
                        int tmpInsertNum = intVal - i;
                        if (!containsElem(uniqueNumbrs, tmpInsertNum)){
                            uniqueNumbrs.add(tmpInsertNum);
                            break;
                        }
                        removeNum++;
                    }
                }
            }
            return removeNum;
        }

        public boolean containsElem(List<Integer> arr, Integer elem){
            return arr.contains(elem);
        }
    }
}
