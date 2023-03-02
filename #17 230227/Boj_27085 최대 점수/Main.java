import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, s, A[], start, end;
    static int result = 0;
    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void solve(){
        start = s - 1;
        end = start;
        while(true) {
            if(left() && right()) break;
        }
        sb.append(result);
    }
    static boolean left(){
        int idx = start;
        int sum = 0;
        int preResult = result;
        while(idx > 0){
            sum += A[--idx];
            if(sum + result < 0) break;
            if(sum > 0){
                for(int i = idx; i <= start; i++){
                    A[i] = 0;
                }
                result += sum;
                start = idx;
                sum = 0;
            }
        }
        if(preResult == result) return true;
        else return false;
    }
    static boolean right(){
        int idx = end;
        int sum = 0;
        int preResult = result;
        while(idx < N - 1){
            sum += A[++idx];
            if(sum + result < 0) break;
            if(sum > 0){
                for(int i = end; i <= idx; i++){
                    A[i] = 0;
                }
                result += sum;
                end = idx;
                sum = 0;
            }
        }
        if(preResult == result) return true;
        else return false;
    }
    public static void main(String[] argrs) throws IOException {
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
