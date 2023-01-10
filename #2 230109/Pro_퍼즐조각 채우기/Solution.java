import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        visited = new boolean[table.length][table.length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] == 1 && !visited[i][j]) {
                    bfs(new int[]{i, j}, table);
                }
            }
        }

        visited = new boolean[table.length][table.length];

        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board.length; j++) {
                if (game_board[i][j] == 0 && !visited[i][j]) {
                    
                }
            }
        }

        return answer;
    }


    static int[][] bfs(int[] start, int[][] table) {
        int[][] result;
        ArrayList<int[]> list = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();

        visited[start[0]][start[1]] = true;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (table[curr[0]][curr[1]] == 1) {
                list.add(new int[]{curr[0], curr[1]});
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx < 0 || nx >= table.length || ny < 0 || ny >= table.length)
                    continue;
                if (!visited[nx][ny] && table[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        int xMin = table.length, xMax = 0, yMin = table.length, yMax = 0;
        int xLimit = 0;
        int yLimit = 0;
        for (int[] i : list) {
            xMin = Math.min(xMin, i[0]);
            xMax = Math.max(xMax, i[0]);
            yMin = Math.min(yMin, i[1]);
            yMax = Math.max(yMax, i[1]);
        }
        xLimit = Math.abs(xMax - xMin);
        yLimit = Math.abs(yMax - yMin);
        int size = Math.max(xLimit, yLimit) + 1;
        result = new int[size][size];
        for (int[] pos : list) {
            result[pos[0] % size][pos[1] % size] = 1;
        }
        return result;
    }

    static class Puzzle {
        int[][] map;

        public Puzzle(int[][] map) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    this.map[i][j] = map[i][j];
                }
            }
        }

        // d번 회전 시키는 함수
        public void rotate(int d) {
            int n = this.map.length;
            int[][] temp = new int[n][n];
            for (int t = 0; t < d; t++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        temp[i][j] = this.map[n - 1 - j][i];
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        this.map[i][j] = temp[i][j];
                    }
                }
            }
        }
    }
}