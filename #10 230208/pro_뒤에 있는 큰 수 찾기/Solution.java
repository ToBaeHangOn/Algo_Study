import java.io.IOException;
import java.util.Stack;
public class Solution {
    static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        /*
        if 스택이 비어있거나 이전 숫자보다 작을 때 현재 인덱스를 push
        else 현재 숫자보다 작지 않을 때 까지 pop하고 해당 인덱스 답에 현재 숫자를 넣음
        스택에 남아있는 값은 더 큰 숫자를 찾지 못한 것이므로 -1
        */
        for(int i = 0; i < numbers.length; i++){
            if(stack.isEmpty() || numbers[i] < numbers[i - 1]){
                stack.push(i);
            }
            else{
                while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){
                    answer[stack.pop()] = numbers[i];
                }
                stack.push(i);
            }
        }
        while(!stack.isEmpty()){
            answer[stack.pop()] = -1;
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        int[] numbers = {2, 1, 3, 5};
        System.out.println(solution(numbers));
    }
}
