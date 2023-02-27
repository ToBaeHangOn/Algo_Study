import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static Long X;
    static void input() throws IOException{
        X = Long.parseLong(br.readLine());
    }
    static void solve(){
        Long n = (long)Math.ceil((-2 + Math.sqrt(8 * X - 4L)) / 4);
        Long start = 2 * n * n - 2 * n + 1L;
        Long end = 2 * n * n + 2 * n + 1L;
        Long mom, son;

        Long full_dist = end - start;
        Long dist = X - start;

        if(dist < full_dist / 4){
            son = n; mom = n;
            son -= dist;
            mom += dist;
        }
        else if(dist < 3 * full_dist / 4){
            son = 1L; mom = 2 * n;
            son += dist - full_dist / 4;
            mom -= dist - full_dist / 4;
        }
        else{
            son = 2 * n + 1L; mom = 1L;
            son -= dist - 3 * full_dist / 4;
            mom += dist - 3 * full_dist / 4;
        }

        System.out.println(son + "/" + mom);
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
