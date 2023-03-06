import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, s, start, left, right;
    static long ans;
    static int[] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

//        st = new StringTokenizer(br.readLine());

        scores = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ans = 0L;
        start = s - 1;
        left = start - 1;
        right = start + 1;
        while (true) {
            long before = ans;
            leftSearch();
            rightSearch();
            // 왼쪽 오른쪽을 체크해도 답이 이전과 같을경우 탈출
            if (before == ans)
                break;
        }
        System.out.println(ans);
    }

    static void leftSearch() {
        long max = 0L;
        int maxIndex = left;
        long sum = 0L;

        while (left >= 0) {
            sum += scores[left];
            // 답과 더했는데 음수면 탈출
            if (ans + sum < 0)
                break;

            // 최대 점수 갱신시
            if (max < sum) {
                // 맥스로 갱신
                max = sum;
                // 현재 위치로 갱신
                maxIndex = left - 1;
            }
            // 탐색 위치 조절
            left--;
        }
        // 다음부터 왼쪽을 탐색할 때는 최대 점수를 얻은 위치의 '다음 지점' 부터 시작한다.
        // 따라서 maxLoc를 갱신할 때 -1을 해주는 것.
        left = maxIndex;
        ans += max;
    }

    // 오른쪽은 반대방향으로 탐색
    static void rightSearch() {
        long max = 0L;
        int maxIndex = right;
        long sum = 0L;

        while (right < N) {
            sum += scores[right];
            // 답과 더했는데 음수면 탈출
            if (ans + sum < 0)
                break;

            // 최대 점수 갱신시
            if (max < sum) {
                // 맥스로 갱신
                max = sum;
                // 현재 위치로 갱신
                maxIndex = right + 1;
            }
            // 탐색 위치 조절
            right++;
        }

        right = maxIndex;
        ans += max;
    }
}
