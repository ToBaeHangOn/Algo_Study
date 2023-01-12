import java.io.IOException;
import java.util.*;

public class Solution {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] depth;
    static int max;
    public static void main(String[] args) throws IOException {
        int n = 6;
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        solution(n, vertex);
    }

    static int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        depth = new int[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < edge.length; i++){
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        bfs(1);

        for(int i = 1; i <= n; i++){
            if(depth[i] == max) answer++;
        }
        return answer;
    }
    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        depth[start] = 1;
        max = 1;
        visited[start] = true;

        while(!q.isEmpty()){
            int node = q.poll();
            for(int i = 0; i < graph[node].size(); i++){
                int next = graph[node].get(i);
                if(!visited[next]){
                    visited[next] = true;
                    depth[next] = depth[node] + 1;
                    max = Math.max(max, depth[next]);
                    q.add(next);
                }
            }
        }
    }
}
