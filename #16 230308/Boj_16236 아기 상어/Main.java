import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int map[][], N, dx[] = {1,-1,0,0}, dy[] = {0,0,1,-1};
    static Shark shark;
    static class Point implements Comparable<Point>{
        int row, col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public int compareTo(Point p){
            if(this.row < p.row) return -1;
            else if(this.row == p.row && this.col < p.col) return -1;
            else return 1;
        } // 기저 조건. 위에 있거나 같은 행이라면 왼쪽에 있는 것 우선
    }
    static class Shark{
        Point point;
        int fishes = 0;
        int size = 2;
        public Shark(Point p) {
            this.point = new Point(p.row, p.col);
        }
    }
    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    shark = new Shark(new Point(i, j));
                    map[i][j] = 0;
                }
            }
        }
    }
    static void solve(){
        Queue<Point> queue;
        int dist[][];
        int min_dist;
        List<Point> fishes;
        int result = 0;
        while(true){
            // 상어가 움직일 때마다 bfs로 가까운 물고기를 찾음
            queue = new LinkedList<>();
            dist = new int[N][N]; // 방문 처리 겸 상어와의 거리
            min_dist = Integer.MAX_VALUE;
            fishes = new ArrayList<>();
            queue.offer(shark.point);
            dist[shark.point.row][shark.point.col] = 0;
            while(!queue.isEmpty()){
                Point cur = queue.poll();
                for(int i = 0; i < 4; i++){
                    Point next = new Point(cur.row + dy[i], cur.col + dx[i]);
                    // 범위를 벗어나거나, 방문했으면 continue
                    if(next.row < 0 || next.col < 0 || next.row >= N || next.col >= N) continue;
                    if(dist[next.row][next.col] > 0) continue;

                    int cur_fish_size = map[next.row][next.col];
                    if(cur_fish_size > shark.size) continue; // 더 큰 물고기가 있으면 못 지나감
                    else if(cur_fish_size == shark.size || cur_fish_size == 0){ // 지나갈 수 있음
                        dist[next.row][next.col] = dist[cur.row][cur.col] + 1;
                        queue.offer(next);
                    }
                    else { // 물고기 먹을 수 있음
                        dist[next.row][next.col] = dist[cur.row][cur.col] + 1;
                        if(min_dist >= dist[next.row][next.col]){ // 최단 거리인 경우만 fishes에 add
                            min_dist = dist[next.row][next.col];
                            fishes.add(next);
                        }
                    }
                }
            }
            if(fishes.size() == 0){
                sb.append(result);
                return;
            }
            else {
                Collections.sort(fishes);
                Point die_fish = new Point(fishes.get(0).row, fishes.get(0).col);
                result += min_dist;
                map[die_fish.row][die_fish.col] = 0;
                shark.fishes++;
                if(shark.size == shark.fishes){
                    shark.size++;
                    shark.fishes = 0;
                }
                shark.point.row = die_fish.row;
                shark.point.col = die_fish.col;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
