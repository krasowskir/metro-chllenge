package challenges;

import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.Stack;
import java.util.stream.Collectors;

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
 and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.


Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


Constraints:

    The number of nodes in each linked list is in the range [1, 100].
    0 <= Node.val <= 9
    It is guaranteed that the list represents a number that does not have leading zeros.


 */
public class AddToNumbers {

    public ListNode testListNode(){
        ListNode ln1 = new ListNode(2);
        ListNode ln2 = new ListNode(4);
        ListNode ln3 = new ListNode(3);
        ln1.next = ln2;
        ln2.next = ln3;

        ListNode ln4 = new ListNode(5);
        ListNode ln5 = new ListNode(6);
        ListNode ln6 = new ListNode(4);

        ln4.next = ln5;
        ln5.next = ln6;

        return addTwoNumbers(ln1, ln4);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger val1 = convertValue(l1);
        BigInteger val2 = convertValue(l2);
        BigInteger result =  val1.add(val2);
        ListNode ln = convertFromIntToListNode(result);
        return ln;
    }

    private BigInteger convertValue(ListNode ln){
        Stack<Integer> number1Stack = new Stack<>();
        StringBuilder strB = new StringBuilder();
        do {
            number1Stack.push(ln.val);
            ln = ln.next;
        } while (ln != null);
        int length = number1Stack.size();
        for (int value = 0; value < length; value++){
          strB.append(number1Stack.pop());
        }

        return new BigInteger(strB.toString());
    }

    private ListNode convertFromIntToListNode(BigInteger val){
        ListNode currentListNode = null;
        ListNode beginningOfLinkedList = null;
        Stack<Integer> numberStack = convertIntegerValIntoStack(val);
        int length = numberStack.size();
        for (int i = 0; i < length; i++){
            if (currentListNode == null){
                currentListNode = new ListNode();
                beginningOfLinkedList = currentListNode;
                currentListNode.val = numberStack.pop();
            } else {
                ListNode ln = new ListNode(numberStack.pop());
                currentListNode.next = ln;
                currentListNode = currentListNode.next;
            }
        }
        return beginningOfLinkedList;
    }

    private Stack<Integer> convertIntegerValIntoStack(BigInteger val) {
        Stack<Integer> numberStack = new Stack<>();

        CharBuffer.wrap(String.valueOf(val).toCharArray())
                .chars()
                .mapToObj(i -> (char)i)
                .map(elem -> numberStack.push(Character.getNumericValue(elem)))
                .collect(Collectors.toList());
        return numberStack;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() { }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
