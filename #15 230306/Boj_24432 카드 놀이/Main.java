import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static long A[], B[];
    static int n, m, k;
    static Set<Long> set;
    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        B = new long[n];
        A = new long[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            B[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            A[i] = Long.parseLong(st.nextToken());
        }
    }
    static void solve(){
        set = new HashSet<>();
        combination(A, new boolean[A.length], 0, A.length, k);
        long Asum[] = set.stream().mapToLong(Long::longValue).toArray();
        set = new HashSet<>();
        combination(B, new boolean[B.length], 0, B.length, k);
        long Bsum[] = set.stream().mapToLong(Long::longValue).toArray();
        Arrays.sort(Asum);
        Arrays.sort(Bsum);
        long max = Math.max(Math.abs(Asum[Asum.length - 1] - Bsum[0]), Math.abs(Bsum[Bsum.length - 1] - Asum[0]));
        long min = getMin(Asum, Bsum);
        sb.append(min).append(" ").append(max);
    }
    // n개 중 r개를 뽑아 그 합을 set에 저장한다
    static void combination(long[] arr, boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            long sum = 0L;
            for(int i = 0; i < arr.length; i++){
                if(visited[i]){
                    sum += arr[i];
                }
            }
            set.add(sum);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
    static long getMin(long[] Asum, long[] Bsum){
        long min = Long.MAX_VALUE;
        int Aidx = 0, Bidx = 0;
        while(Aidx < Asum.length && Bidx < Bsum.length){
            long temp = Math.abs(Asum[Aidx] - Bsum[Bidx]);
            if(temp < min) min = temp;
            if(min == 0L) break;

            // Asum과 Bsum은 이미 정렬되어 있다. idx가 커질 수록 가르키는 배열의 값도 커진다.
            if(Asum[Aidx] < Bsum[Bidx]) Aidx++;
            else Bidx++;
        }
        return min;
    }
    public static void main(String[] argrs) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            input();
            solve();
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

