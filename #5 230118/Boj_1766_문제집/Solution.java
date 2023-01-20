import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
        int[] inDegree = new int[N + 1];
        for (int i = 0; i <= N; i++)
            mat.add(new ArrayList<>());


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            mat.get(from).add(to);
            inDegree[to] += 1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0)
                pq.offer(i);

        }

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr).append(" ");
            ArrayList<Integer> nodes = mat.get(curr);
            for (int node : nodes) {
                inDegree[node] -= 1;
                if (inDegree[node] == 0)
                    pq.offer(node);

            }
        }
        System.out.println(sb);
    }
}
