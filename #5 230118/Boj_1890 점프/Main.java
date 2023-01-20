import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] map;
    static long[][] dp;
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new long[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    static void solve(){
        dp[0][0] = 1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int distance = map[i][j];
                if(distance == 0) break;
                if(i + distance < N){ dp[i + distance][j] += dp[i][j]; }
                if(j + distance < N){ dp[i][j + distance] += dp[i][j]; }
            }
        }
        sb.append(dp[N - 1][N - 1]);
    }
    static public void main(String[] args) throws IOException{
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
