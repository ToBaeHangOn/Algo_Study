import java.io.IOException;
import java.util.*;

public class Solution {
    static List<List<Integer>> graph;
    static int[] indgree;
 //   static boolean[] isSafe;
    static int solution(int n, int[][] lighthouse) {
        int answer = 0;
        init(n, lighthouse);
        answer = bfs();
        return answer;
    }
    static void init(int n, int[][] lighthouse){
        int a, b;
        graph = new ArrayList<>();
        indgree = new int[n + 1];
    //    isSafe = new boolean[n + 1];
        for(int i = 0; i < n + 1; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < lighthouse.length; i++){
            a = lighthouse[i][0];
            b = lighthouse[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
            indgree[a]++;
            indgree[b]++;
        }
        System.out.println(graph.size());
    }
    static int bfs(){
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i < graph.size(); i++){
            if(indgree[i] == 1){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int pre = q.poll();
        //    if(isSafe[pre]) continue;
            if(graph.get(pre).size() == 0) continue;
            int cur = graph.get(pre).get(0); // 차수가 1인 노드만 넣었으므로 탐색할 가치가 있는 유일한 노드
            if(graph.get(cur).size() == 0) continue;
        //    isSafe[cur] = true;
            cnt++;
            for(int i = 0; i < graph.get(cur).size(); i++){
                int next = graph.get(cur).get(i);
         //       isSafe[next] = true;
                graph.get(next).remove(Integer.valueOf(cur));
                indgree[next]--;
                if(indgree[next] == 1) q.offer(next);
            }
            graph.get(cur).clear();
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        int n = 10;
        int[][] lighthouse = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
        System.out.println(solution(n, lighthouse));
    }
}
