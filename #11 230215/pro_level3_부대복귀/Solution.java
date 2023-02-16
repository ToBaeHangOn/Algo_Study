import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 전형적인 다익스트라
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int start = road[0];
            int end = road[1];

            // 방향이 없으므로 양방향 연결
            map.get(start).add(end);
            map.get(end).add(start);
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // 다익스트라
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(destination);
        // 목적지에서 목적지는 0
        distance[destination] = 0;

        while (!pq.isEmpty()) {
            int curr = pq.poll();

            for (int i = 0; i < map.get(curr).size(); i++) {
                int now = map.get(curr).get(i);
                // now(맵에서 뽑은 지역)의 거리보다 curr의 거리가 더 작은 경우 갱신
                if (distance[now] > distance[curr] + 1) {
                    distance[now] = distance[curr] + 1;
                    pq.add(now);
                }
            }
        }

        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            // 만약 맥스밸류가 그대로일 경우, 복귀가 불가능하므로 -1
            answer[i] = distance[sources[i]] < Integer.MAX_VALUE ? distance[sources[i]] : -1;
        }

        return answer;
    }
}