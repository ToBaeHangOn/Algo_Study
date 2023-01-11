import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            mat.add(new ArrayList<>());
        }
        for (int[] ints : edge) {
            mat.get(ints[0]).add(ints[1]);
            mat.get(ints[1]).add(ints[0]);
        }
        // 정점 방문 체크
        boolean[] visit = new boolean[n + 1];
        // 정점 길이 저장
        int[] distance = new int[n + 1];
        visit[1] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            ArrayList<Integer> node = mat.get(curr);
            for (Integer integer : node) {
                // 방문하지 않았다면
                if (!visit[integer]) {
                    queue.add(integer);
                    visit[integer] = true;
                    // 길이에 저장
                    distance[integer] = distance[curr] + 1;
                }
            }
        }
        // 길이순으로 정렬
        Arrays.sort(distance);
        // 제일 마지막 노드가 가장 먼 노드이므로
        int max = distance[distance.length - 1];
        // 가장 먼 노드 찾기
        for (int j : distance) {
            if (j == max)
                answer++;
        }
        return answer;
    }
}