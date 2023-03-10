import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int R,C,M,r,c,s,d,z,map[][];
    static Shark[] sharks;
    static class Shark{
        int row, col, speed, dir, size;
        public Shark(int row, int col, int speed, int dir, int size) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R + 1][C + 1];
        sharks = new Shark[M + 1];
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            map[r][c] = i;
            sharks[i] = new Shark(r,c,s,d,z);
        }
    }
    static void solve(){
        Long result = 0L;
        for(int king = 1; king <= C; king++){
            // 위에서부터 행을 내려오며 상어가 발견되면 잡아먹고 break
            for(int row = 1; row <= R; row++){
                if(map[row][king] != 0){
                    result += sharks[map[row][king]].size;
                    sharks[map[row][king]] = null;
                    map[row][king] = 0;
                    break;
                }
            }
            for(int cur_shark = 0; cur_shark < sharks.length; cur_shark++){
                if(sharks[cur_shark] == null) continue; // 죽은 상어는 continue
                map[sharks[cur_shark].row][sharks[cur_shark].col] = 0; // 현재 상어 위치 비워줌
                move(cur_shark); // 상어 이동
            }
            for(int cur_shark = 0; cur_shark < sharks.length; cur_shark++) {
                if(sharks[cur_shark] == null) continue; // 죽은 상어는 continue
                int other_shark = map[sharks[cur_shark].row][sharks[cur_shark].col]; // 다른 상어(없으면 0)
                if (other_shark != 0) { // 다른 상어 존재
                    if (sharks[other_shark].size < sharks[cur_shark].size) {
                        sharks[other_shark] = null; // 다른 상어 잡아먹음
                        map[sharks[cur_shark].row][sharks[cur_shark].col] = cur_shark; // 해당 위치에 현재 상어
                    } else {
                        sharks[cur_shark] = null; // 현재 상어 잡아먹힘
                    }
                } else {
                    map[sharks[cur_shark].row][sharks[cur_shark].col] = cur_shark; // 다른 상어 없으면 해당 위치에 현재 상어
                }
            }
            /*
            for(int cur_shark = 0; cur_shark < sharks.length; cur_shark++){
                if(sharks[cur_shark] == null) continue; // 죽은 상어는 continue
                map[sharks[cur_shark].row][sharks[cur_shark].col] = 0; // 현재 상어 위치 비워줌
                move(cur_shark); // 상어 이동
                int other_shark = map[sharks[cur_shark].row][sharks[cur_shark].col]; // 다른 상어(없으면 0)
                if(other_shark != 0){ // 다른 상어 존재
                    if(sharks[other_shark].size < sharks[cur_shark].size){
                        sharks[other_shark] = null; // 다른 상어 잡아먹음
                        map[sharks[cur_shark].row][sharks[cur_shark].col] = cur_shark; // 해당 위치에 현재 상어
                    }
                    else{
                        sharks[cur_shark] = null; // 현재 상어 잡아먹힘
                    }
                }
                else{
                    map[sharks[cur_shark].row][sharks[cur_shark].col] = cur_shark; // 다른 상어 없으면 해당 위치에 현재 상어
                }
            }
             */
        }
        sb.append(result);
    }
    // 상어를 움직이는 함수
    // speed는 2 * (해당 범위 - 1) 마다 위치가 반복되므로 그 수로 나눈 나머지로 연산해도 무방하다
    // 움직이면서 범위를 벗어날 때 방향을 바꿔줌
    static void move(int idx){
        int row = sharks[idx].row;
        int col = sharks[idx].col;
        int dir = sharks[idx].dir;
        int speed = sharks[idx].speed;
        if(dir == 1 || dir == 2) { // 위, 아래로 움직일 때
            speed = speed % (2 * R - 2);

            for (int i = 0; i < speed; i++) {
                if (row == 1) dir = 2;
                else if (row == R) dir = 1;

                if (dir == 2) row++;
                else row--;
            }
            sharks[idx].dir = dir;
            sharks[idx].row = row;
        }
        else{ // 좌, 우로 움직일 때
            speed = speed % (2 * C - 2);

            for (int i = 0; i < speed; i++) {
                if (col == 1) dir = 3;
                else if (col == C) dir = 4;

                if (dir == 3) col++;
                else col--;
            }
            sharks[idx].dir = dir;
            sharks[idx].col = col;
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
