import java.io.IOException;
import java.util.*;

public class Solution {
    static class Point{
        int row;
        int col;
        int days;
        public Point(int row, int col, int days) {
            this.row = row;
            this.col = col;
            this.days = days;
        }
    }
    static boolean visited[][];
    static int[] dirX = {0, 1, 0, -1};
    static int[] dirY = {1, 0, -1, 0};
    static int height, length;
    static List<Integer> solution(String[] maps) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        height = maps.length;
        length = maps[0].length();
        visited = new boolean[height][length];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < length; j++){
                if(maps[i].charAt(j) != 'X'){
                    if(!visited[i][j])
                        list.add(bfs(i, j, maps));
                }
            }
        }
        Collections.sort(list);
        if(list.size() == 0) list.add(-1);
        return list;
    }
    static int bfs(int row, int col, String[] maps){
        int sum_days = 0;
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(row, col, maps[row].charAt(col) - '0'));
        visited[row][col] = true;

        while(!que.isEmpty()){
            Point cur = que.poll();
            sum_days += cur.days;
            for(int i = 0; i < 4; i++){
                int next_row = cur.row + dirY[i];
                int next_col = cur.col + dirX[i];
                if(next_row >= height || next_row < 0 || next_col >= length || next_col < 0){
                    continue;
                }
                if(maps[next_row].charAt(next_col) != 'X' && !visited[next_row][next_col]){
                    visited[next_row][next_col] = true;
                    que.offer(new Point(next_row, next_col, maps[next_row].charAt(next_col) - '0'));
                }
            }
        }
        return sum_days;
    }
    public static void main(String[] args) throws IOException {
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        System.out.println(solution(maps));
    }
}
