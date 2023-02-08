import java.util.Stack;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            answer[i] = calc(s[i]);
        }

        return answer;
    }

    static String calc(String str) {
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            stack.push(c);
            // 3개 이상이면 110 검사
            if (stack.size() >= 3) {
                char zero = stack.pop();
                if (zero != '0') {
                    stack.push(zero);
                    continue;
                }
                char one2 = stack.pop();
                if (one2 != '1') {
                    stack.push(one2);
                    stack.push(zero);
                    continue;
                }
                char one1 = stack.pop();
                if (one1 != '1') {
                    stack.push(one1);
                    stack.push(one2);
                    stack.push(zero);
                    continue;
                }
                count++;
            }
        }

        StringBuilder sb = new StringBuilder();
        int index = stack.size();
        boolean isZero = false;
        // 110을 옮기는 유리한 조건은 가장 먼저 발견된 0 뒤에 붙이는 것이므로
        // 가장 먼저 오는 0을 찾는다.
        while (!stack.isEmpty()) {
            char c = stack.pop();
            if (!isZero && c == '1')
                index--;
            if (c == '0')
                isZero = true;
            sb.insert(0, c);
        }

        // count만큼 110을 붙여준다.
        for (int i = 0; i < count; i++)
            sb.insert(index, "110");

        return sb.toString();
    }
}