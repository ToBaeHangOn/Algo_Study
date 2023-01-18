import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int length;
    static int width;
    static int height;
    static int n;
    static int[] cube;
    static long max_volume = 0;
    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        max_volume = (long)length * width * height;

        n = Integer.parseInt(br.readLine());
        cube = new int[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            cube[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void solve(){
        long cnt = 0;
        int len = cube.length;
        long pre_cube = 0;
        for(int i = len - 1; i >= 0; i--){
            int side_length = (int)Math.pow(2, i);
            pre_cube *= 8;
            long temp_cube = (long)(length / side_length) * (width / side_length) * (height / side_length) - pre_cube;
            if(temp_cube > cube[i]){ temp_cube = cube[i]; }

            pre_cube += temp_cube;
            cnt += temp_cube;
        }
        if(pre_cube != max_volume){ sb.append(-1); return; }
        else { sb.append(cnt); }
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
