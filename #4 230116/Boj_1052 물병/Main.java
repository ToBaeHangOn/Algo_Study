import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int k;
    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }
    static void solve(){
        int answer = 0;

        while(Integer.bitCount(n++) > k){ // n을 이진수로 표현 했을 때 1의 갯수
            answer++;
        }
        sb.append(answer);
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
