public class Solution {
    //순열 선택 배열
    static int[] sel;
    // 선택 체크 배열
    static boolean[] check;
    static int answer;

    public int solution(int k, int[][] dungeons) {
        answer = 0;
        sel = new int[dungeons.length];
        check = new boolean[dungeons.length];
        rec(k, dungeons, 0);
        return answer;
    }

    static void rec(int k, int[][] dungeons, int cnt) {
        if (cnt == dungeons.length) {
            int count = 0;
            for (int i : sel) {
                if (k < dungeons[i][0])
                    break;
                else {
                    k -= dungeons[i][1];
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }
        for (int i = 0; i < dungeons.length; i++) {
            if (!check[i]) {
                check[i] = true;
                sel[cnt] = i;
                rec(k, dungeons, cnt + 1);
                check[i] = false;
            }
        }
    }
}
