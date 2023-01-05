import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static int k;
    static int[] arr;
    static boolean[] selected;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        while(true) {
            String str = br.readLine();
            if(str.equals("0")) {
                    break;
            } else {
                st = new StringTokenizer(str);
                k = Integer.parseInt(st.nextToken());
                arr = new int[k];
                selected = new boolean[k];
                for (int i = 0; i < k; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }
                rec(0, 0);
                sb.append('\n');
            }
        }
        System.out.println(sb.toString());
    }

    static void rec(int start, int cnt) {
        if(cnt == 6 ) {
            for (int i = 0; i < k; i++) {
                if(selected[i])
                    sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
        }
            for (int i = start; i < k; i++) {
                selected[i] = true;
                rec(i+1, cnt+1);
                selected[i] = false;
            }

    }
}
