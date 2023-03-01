import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node first = null;
        Node last = null;
        Node current = null;
        Stack<Node> stack = new Stack<>();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            // 가장 마지막 노드 제거
            if (command == 3) {
                if (stack.size() != 0) {
                    // 1개 일 때
                    if (stack.size() == 1 && first == last) {
                        stack.pop();
                        first = null;
                        last = null;
                        current = null;
                    } else {
                        // 맨 앞 일 때
                        if (first == current) {
                            first = first.end;
                            first.front = null;
                        }
                        // 맨 뒤 일 떄
                        else if (last == current) {
                            last = last.front;
                            last.end = null;
                        }
                        // 그냥 일 때
                        else {
                            Node f = current.front;
                            Node e = current.end;
                            f.end = e;
                            e.front = f;
                        }
                        stack.pop();
                        current = stack.peek();
                    }
                }

            } else {
                char temp = st.nextToken().charAt(0);
                Node newNode = new Node(temp);
                // 맨 뒤에 추가
                if (command == 1) {
                    // 처음 입력이 1일 경우
                    if (first == null)
                        first = newNode;

                    if (last != null) {
                        last.end = newNode;
                        newNode.front = last;
                    }
                    last = newNode;
                }
                // 맨 앞에 추가
                else if (command == 2) {
                    // 처음 입력이 2일 경우
                    if (last == null)
                        last = newNode;

                    if (first != null) {
                        first.front = newNode;
                        newNode.end = first;
                    }
                    first = newNode;
                }
                current = newNode;
                stack.push(newNode);
            }
        }
        Node index = first;
        StringBuilder sb = new StringBuilder();
        while (index != null) {
            sb.append(index.c);
            index = index.end;
        }
        if (sb.toString().equals(""))
            System.out.println(0);
        else
            System.out.println(sb);
    }

    static class Node {
        Node front, end;
        char c;

        public Node(char c) {
            this.c = c;
        }
    }
}
