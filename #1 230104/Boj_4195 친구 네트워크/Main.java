import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] count;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i++){
            int cnt = 0;
            int F = Integer.parseInt(br.readLine());
            List<String> list = new ArrayList<>();

            parent = new int[F * 2];
            count = new int[F * 2];
            Arrays.fill(count, 1);

            for(int j = 0; j < F * 2; j++){
                parent[j] = j;
            }

            Map<String, Integer> map = new HashMap<>();

            for(int j = 0; j < F; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if(!map.containsKey(f1)){
                    map.put(f1, cnt++);
                }
                if(!map.containsKey(f2)){
                    map.put(f2, cnt++);
                }

                sb.append(union(map.get(f1), map.get(f2)) + "\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int find(int a){
        if(parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    static int union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b) {
            parent[b] = a;
            count[a] = count[a] + count[b];
            count[b] = count[a];
        }
        return count[a];
    }
}
