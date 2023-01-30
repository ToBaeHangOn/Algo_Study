import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    static int[][] map;
    static int N, K, L, time;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static LinkedList<int[]> snake;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        snake = new LinkedList<>();
        snake.add(new int[]{1, 1});
        map = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }
        HashMap<Integer, Character> dir = new HashMap<>();
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char y = st.nextToken().charAt(0);
            dir.put(x, y);
        }
        time = 0;
        int nowDir = 0;
        int x = 1;
        int y = 1;
        a:
        while (true) {
            time++;
            int nx = x + dx[nowDir];
            int ny = y + dy[nowDir];

            if (nx < 1 || ny < 1 || nx >= N + 1 || ny >= N + 1) {
                break;
            } // 범위를 벗어나면 while문 break;

            for (int i = 0; i < snake.size(); i++) {

                if (nx == snake.get(i)[1] && ny == snake.get(i)[0]) {
                    break a;
                }
            } // 뱀이 벽이나 자신의 몸에 머리가 부딛히면 함수 종료

            if (map[ny][nx] == 1) { // 사과일 경우
                map[ny][nx] = 0; // 사과를 먹는다
                snake.add(new int[]{ny, nx}); // 뱀의 길이가 길어짐
            } else {
                snake.add(new int[]{ny, nx}); // 뱀이 움직임
                snake.remove(0); // 원래 있던 꼬리 부분을 없앰
            }

            x = nx; // 현재 이동 좌표
            y = ny; // 현재 이동 좌표

            if (dir.containsKey(time)) { // dir에 time과 같은 경우가 있으면
                if (dir.get(time) == 'D') { // D일 경우
                    nowDir++; // 오른쪽으로 턴
                    if (nowDir == 4) { // 다시 0으로
                        nowDir = 0;
                    }
                } else if (dir.get(time) == 'L') { // L일 경우
                    nowDir--; // 왼쪽으로 턴
                    if (nowDir == -1) { // 다시 3으로
                        nowDir = 3;
                    }
                }
            }
        }
        System.out.println(time);
    }
}
