import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static Map<Integer, Integer> ladder;
    static int[] dice = {1,2,3,4,5,6};
    static int[] visited = new int[101];
    static int N, M, x, y;
    static void input() throws IOException{
        ladder = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Arrays.fill(visited, -1);
        for(int i = 0; i < N + M; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            ladder.put(x, y);
        }
    }
    static void solve(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < 6; i++) {
                int next = cur + dice[i];
                if (next > 100) continue;
                if (ladder.containsKey(next)) { // 사다리 또는 뱀에 걸렸을 때
                    next = ladder.get(next);
                }
                if (visited[next] != -1) continue;
                visited[next] = visited[cur] + 1; // next까지 주사위 던진 횟수로 방문처리
                queue.offer(next);
            }
        }
        sb.append(visited[100]);
    }
    public static void main(String[] argrs) throws IOException {
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
