package challenges;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

public class SetOfStacks  {
    private Stack<String>[] stacks;

    private int threashold = 0;
    private int currentPosInStack = 0;
    private Stack<String> currentStack;
    private int currentIndxOfStack = -1;

    public SetOfStacks(int threashold) {
        this.threashold = threashold;
        this.stacks = new Stack[threashold];
        Arrays.stream(stacks)
                .map(stack -> { stack = new Stack<>(); return stack;})
                .collect(Collectors.toList()).toArray(stacks);

        this.currentStack = nextStack();
    }

    public int getThreashold() {
        return threashold;
    }

    public void setThreashold(int threashold) {
        this.threashold = threashold;
        if (this.stacks.length < threashold){
            Stack[] tmp = this.stacks;
            this.stacks = new Stack[threashold];
            System.arraycopy(tmp,0, this.stacks, 0, tmp.length);
        }
    }

    public void push(String item){
        if (currentPosInStack == threashold){
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
            currentPosInStack = threashold;
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
                .filter(elem -> elem.size() < threashold)
                .findFirst();

        return stack.orElseThrow(() -> new RuntimeException("no stacks contained"));
    }


}
