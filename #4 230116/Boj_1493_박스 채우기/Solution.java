import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(br.readLine());
//        int[][] arr = new int[K][2];
//        for (int i = K - 1; i >= 0; i--) {
//            st = new StringTokenizer(br.readLine());
//            arr[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
//        }
//        int count = 0;
//        a:
//        while (true) {
//            int temp = N;
//            count = 0;
//            for (int i = 0; i < arr.length; i++) {
//                for (int j = 0; j < arr[i][1]; j++) {
//                    temp -= (int) Math.pow(2, arr[i][0]);
//                    count++;
//                    if (temp == 0) {
//                        System.out.println(count);
//                        break a;
//                    }
//
//                }
//            }
//            System.out.println(-1);
//            break;
//        }
//        System.out.println(count);
//    }

    static int L, W, H, n, cube[];
    static boolean f = true;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cube = new int[21];
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cube[a] = b;
        }
        int ans = func(L, W, H);
        if (f)
            System.out.println(ans);
        else System.out.println(-1);
    }

    static int func(int l, int w, int h) {
        if (l == 0 || w == 0 || h == 0) return 0;
        int k = l;
        if (w < k) k = w;
        if (h < k) k = h;
        // t = log2(k)
        int t = 31 - Integer.numberOfLeadingZeros(k);
        do {
            if (cube[t] == 0) continue;
            cube[t]--;
            int T = (int) Math.pow(2, t);
            // 남은 공간을 3 경우로 나누어 재귀 호출
            return func(l - T, T, h) + func(l, w - T, h) + func(T, T, h - T) + 1;
        } while (--t >= 0);
        // 없으면 불가능으로 만들고
        f = false;
        // -1 출력
        return -1;
    }
}
