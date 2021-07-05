package com.example.metrochllenge;

public class StringCombiner {

    private StringBuilder stringBuilder = new StringBuilder();
    private String prefix = "[";
    private String delim = ", ";

    public StringCombiner add(String elem){
        if (areAtStart()){
            stringBuilder.append(prefix);
        } else {
            stringBuilder.append(delim);
        }
        stringBuilder.append(elem);
        return this;
    }

    public StringCombiner merge(StringCombiner other){
        stringBuilder.append(other.stringBuilder);
        return this;
    }

    private boolean areAtStart(){
        return stringBuilder.length() == 0;
    }
}
