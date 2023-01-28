import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int[] dice, order; // 주사위, 배치순서
    private static Node[] horse; // 4개의 말
    private static Node start; // 시작지점
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dice = new int[10 + 1];
        order = new int[10 + 1];
        horse = new Node[4 + 1];

        for (int i = 1; i <= 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        start = new Node(0);
        Node temp = start;
        for (int i = 2; i <= 40; i += 2) {
            temp = temp.addNext(i);
        }

        // 도착점
        Node end = temp.addNext(0);
        end.isFinish = true;
        end.next = end;

        Node crossroads = new Node(25);

        temp = crossroads.addNext(30);
        temp = temp.addNext(35);
        temp.next = Node.getNode(start, 40);

        temp = Node.getNode(start, 10);
        temp = temp.fastPath = new Node(13);
        temp = temp.addNext(16);
        temp = temp.addNext(19);
        temp.next = crossroads;

        temp = Node.getNode(start, 20);
        temp = temp.fastPath = new Node(22);
        temp = temp.addNext(24);
        temp.next = crossroads;

        temp = Node.getNode(start, 30);
        temp = temp.fastPath = new Node(28);
        temp = temp.addNext(27);
        temp = temp.addNext(26);
        temp.next = crossroads;

        perm(1);
        System.out.println(answer);
    }

    static int gameStart() {
        Arrays.fill(horse, start);

        int score = 0;
        for (int i = 1; i <= 10; i++) {
            Node curr = horse[order[i]];
            curr.isEmpty = true;

            for (int j = 1; j <= dice[i]; j++) {
                // 파란 길일때
                if (j == 1 && curr.fastPath != null) {
                    curr = curr.fastPath;
                } else curr = curr.next;
            }

            horse[order[i]] = curr; // 이동 말 위치

            if (!curr.isEmpty && !curr.isFinish) {
                // 이동을 마친칸에 다른 말이 있다면, 해당 말은 고를 수 없다.
                score = 0; // 주사위 점수 초기화
                break;
            } else {
                curr.isEmpty = false;
                score += curr.score;
            }
        } // 게임 종료

        for (int i = 1; i <= 4; i++)
            horse[i].isEmpty = true;
        return score;
    }

    static void perm(int depth) {
        if (depth >= 11) {
            answer = Math.max(answer, gameStart());
            return;
        }
        for (int i = 1; i <= 4; i++) {
            order[depth] = i;
            perm(depth + 1);
        }
    }

    static class Node {
        int score;
        boolean isEmpty, isFinish;
        Node next;
        Node fastPath;

        public Node(int score) {
            this.score = score;
            this.isEmpty = true;
        }

        // 노드 연결
        public Node addNext(int score) {
            Node nextNode = new Node(score);
            this.next = nextNode;
            return nextNode;
        }

        // 노드 찾기 (지름길 놓는 지점을 찾기 위한 함수)
        public static Node getNode(Node start, int target) {
            Node temp = start;
            while (true) {
                if (temp == null) return null;
                if (temp.score == target) return temp;
                temp = temp.next;
            }
        }
    }
}
