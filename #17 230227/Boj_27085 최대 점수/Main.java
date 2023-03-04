import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, s, start, end;
    static Long A[], result = 0L;
    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        A = new Long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Long.parseLong(st.nextToken());
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
        Long sum = 0L;
        Long preResult = result;
        while(idx > 0){
            sum += A[--idx];
            if(sum + result < 0L) break;
            if(sum > 0L){
                for(int i = idx; i <= start; i++){
                    A[i] = 0L;
                }
                result += sum;
                start = idx;
                sum = 0L;
            }
        }
        if(preResult == result) return true;
        else return false;
    }
    static boolean right(){
        int idx = end;
        Long sum = 0L;
        Long preResult = result;
        while(idx < N - 1){
            sum += A[++idx];
            if(sum + result < 0L) break;
            if(sum > 0L){
                for(int i = end; i <= idx; i++){
                    A[i] = 0L;
                }
                result += sum;
                end = idx;
                sum = 0L;
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
