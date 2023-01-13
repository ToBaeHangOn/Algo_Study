import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        // 테이블과 보드 방문 처리
        boolean[][] visitedTable = new boolean[table.length][table.length];
        boolean[][] visitedBoard = new boolean[game_board.length][game_board.length];
        // 0 또는 1로 퍼즐 모양을 만드는 테이블과 보드 리스트를 저장.
        List<List<int[]>> boardList = new ArrayList<>();
        List<List<int[]>> tableList = new ArrayList<>();

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                // 타입으로 구분해 bfs 재활용
                if (table[i][j] == 1 && !visitedTable[i][j]) {
                    bfs(i, j, visitedTable, table, 1, tableList);
                }

                if (game_board[i][j] == 0 && !visitedBoard[i][j]) {
                    bfs(i, j, visitedBoard, game_board, 0, boardList);
                }
            }
        }

        answer = findBlock(boardList, tableList);

        return answer;
    }

    // 블록을 찾아보자.
    public int findBlock(List<List<int[]>> board, List<List<int[]>> table) {
        // 일치하는 블록의 개수
        int size = 0;
        int tableLen = table.size();
        int boardLen = board.size();
        // 보드 좌표 리스트 방문 체크
        boolean[] visitedBoard = new boolean[boardLen];
        for (int i = 0; i < tableLen; i++) {
            // 리스트와 배열로 된 퍼즐 가져오기
            List<int[]> tablePoints = table.get(i);
            for (int j = 0; j < boardLen; j++) {
                // 보드 중 퍼즐 모양 가져오기
                List<int[]> boardPoints = board.get(j);
                // 만약 좌표 개수가 같고 아직 방문하지 않았을 때
                if (tablePoints.size() == boardPoints.size() && !visitedBoard[j]) {
                    // 회전을 해서 맞는지 확인(4방향)
                    if (isRotate(boardPoints, tablePoints)) {
                        // 같으면 테이블의 퍼즐 넓이 만큼 사이즈에 저장
                        size += tablePoints.size();
                        // 보드에 이미 퍼즐이 들어갔으므로 방문 처리
                        visitedBoard[j] = true;
                        break;
                    }
                }
            }

        }
        // 채운 빈칸 반환
        return size;
    }

    // 돌려보자
    public boolean isRotate(List<int[]> board, List<int[]> table) {
        // 반환값
        boolean isCollect = false;
        // 보드의 빈칸 좌표들을 왼쪽 상단 순으로 정렬.
        board.sort((o1, o2) -> {
            return o1[0] > o2[0] ? 1 : o1[0] < o2[0] ? -1 : Integer.compare(o1[1], o2[1]);
        });

        // 테이블을 회전
        for (int i = 0; i < 4; i++) {
            // 테이블 퍼즐도 마찬가지로 왼쪽 상단 순으로 정렬.
            table.sort((o1, o2) -> {
                return o1[0] > o2[0] ? 1 : o1[0] < o2[0] ? -1 : Integer.compare(o1[1], o2[1]);
            });

            //
            int nearZeroX = table.get(0)[0];
            int nearZeroY = table.get(0)[1];

            //
            for (int j = 0; j < table.size(); j++) {
                table.get(j)[0] -= nearZeroX;
                table.get(j)[1] -= nearZeroY;
            }


            boolean isCollectPoint = true;

            // 좌표를 비교
            for (int j = 0; j < board.size(); j++) {
                int[] boardPoint = board.get(j);
                int[] tablePoint = table.get(j);

                // 둘 중 하나라도 숫자가 같으면 안됨 (0 != 1)
                if (boardPoint[0] != tablePoint[0] || boardPoint[1] != tablePoint[1]) {
                    // 바로 탈출
                    isCollectPoint = false;
                    break;
                }
            }

            // 만약 일치한다면
            if (isCollectPoint) {
                isCollect = true;
                break;
            }
            // 같지 않으므로 회전(90도)
            else {
                for (int j = 0; j < table.size(); j++) {
                    int temp = table.get(j)[0];
                    table.get(j)[0] = table.get(j)[1];
                    table.get(j)[1] = -temp;
                }
            }
        }
        return isCollect;

    }


    // 퍼즐과 빈칸을 찾아보자
    public void bfs(int x, int y, boolean[][] visited, int[][] map,
                    int type, List<List<int[]>> list) {

        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        // 탐색할 좌표
        queue.add(new int[]{x, y});
        // 실제로 비교할 때 사용할 좌표
        List<int[]> subList = new ArrayList<>();
        // 0,0부터 저장
        subList.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 0 || nx >= map.length || ny < 0 || ny >= map.length) {
                    continue;
                }
                if (!visited[nx][ny] && map[nx][ny] == type) {

                    visited[nx][ny] = true;
                    // 탐색할 좌표를 큐에 저장
                    queue.add(new int[]{nx, ny});
                    // 새로 만들 0,0 기준 좌표로 저장.
                    subList.add(new int[]{nx - x, ny - y});
                }
            }
        }
        list.add(subList);
    }
}