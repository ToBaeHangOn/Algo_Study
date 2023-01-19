import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int M;
    static int[] indegree;
    static List<List<Integer>> list = new ArrayList<>();
    static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A, B;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
        }
        indegree = new int[N + 1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            list.get(A).add(B);
            indegree[B]++;
        }
    }
    static void solve(){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0) pq.offer(i);
            // 먼저 풀어야 할 문제가 없는 문제들은 우선순위 큐에 offer
        }
        while(!pq.isEmpty()){
            int cur_node = pq.poll();
            sb.append(cur_node).append(" ");
            List<Integer> next_list = list.get(cur_node);
            for(int i = 0; i < next_list.size(); i++){
                int next_node = next_list.get(i);
                indegree[next_node]--;
                if(indegree[next_node] == 0) pq.offer(next_node);
            }
        }
    }
    static public void main(String[] args) throws IOException{
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
