import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static List<List<Node>> list, revList;
    static int N, M, X;
    static int INF = Integer.MAX_VALUE;
    static class Node implements Comparable<Node>{
        int index, distance;
        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
        public int compareTo(Node n) {
            return this.distance - n.distance;
        }
    }
    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        revList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            revList.add(new ArrayList<>());
        }

        int u, v, dist;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            list.get(u).add(new Node(v, dist));
            revList.get(v).add(new Node(u, dist));
            // 특정 노드에서 X까지 가는 거리를 찾을 때 X 기준으로 찾기 위해 역방향 List를 만들어줌
        }
    }
    static void solve(){
        int[] dists = new int[N + 1];
        int[] revDists = new int[N + 1];
        Arrays.fill(dists, INF);
        Arrays.fill(revDists, INF);
        dijkstra(X, list, dists);
        dijkstra(X, revList, revDists);
        // X를 기준으로 왕복하는 거리를 구해야 하므로
        // X에서 특정 노드까지 가는 최단거리와
        // 특정 노드에서 X까지 가는 최단거리를 둘 다 구해야한다.
        int max = -1;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dists[i] + revDists[i]);
        }
        sb.append(max);
    }
    static void dijkstra(int start, List<List<Node>> list, int[] dist){
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 거리가 가까운 노드부터 탐색하기 위해 PriorityQueue 사용
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            int idx = pq.poll().index;
            if (visited[idx]) continue;
            visited[idx] = true;
            for (Node node : list.get(idx)) {
                if (dist[node.index] > dist[idx] + node.distance) {
                    dist[node.index] = dist[idx] + node.distance;
                    pq.add(new Node(node.index, dist[node.index]));
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
