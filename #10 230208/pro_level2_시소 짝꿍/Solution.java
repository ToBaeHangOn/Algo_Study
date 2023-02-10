import java.util.Arrays;
import java.util.HashMap;

class Solution {
    static int N;
    static boolean[] visited;

    static int[] weight;

    static long answer;
    static int[] selected;

    static int[] dx = {2, 2, 3, 3, 4, 4};
    static int[] dy = {3, 4, 2, 4, 2, 3};

    public long solution(int[] weights) {

//        weight = weights;
        HashMap<Double, Integer> map = new HashMap<>();
//        long ret = 0;
        Arrays.sort(weights);
//        N = weights.length;
//        selected = new int[2];
        answer = 0;
//        visited = new boolean[N];
        for (int i : weights) {
            answer += check(i, map);
        }
        //rec(0, 0);
        return answer;
    }

    // 2. 몸무게의 경우의 수를 체크한다.
    static long check(int w, HashMap<Double, Integer> map) {
        long result = 0;
        // 총 1 + 6개를 비교한다.
        // 1 : 1 / 2 : 3
        // 2 : 4 / 3 : 2
        // 3 : 4 / 4 : 2
        // 4 : 3
        double[] ws = new double[7];
        ws[0] = w * 1.0;
        ws[1] = w * 2.0 / 3.0;
        ws[2] = w * 2.0 / 4.0;
        ws[3] = w * 3.0 / 2.0;
        ws[4] = w * 3.0 / 4.0;
        ws[5] = w * 4.0 * 2.0;
        ws[6] = w * 4.0 / 3.0;
        // 만약 map에 해당되는 키가 있다면
        // 이는 몸무게가 같은 조합이 있으므로 결과에 그 개수를 더해준다.
        for (double d : ws) {
            if (map.containsKey(d))
                result += map.get(d);
        }
        // 그 후 입력된 몸무게 또한 map에 저장한다.
        map.put(ws[0], map.getOrDefault(ws[0], 0) + 1);
        return result;
    }

    // 1. 정통적인 조합
//    static void rec(int start, int cnt) {
//        if (cnt == 2) {
//            System.out.println(selected[0] + ", " + selected[1]);
//            if (selected[0] == selected[1]) {
//                answer++;
//            } else {
//                for (int i = 0; i < 6; i++) {
//                    int nx = selected[0] * dx[i];
//                    int ny = selected[1] * dy[i];
//                    if (nx == ny) {
//                        answer++;
//                        return;
//                    }
//                }
//            }
//            return;
//        }
//        for (int i = start; i < N; i++) {
//            selected[cnt] = weight[i];
//            rec(i + 1, cnt + 1);
//
//        }
//    }
}