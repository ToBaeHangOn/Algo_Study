import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    static int x, y;
    static char[][] island;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int[] solution(String[] maps) {
        x = maps.length;
        y = maps[0].length();
        island = new char[x][y];
        visited = new boolean[x][y];
        for (int i = 0; i < x; i++) {
            island[i] = maps[i].toCharArray();
        }

        PriorityQueue<Integer> que = new PriorityQueue<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (!visited[i][j] && island[i][j] != 'X') {
                    // bfs 탐색
                    int temp = bfs(new int[]{i, j});
                    if (temp != 0)
                        que.offer(temp);
                }
            }
        }
        if (que.size() == 0)
            return new int[]{-1};
        int[] answer = new int[que.size()];
        int index = 0;
        while (!que.isEmpty())
            answer[index++] = que.poll();
//        Arrays.sort(answer);
        return answer;
    }

    static int bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        int result = 0;
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            result += island[curr[0]][curr[1]] - '0';

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx < 0 || nx >= x || ny < 0 || ny >= y || visited[nx][ny])
                    continue;
                if (island[nx][ny] != 'X') {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }

            }
        }
        return result;
    }
}