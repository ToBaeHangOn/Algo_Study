import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static List<List<Integer>> tree;
    static int N, u, v;
    static String str;
    static boolean visited[];
    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        str = " " + br.readLine();
        tree = new ArrayList<>();
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++){
            tree.add(new ArrayList<>());
        }
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
    }
    static void solve(){
        // 사전상 마지막에 오는 단어를 가진 노드를 찾아 그 노드만 탐색하며 핸들을 만들어준다.
        Queue<Integer> queue = new LinkedList<>();
        visited[1] = true;
        queue.offer(1);
        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(str.charAt(cur));
            for(int next : tree.get(cur)){
                if(visited[next]) continue;
                visited[next] = true;
                // 현재 큐가 비어있으면 일단 추가
                if(queue.isEmpty()) queue.offer(next);
                // 큐에 들어있는 값보다 next값이 사전상 후순위라면 큐에 들어있는 값 교체
                else if(str.charAt(queue.peek()) < str.charAt(next)) {
                    queue.poll();
                    queue.offer(next);
                }
            }
        }
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

