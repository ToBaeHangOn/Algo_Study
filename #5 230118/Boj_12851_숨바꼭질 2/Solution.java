import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int[] dx = {-1, 1};
    static int N, K, arr[], result, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[100001];
        result = Integer.MAX_VALUE;
        count = 0;
        StringBuilder sb = new StringBuilder();
        if (N >= K) {
            // 더 크면 줄어드는 방법밖에 없으므로 방법은 1개, 최소시간은 길이다.
            sb.append(N - K).append('\n').append(1);
            System.out.println(sb);
            return;
        }
        bfs();

        sb.append(result).append('\n').append(count);
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        arr[N] = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (result < arr[curr])
                return;
            for (int i = 0; i < 3; i++) {
                int nx = curr;
                if (i == 2)
                    nx *= 2;
                else
                    nx += dx[i];
                if (nx < 0 || nx > 100000)
                    continue;
                // 찾으면
                if (nx == K) {
                    result = arr[curr];
                    count++;
                }

                if (arr[nx] == 0 || arr[nx] == arr[curr] + 1) {
                    queue.offer(nx);
                    arr[nx] = arr[curr] + 1;
                }
            }
        }
    }
}
