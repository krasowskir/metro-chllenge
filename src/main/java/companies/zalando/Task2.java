package companies.zalando;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 {

    public int[] solution(int counterArrayNumbers, int[] operationArray){
//        operationArray = [3,4,4,6,1,4,4]
        int[] counters = prepareCounters(counterArrayNumbers);
//        for (int operation : operationArray){
//            counters = applyOperation(counters,operation,counterArrayNumbers);
////            System.out.println(Arrays.toString(counters));
//        }
//        return counters;
        List<Integer> maxValuesIndexes = positionsOfMaxVal(operationArray, counterArrayNumbers);
        List<Range> rangeList = determineRanges(maxValuesIndexes);
        int[] sortedArray = sortRangeArray(operationArray, rangeList);

        return sortedArray;
    }

    private  List<Range>  determineRanges(List<Integer> maxValuesIndexes) {
        if (maxValuesIndexes.size() > 0){
            List<Range> rangeList = new ArrayList<>();
            int fromOrigin = 0;
            for (int i : maxValuesIndexes){

                rangeList.add(new Range(fromOrigin, i));
                fromOrigin = i;
            }
            return rangeList;
        }
        return Collections.emptyList();
    }

    private int[] prepareCounters(int counterArrayNumbers) {
        int[] countersArray = new int[counterArrayNumbers];
        Arrays.fill(countersArray, 0);
        return countersArray;
    }

    private int[] applyOperation(int[] counters, int operation, int nValue){
        if (operation >= 1 && operation <= nValue){
            counters[operation-1] = ++(counters[operation-1]);
        } else  { //(operation == nValue + 1)
            int tmpMaxValue = Arrays.stream(counters).max().getAsInt();
            Arrays.fill(counters, tmpMaxValue);
        }
        return counters;
    }

    private List<Integer> positionsOfMaxVal(int[] counters, int nValue){
        int maxValue = nValue + 1;
        List<Integer> intList = Arrays.stream(counters).boxed().collect(Collectors.toList());
        List<Integer> maxInd = new ArrayList<>();
        long amountOfMax = Arrays.stream(counters).filter(x -> x == maxValue).count();

        for (int i = 0; i < amountOfMax; i++){
            int firstMaxValInd = intList.indexOf(maxValue);
            maxInd.add(firstMaxValInd);
            intList.remove(firstMaxValInd);
        }

        return maxInd;
    }

    private class Range {
        public Range() { }
        private int from;
        private int to;

        public Range(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }
    }

    private int[] sortRangeArray(int[] arrayToSort, List<Range> rangeList){
        int[] tmpArrCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        if (rangeList.size() > 0){
            Arrays.sort(tmpArrCopy,rangeList.get(rangeList.size()-1).getTo()+1,tmpArrCopy.length);
            for (Range range : rangeList){
                Arrays.sort(tmpArrCopy,range.getFrom(),range.getTo());
            }
        } else {
            Arrays.sort(tmpArrCopy);
        }
        return tmpArrCopy;
    }
}
