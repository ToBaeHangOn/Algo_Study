import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static List<Integer>[] lists;
    static boolean[] visited;
    static int n, m, x, y, a, b, level;
    static void input() throws IOException{
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        lists = new List[n + 1];
        for(int i = 1; i <= n; i++){
            lists[i] = new ArrayList<>();
        }
        visited = new boolean[n + 1];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            lists[x].add(y);
            lists[y].add(x);
        }
    }
    static void solve(){
        bfs(a);
        if(!visited[b]) sb.append(-1);
        else sb.append(level);
    }
    static void bfs(int start){
        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(start);
        visited[start] = true;
        level = 1;

        while(!que.isEmpty()){
            int que_size = que.size();
            for(int i = 0; i < que_size; i++) {
                int cur_node = que.poll();
                for (int j = 0; j < lists[cur_node].size(); j++) {
                    int next_node = lists[cur_node].get(j);
                    if (next_node == b) {
                        visited[next_node] = true;
                        return;
                    }
                    if (!visited[next_node]) {
                        visited[next_node] = true;
                        que.offer(next_node);
                    }
                }
            }
            level++;
        }
    }
    public static void main(String[] argrs) throws IOException{
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
