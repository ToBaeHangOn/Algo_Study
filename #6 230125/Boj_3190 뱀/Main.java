import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, K, L;
    static int[][] board;
    static int[] dx ={1,0,-1,0};
    static int[] dy ={0,1,0,-1};
    static int cur_dir = 0;
    static int time = 0;
    static HashMap<Integer, Character> map;
    static class Point{
        int row;
        int col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static Queue<Point> body;
    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = 1; // 사과 위치 : 1
        }
        L = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        for(int i = 0; i < L; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            map.put(x, c);
        }
    }
    static void solve(){
        Point head = new Point(1, 1); // 시작 위치
        body = new LinkedList<>();
        body.offer(head);
        board[head.row][head.col] = 2; // 뱀의 몸 위치 : 2
        move(head);
        sb.append(time);
    }
    static void move(Point cur_head){
        if(map.containsKey(time)){
            if(map.get(time) == 'D'){
                cur_dir = (cur_dir + 1) % 4;
            }
            else{
                cur_dir = (cur_dir + 3) % 4;
            }
        }
        time++;
        Point next_head = new Point(cur_head.row + dy[cur_dir], cur_head.col + dx[cur_dir]);
        if(next_head.row <= 0 || next_head.row > N || next_head.col <= 0 || next_head.col > N) return;
        int next_board = board[next_head.row][next_head.col];
        if(next_board == 2) return;
        else {
            body.offer(next_head);
            board[next_head.row][next_head.col] = 2;
            if(next_board == 0){
                Point tail = body.poll();
                board[tail.row][tail.col] = 0;
            }
            move(next_head);
        }
    }
    public static void main(String[] argrs) throws IOException{
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
