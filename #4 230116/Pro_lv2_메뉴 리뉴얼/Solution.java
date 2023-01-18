import java.util.*;

class Solution {
    static HashMap<String, Integer> map;
    static int m;

    public String[] solution(String[] orders, int[] course) {
        // 알파벳 순으로 정렬해야 하므로 PQ
        PriorityQueue<String> pq = new PriorityQueue<>();

        // 각각의 코스 개수에 대해 반복
        for (int i = 0; i < course.length; i++) {
            // hashMap을 초기화
            map = new HashMap<>();
            // max 값 초기화
            m = 0;

            for (int j = 0; j < orders.length; j++) {
                // 조합으로 찾기
                find(0, "", course[i], 0, orders[j]);
            }

            for (String s : map.keySet()) {
                // 최댓값이랑 같다 = 넣어야 한다
                if (map.get(s) == m && m > 1) {
                    pq.offer(s);
                }
            }
        }
        // pq의 사이즈 만큼 배열 생성
        String ans[] = new String[pq.size()];
        int k = 0;
        while (!pq.isEmpty()) {
            ans[k++] = pq.poll();
        }
        return ans;
    }

    // 알파벳을 조합해 요리 코스를 만듬
    static void find(int cnt, String str, int targetNum, int idx, String word) {
        if (cnt == targetNum) {
            char[] c = str.toCharArray();
            // 순서대로 정렬
            Arrays.sort(c);

            String temps = "";
            // 다시 합치기
            for (int i = 0; i < c.length; i++)
                temps += c[i];
            // 중복된 것 체크하고 개수 ++
            map.put(temps, map.getOrDefault(temps, 0) + 1);
            // 동시에 max값 갱신
            m = Math.max(m, map.get(temps));
            return;
        }
        // 조합이므로 idx부터 시작
        for (int i = idx; i < word.length(); i++) {
            char now = word.charAt(i);
            find(cnt + 1, str + now, targetNum, i + 1, word);
        }
    }
}