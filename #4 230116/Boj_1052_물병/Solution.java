import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int count = 0;
        if (N <= K) {
            System.out.println(0);
            return;
        }
        int buy = 0;
        while (true) {
            count = 0;
            int temp = N;
            while (temp != 0) {
                if (temp % 2 == 1) {
                    count += 1;
                }
                temp /= 2;
            }
            if (count <= K)
                break;
            N += 1;
            buy += 1;
        }
        System.out.println(buy);
    }
}
