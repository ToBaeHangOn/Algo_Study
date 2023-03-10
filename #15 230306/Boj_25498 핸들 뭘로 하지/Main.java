import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static List<List<Integer>> tree;
    static int N, u, v;
    static String str;
    static boolean visited[];
    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        str = " " + br.readLine();
        tree = new ArrayList<>();
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++){
            tree.add(new ArrayList<>());
        }
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
    }
    static void solve(){
        // 사전상 마지막에 오는 단어를 가진 노드를 찾아 그 노드만 탐색하며 핸들을 만들어준다.
        Queue<Integer> queue = new LinkedList<>();
        visited[1] = true;
        queue.offer(1);
        sb.append(str.charAt(1));
        while(!queue.isEmpty()){
            char max = Character.MIN_VALUE; // 현재 사전 상 가장 후순위 값
            List<Integer> tempList = new ArrayList<>(); // 탐색할 가치 있는 노드들
            int size = queue.size();
            // 현재 큐에 있는(같은 레벨에 있는) 모든 노드에 대해 한 루프 안에서 탐색
            for(int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int next : tree.get(cur)) {
                    if (visited[next]) continue;
                    // 최대값보다 작으면 사전상 후순위가 아니므로 탐색할 필요 없음
                    if (str.charAt(next) < max) continue;

                    // 현재 큐가 비어있거나 최대값이 갱신되면 리스트 초기화
                    if (str.charAt(next) > max) {
                        tempList = new ArrayList<>();
                        max = str.charAt(next);
                    }
                    tempList.add(next);
                }
            }
            for(int i : tempList){
                visited[i] = true;
                queue.offer(i);
            }
            if(max != Character.MIN_VALUE) sb.append(max);
        }
    }
    public static void main(String[] argrs) throws IOException {
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

/*
import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static List<List<Integer>> tree;
    static int N, u, v;
    static String str;
    static boolean visited[];
    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        str = " " + br.readLine();
        tree = new ArrayList<>();
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++){
            tree.add(new ArrayList<>());
        }
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
    }
    static void solve(){
        // 사전상 마지막에 오는 단어를 가진 노드를 찾아 그 노드만 탐색하며 핸들을 만들어준다.
        Queue<Integer> queue = new LinkedList<>();
        visited[1] = true;
        queue.offer(1);
        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(str.charAt(cur));
            for(int next : tree.get(cur)){
                if(visited[next]) continue;
                visited[next] = true;
                // 현재 큐가 비어있으면 일단 추가
                if(queue.isEmpty()) queue.offer(next);
                // 큐에 들어있는 값보다 next값이 사전상 후순위라면 큐에 들어있는 값 교체
                else if(str.charAt(queue.peek()) < str.charAt(next)) {
                    queue.poll();
                    queue.offer(next);
                }
            }
        }
    }
    public static void main(String[] argrs) throws IOException {
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

*/
