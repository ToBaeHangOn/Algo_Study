import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] dice = new int[7];
    static int[][] map;
    static int[] order;
    static int n;
    static int m;
    static int x;
    static int y;
    static int k;
    static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        order = new int[k];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            order[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void solve(){
        for(int i = 0; i < k; i++){
            int nx = x;
            int ny = y;
            switch (order[i]){
                case 1: ny = y + 1; break;
                case 2: ny = y - 1; break;
                case 3: nx = x - 1; break;
                case 4: nx = x + 1; break;
            }
            if(isInside(nx, ny)) {
                x = nx; y = ny;
                copy(order[i]);
            }

        }
    }
    static void copy(int m_order){
        int temp;
        switch (m_order){
            case 1:
                temp = dice[6];
                dice[6] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = temp;
                break;
            case 2:
                temp = dice[6];
                dice[6] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = temp;
                break;
            case 3:
                temp = dice[6];
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[2];
                dice[2] = temp;
                break;
            case 4:
                temp = dice[6];
                dice[6] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = temp;
                break;
        }
        if(map[x][y] == 0){
            map[x][y] = dice[6];
        }
        else{
            dice[6] = map[x][y];
            map[x][y] = 0;
        }
        sb.append(dice[1]).append("\n");
    }
    static boolean isInside(int nx, int ny){
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
