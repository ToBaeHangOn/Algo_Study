import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // 맵에 귤 추가
        for (int tan : tangerine) {
            map.put(tan, map.getOrDefault(tan, 0) + 1);
        }
        // 맵 내림차순 정렬
        ArrayList<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int count = 0;
        // 만약 카운트가 k 이상이면 answer를 출력한다.
        for (Entry<Integer, Integer> entry : list) {
            count += entry.getValue();
            answer++;
            if (count >= k)
                break;
        }
        return answer;
    }
}