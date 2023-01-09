import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    static int[] parent;
    static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int F = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map=new HashMap<>();
            parent = new int[F*2];
            cnt = new int[F*2];
            Arrays.fill(cnt, 1);
            int index = 0;
            for (int j = 0; j < F; j++) {
                st = new StringTokenizer(br.readLine());
                String x = st.nextToken();
                String y = st.nextToken();
                
                // 만약 처음 들어온 이름이라면
                if(!map.containsKey(x)){
                    // 부모 노드를 자신으로 만들고
                    parent[index] = index;
                    // 해쉬맵에 추가
                    map.put(x,index++);
                }

                if(!map.containsKey(y)) {
                    parent[index] = index;
                    map.put(y,index++);
                }
                // 유니온 실행
                union(map.get(x), map.get(y));
                // y의 부모노드의 카운트(레벨)을 sb에 저장
                sb.append(cnt[find(map.get(y))]).append('\n');
            }
        }
        System.out.println(sb.toString());
    }

    static int find(int x) {
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if(px == py)
            return;
        parent[py] = px;
        cnt[px]+=cnt[py];
    }
}
