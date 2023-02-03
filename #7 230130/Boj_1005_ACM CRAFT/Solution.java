import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int[] times, indegree;
    static int N, K, W;
    static List<List<Integer>> map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            // 건물의 개수
            N = Integer.parseInt(st.nextToken());
            // 건설 순서 규칙 개수
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            // 건설 시간 배열
            times = new int[N + 1];
            // 현재 노드를 가리키는 노드의 개수 배열
            indegree = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }
            map = new ArrayList<>();
            for (int i = 0; i <= N + 1; i++) {
                map.add(new ArrayList<>());
            }
            // 간선 연결
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                map.get(from).add(to);
                indegree[to]++;
            }
            // 목표 건물 번호
            W = Integer.parseInt(br.readLine());
            sb.append(topologySort(W)).append('\n');
        }
        System.out.println(sb);
    }

    static int topologySort(int W) {
        Queue<Integer> queue = new LinkedList<>();

        int[] result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            // 가리키는 노드의 개수가 0이면
            if(indegree[i] == 0) {
                // 걸리는 시간은 그 자체가 된다.
                result[i] = times[i];
                queue.add(i);
            }
        }

        // 큐는 가리키는 노드의 개수가 없는 경우를 계속 저장한다.
        // 위상정렬된 그래프를 탐색하며 가리키는 노드가 모두 0이 될 때까지 탐색을 반복한다.
        // 가리키는 노드가 여러개의 경우, 그 중 걸리는 시간이 큰 것이 더해진다.
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for(Integer i : map.get(curr)) {
                if(map.get(curr).contains(i)){
                    result[i] = Math.max(result[i], result[curr] + times[i]);
                    indegree[i]--;

                    if (indegree[i] == 0) {
                        queue.offer(i);
                    }
                }

            }

        }
        return result[W];
    }
}
