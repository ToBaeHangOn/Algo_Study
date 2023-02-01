import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static boolean[] visited;
    static ArrayList<Integer>[] map;
    static int result;

    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            map[lighthouse[i][0]].add(lighthouse[i][1]);
            map[lighthouse[i][1]].add(lighthouse[i][0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        // 리스트의 사이즈가 1이면 연결된 등대가 1개이다.
        // 1개인 놈들은 무조건 자기자신보다 연결된 등대를 켜는 것이 유리하다.
        for (int i = 1; i <= n; i++) {
            if (map[i].size() == 1)
                queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int num = queue.poll();
            // 사이즈가 0이면 연결된 것을 모두 탐색해서(remove) continue
            if (map[num].size() == 0) continue;
            // 켤 등대를 선택(연결된 등대)
            int turnOn = map[num].get(0);
            // 연결된 등대도 다 탐색을 했다면 continue
            if (map[turnOn].size() == 0) continue;
            for (int i = 0; i < map[turnOn].size(); i++) {
                // 다음에 탐색할 등대 선택
                int next = map[turnOn].get(i);
                // 현재 등대를 다음 등대에서 제거
                map[next].remove(map[next].indexOf(turnOn));
                // 만약 지웠는데도 탐색할 등대가 있다면 큐에 넣고 탐색
                if (map[next].size() == 1) queue.offer(next);
            }
            // 현재 켜진 등대에 연결된 모든 등대를 탐색했으므로 모두 제거
            map[turnOn].clear();
            answer++;
        }
        return answer;

//        visited = new boolean[n + 1];
//        result = Integer.MAX_VALUE;
////        rec(n, 0, 0);
//        answer = result;
//        return answer;
    }

//    static void rec(int n, int cnt, int deungdae) {
//        if (cnt == n) {
//            int count = deungdae;
//            if (count >= result)
//                return;
//            boolean[] temp = new boolean[n + 1];
//            for (int i = 1; i <= n; i++)
//                temp[i] = visited[i];
//            for (int i = 1; i <= n; i++) {
//                if (visited[i]) {
//                    for (int j = 0; j < map[i].size(); j++) {
//                        if (!temp[map[i].get(j)]) {
//                            temp[map[i].get(j)] = true;
//                            count++;
//                        }
//                    }
//                }
//            }
//            if (count == n) {
//                result = Math.min(result, deungdae);
//                return;
//            }
//            return;
//        }
//        visited[cnt + 1] = true;
//        rec(n, cnt + 1, deungdae + 1);
//        visited[cnt + 1] = false;
//        rec(n, cnt + 1, deungdae);
//
//    }

}