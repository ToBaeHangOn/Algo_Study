import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int K;
    static int[] dir = {-1, 1, 0};
    static class Pair{
        int first;
        int second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
        public int getFirst() {
            return first;
        }
        public void setFirst(int first) {
            this.first = first;
        }
    }
    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
    static void solve(){
        int time = 100000;
        int ways = 0;
        if(K <= N) {
            time = N - K;
            ways = 1;
            sb.append(time).append("\n").append(ways);
            return;
        }
        Queue<Pair> que = new LinkedList<>();
        Pair pair = new Pair(N, 0);
        que.offer(pair);

        while(!que.isEmpty()){
            Pair cur = que.poll();
            if(cur.second > time){ break; }
            dir[2] = cur.first; // dir = { -1, 1, X }
           // System.out.println("time : " + cur.second + " node : " + cur.first);
            for(int i = 0; i < 3; i++){
                Pair next = new Pair(cur.first + dir[i], cur.second + 1);
                if(next.first < 0 || next.first > 100000){continue;}
                if(next.first == K){
                 //   System.out.println("어케 걸림?" + next.first);
                    ways++;
                    time = next.second;
                }
                else{
                    que.offer(next);
                }
            }
        }
        sb.append(time).append("\n").append(ways);
    }
    static public void main(String[] args) throws IOException{
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
