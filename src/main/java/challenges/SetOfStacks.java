package challenges;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

public class SetOfStacks  {
    private Stack<String>[] stacks;

    private int threshold = 0;
    private int currentPosInStack = 0;
    private int currentIndxOfStack = 0;
    private Stack<String> currentStack;

    public SetOfStacks(int threshold) {
        this.threshold = threshold;
        this.stacks = new Stack[threshold];
        Arrays.stream(stacks)
                .map(stack -> { stack = new Stack<>(); return stack;})
                .collect(Collectors.toList()).toArray(stacks);

        this.currentStack = stacks[0];
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
        if (this.stacks.length < threshold){
            Stack[] tmp = this.stacks;
            this.stacks = new Stack[threshold];
            System.arraycopy(tmp,0, this.stacks, 0, tmp.length);
        }
    }

    public void push(String item){
        if (currentPosInStack == threshold){
            this.currentStack = nextStack();
            currentPosInStack = 0;
        }
        this.currentStack.push(item);
        this.currentPosInStack = this.currentPosInStack +1;
    }

    public String pop(){
        if (currentPosInStack == 0){
            this.currentIndxOfStack = this.currentIndxOfStack -1;
            currentStack = stacks[currentIndxOfStack];
            currentPosInStack = threshold;
        }
        this.currentPosInStack = this.currentPosInStack -1;
        return currentStack.pop();
    }

    public int getCurrentPosInStack() {
        return currentPosInStack;
    }

    public int getCurrentIndxOfStack() {
        return currentIndxOfStack;
    }

    private Stack<String> nextStack(){
        this.currentIndxOfStack = this.currentIndxOfStack + 1;
        Optional<Stack<String>> stack = Arrays.stream(stacks)
                .filter(Objects::nonNull)
                .filter(elem -> elem.size() < threshold)
                .findFirst();

        return stack.orElseThrow(() -> new RuntimeException("no stacks contained"));
    }


}
