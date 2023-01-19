import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 범위가 크므로
        long[][] dp = new long[N + 1][N + 1];
        int[][] arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][1] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int temp = arr[i][j];
                // 0은 도착
                if (temp == 0)
                    break;
                // 밑으로, 옆으로 탐색
                if (j + temp <= N) dp[i][j + temp] += dp[i][j];
                if (i + temp <= N) dp[i + temp][j] += dp[i][j];
            }
        }
        System.out.println(dp[N][N]);
    }
}
