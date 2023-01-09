import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private  static final int MOD = 1000000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[201][201];

        Arrays.fill(dp[0],1); // 0을 만들기 위한 경우의 수
        
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= K; j++){
                dp[i][j] = (dp[i][j - 1] + dp[i-1][j]) % MOD;
            }
        }
        System.out.println(dp[N][K]);
    }
}
