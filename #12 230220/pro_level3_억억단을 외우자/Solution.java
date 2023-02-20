import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        // 1에서 e까지의 구구단 개수 저장
        int[] list = new int[e + 1];
        // 1~e까지 나온 숫자를 구한다.
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e; j++) {
                // e가 최대이므로 넘으면 break
                if (i * j > e) break;
                list[i * j]++;
            }
        }

        // 모든 starts를 약수의 개수를 탐색하면 시간초과이므로
        // starts의 값과 순서를 저장하고 만약 큰 숫자가 먼저나올 경우
        // 뒤에 나오는 작은 숫자는 무시할 수 있다.
        // 정렬전 순서를 저장.
        HashMap<Integer, Integer> startsSave = new HashMap<>();
        for (int i = 0; i < starts.length; i++) {
            startsSave.put(starts[i], i);
        }
        Arrays.sort(starts);
        //System.out.println(Arrays.toString(starts));
        // 최댓값과 최대인덱스
        int max = Integer.MIN_VALUE;
        int maxIndex = Integer.MIN_VALUE;

        for (int i = 0; i < starts.length; i++) {
            // 정렬 전 순서를 불러온다.
            int location = startsSave.get(starts[i]);

            // 이미 정렬을 했으므로 앞으로 나올 수는 이전에 나온 수보다 반드시 크다.
            // 이를 이용해서 maxIndex가 더 크면 굳이 연산할 필요 없이 해당 maxIndex를 답에 추가한다.
            if (maxIndex >= starts[i]) {
                answer[location] = maxIndex;
                continue;
            }

            // 만약 max가 더 작다면 개수를 탐색한다.
            max = Integer.MIN_VALUE;
            maxIndex = Integer.MIN_VALUE;

            for (int j = starts[i]; j <= e; j++) {
                if (max < list[j]) {
                    max = list[j];
                    maxIndex = j;
                }
            }

            answer[location] = maxIndex;
        }

        return answer;
    }
}