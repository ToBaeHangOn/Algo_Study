//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//
//class Solution_fail {
//    static int[] dx = {-1, 1, 0, 0};
//    static int[] dy = {0, 0, -1, 1};
//    static boolean[][] visitedTable, visitedBoard;
//    static boolean[] visited, visitedTab;
//
//    public int solution(int[][] game_board, int[][] table) {
//        int answer = 0;
//        visitedTable = new boolean[table.length][table.length];
//        visitedBoard = new boolean[game_board.length][game_board.length];
//
//        List<Puzzle> boardList = new ArrayList<>();
//        List<Puzzle> tableList = new ArrayList<>();
//
//        for (int i = 0; i < table.length; i++) {
//            for (int j = 0; j < table.length; j++) {
//                if (table[i][j] == 1 && !visitedTable[i][j]) {
//                    tableList.add(bfs(new int[]{i, j}, table, 1));
//                }
//                if (game_board[i][j] == 0 && !visitedBoard[i][j]) {
//                    boardList.add(bfs(new int[]{i, j}, game_board, 0));
//                }
//            }
//        }
//
//        visited = new boolean[boardList.size()];
//        visitedTab = new boolean[tableList.size()];
//        for (int i = 0; i < boardList.size(); i++) {
//            for (int j = 0; j < tableList.size(); j++) {
//                if (!visited[i] && !visitedTab[j] && boardList.get(i).size == tableList.get(i).size) {
//                    if (isCorrect(tableList.get(i), boardList.get(i))) {
//                        answer++;
//                        visited[i] = true;
//                        visitedTab[j] = true;
//                    }
//                }
//            }
//        }
////        for (int i = 0; i < game_board.length; i++) {
////            for (int j = 0; j < game_board.length; j++) {
////                if (game_board[i][j] == 0 && !visited[i][j]) {
////
////                }
////            }
////        }
//
//        return answer;
//    }
//
//    static boolean isCorrect(Puzzle table, Puzzle gameBoard) {
//        boolean result = false;
//
//        for (int d = 1; d <= 4; d++) {
//            boolean isGood = true;
//            a:
//            for (int i = 0; i < gameBoard.map.length; i++) {
//                for (int j = 0; j < gameBoard.map[i].length; j++) {
//                    if (table.map[i][j] == gameBoard.map[i][j]) {
//                        table.rotate(d);
//                        isGood = false;
//                        break a;
//                    }
//                }
//            }
//            if (isGood)
//                return true;
//        }
//        return result;
//    }
//
//
//    static Puzzle bfs(int[] start, int[][] table, int type) {
//        int[][] result;
//        ArrayList<int[]> list = new ArrayList<>();
//        Queue<int[]> queue = new LinkedList<>();
//
//        if (type == 1) visitedTable[start[0]][start[1]] = true;
//        else visitedBoard[start[0]][start[1]] = true;
//        queue.offer(start);
//        while (!queue.isEmpty()) {
//            int[] curr = queue.poll();
//            if (table[curr[0]][curr[1]] == 1) {
//                list.add(new int[]{curr[0], curr[1]});
//            }
//            for (int i = 0; i < 4; i++) {
//                int nx = curr[0] + dx[i];
//                int ny = curr[1] + dy[i];
//
//                if (nx < 0 || nx >= table.length || ny < 0 || ny >= table.length)
//                    continue;
//                if (type == 1) {
//                    if (!visitedTable[nx][ny] && table[nx][ny] == type) {
//                        queue.offer(new int[]{nx, ny});
//                    }
//                } else {
//                    if (visitedBoard[nx][ny] && table[nx][ny] == type) {
//                        queue.offer(new int[]{nx, ny});
//                    }
//                }
//            }
//        }
//        int xMin = table.length, xMax = 0, yMin = table.length, yMax = 0;
//        int xLimit = 0;
//        int yLimit = 0;
//        for (int[] i : list) {
//            xMin = Math.min(xMin, i[0]);
//            xMax = Math.max(xMax, i[0]);
//            yMin = Math.min(yMin, i[1]);
//            yMax = Math.max(yMax, i[1]);
//        }
//        xLimit = Math.abs(xMax - xMin);
//        yLimit = Math.abs(yMax - yMin);
//        int size = Math.max(xLimit, yLimit) + 1;
//        result = new int[size][size];
//        int s = 0;
//        for (int[] pos : list) {
//            result[pos[0] % size][pos[1] % size] = type;
//            s++;
//        }
//        return new Puzzle(result, s);
//    }
//
//    static class Puzzle {
//        int[][] map;
//        int size;
//
//        public Puzzle(int[][] map, int size) {
//            for (int i = 0; i < map.length; i++) {
//                for (int j = 0; j < map[i].length; j++) {
//                    this.map[i][j] = map[i][j];
//                }
//            }
//            this.size = size;
//        }
//
//        // d번 회전 시키는 함수
//        public void rotate(int d) {
//            int n = this.map.length;
//            int[][] temp = new int[n][n];
//            for (int t = 0; t < d; t++) {
//                for (int i = 0; i < n; i++) {
//                    for (int j = 0; j < n; j++) {
//                        temp[i][j] = this.map[n - 1 - j][i];
//                    }
//                }
//
//                for (int i = 0; i < n; i++) {
//                    for (int j = 0; j < n; j++) {
//                        this.map[i][j] = temp[i][j];
//                    }
//                }
//            }
//        }
//    }
//}