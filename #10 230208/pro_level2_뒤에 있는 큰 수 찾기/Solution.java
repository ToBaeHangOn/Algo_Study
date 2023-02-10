import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        // 1. 거꾸로 시작해서 탐색
//        for (int i = numbers.length - 1; i >= 0; i--) {
//            if (i == numbers.length - 1) {
//                stack.push(-1);
//                max = numbers[i];
//            } else {
//                if (numbers[i] < numbers[i + 1]) {
//                    max = Math.max(max, numbers[i + 1]);
//                    stack.push(numbers[i + 1]);
//                } else if (numbers[i] >= max) {
//                    max = numbers[i];
//                    stack.push(-1);
//                } else {
//                    stack.push(max);
//                }
//            }
//        }
        // 2. 이중 for문으로 탐색
//        int maxIndex = numbers.length - 1;
//        for (int i = numbers.length - 1; i >= 0; i--) {
//            if (i == numbers.length - 1) {
//                stack.push(-1);
//                max = numbers[i];
//            } else {
//                boolean isAdded = false;
//                for (int j = i; j <= maxIndex; j++) {
//                    if (numbers[i] < numbers[j]) {
//                        stack.push(numbers[j]);
//                        if (max <= numbers[j]) {
//                            max = numbers[j];
//                            maxIndex = j;
//                        }
//                        isAdded = true;
//                        break;
//                    }
//                }
//                if (!isAdded)
//                    stack.push(-1);
//            }
//        }


        //
//        int index = 0;
//        while (!stack.isEmpty()) {
//            answer[index++] = stack.pop();
//        }
        //3. 스택을 이용해 탐색
        answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            if (stack.isEmpty() || numbers[i] < numbers[i - 1]) {
                // 스택이 비어있거나 (index == 0) 이전 숫자가 지금 숫자보다 클 경우
                // 아직 정답을 확신할 수 없으므로 스택에 저장.
                stack.push(i);
            } else {
                // 스택의 최상단 인덱스에 해당하는 숫자가 현재 인덱스의 숫자보다 작을 때
                // ex) i = 0, number[0] = 2 -> i = 1, number[1] = 3
                // 현재까지의 인덱스에서 가장 가까운 큰 수는 현재 number[i] 이므로
                while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                    // 스택에서 뽑아주며 answer를 채운다.
                    answer[stack.pop()] = numbers[i];
                }
                // 그 후 현재 인덱스는 아직 못정했으므로 스택에 저장
                stack.push(i);
            }
        }
        // 만약, 모두 정해졌음에도 스택에 남아있을 경우, 해당하는 인덱스의 숫자보다 큰 가까운 수가 없으므로
        // -1로 해당 인덱스를 채워준다.
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }


        return answer;
    }
}