import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        int[][] arr = new int[K][2];
        for (int i = K - 1; i >= 0; i--) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        int count = 0;
        a:
        while (true) {
            int temp = N;
            count = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i][1]; j++) {
                    temp -= (int) Math.pow(2, arr[i][0]);
                    count++;
                    if (temp == 0) {
                        System.out.println(count);
                        break a;
                    }

                }
            }
            System.out.println(-1);
            break;
        }
        System.out.println(count);
    }
}
