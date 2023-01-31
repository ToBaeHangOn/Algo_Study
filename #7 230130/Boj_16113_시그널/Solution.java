import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static char[][] map;

    static char[][][] ones = {
            {{'.', '#', '.'}, {'.', '#', '.'}, {'.', '#', '.'}, {'.', '#', '.'}, {'.', '#', '.'}},
            {{'.', '.', '#'}, {'.', '.', '#'}, {'.', '.', '#'}, {'.', '.', '#'}, {'.', '.', '#'}}
    };
    static char[][] one2 = {{'.', '#'}, {'.', '#'}, {'.', '#'}, {'.', '#'}, {'.', '#'}};


    static char[][][] nums = {
            {{'#', '#', '#'}, {'#', '.', '#'}, {'#', '.', '#'}, {'#', '.', '#'}, {'#', '#', '#'}},
            {{'#', '.', '.'}, {'#', '.', '.'}, {'#', '.', '.'}, {'#', '.', '.'}, {'#', '.', '.'}},
            {{'#', '#', '#'}, {'.', '.', '#'}, {'#', '#', '#'}, {'#', '.', '.'}, {'#', '#', '#'}},
            {{'#', '#', '#'}, {'.', '.', '#'}, {'#', '#', '#'}, {'.', '.', '#'}, {'#', '#', '#'}},
            {{'#', '.', '#'}, {'#', '.', '#'}, {'#', '#', '#'}, {'.', '.', '#'}, {'.', '.', '#'}},
            {{'#', '#', '#'}, {'#', '.', '.'}, {'#', '#', '#'}, {'.', '.', '#'}, {'#', '#', '#'}},
            {{'#', '#', '#'}, {'#', '.', '.'}, {'#', '#', '#'}, {'#', '.', '#'}, {'#', '#', '#'}},
            {{'#', '#', '#'}, {'.', '.', '#'}, {'.', '.', '#'}, {'.', '.', '#'}, {'.', '.', '#'}},
            {{'#', '#', '#'}, {'#', '.', '#'}, {'#', '#', '#'}, {'#', '.', '#'}, {'#', '#', '#'}},
            {{'#', '#', '#'}, {'#', '.', '#'}, {'#', '#', '#'}, {'.', '.', '#'}, {'#', '#', '#'}}
    };
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] str = br.readLine().toCharArray();
        map = new char[5][N / 5];
        
        for (int i = 0; i < N; i++) {
            map[i / (N / 5)][i % (N / 5)] = str[i];
        }
        StringBuilder sb = new StringBuilder();
        visited = new boolean[5][N / 5];

//        for (int i = 0; i < N / 5 - 2; i++) {
//            if (!visited[0][i]) {
//                int checkNum = -1;
//                if (map[0][i] == '#') {
//                    // 0부터 9까지 체크
//                    checkNum = checkIsNum(false, i);
//
//                } else {
//                    // 1. 010 체크
//                    // 2. 001 체크
//                    checkNum = checkIsNum(true, i);
//                }
//                if (checkNum != -1) {
//                    visitCheck(checkNum == 1, i);
//                    sb.append(checkNum);
//                }
//            }
//        }
        for (int i = 0; i < N / 5; i++) {
            if (map[0][i] == '#') {
                if (i + 2 <= N / 5) {
                    // 1과 4를 제외한 나머지 모든 수가 ###으로 시작하므로
                    if (map[0][i + 1] == '#' && map[0][i + 2] == '#') {
                        sb.append(choice(i));
                        i = i + 3;
                        if (i >= N / 5) break;
                        continue;
                    }
                }
                // 4번째 문자가 #일경우, 4가 나올 수 없다.
                if (map[3][i] == '#') sb.append(1);
                    // 그렇지 않다면 4로 확정
                else {
                    sb.append(4);
                    i = i + 3;
                    if (i >= N / 5) break;
                }

            }

        }
        System.out.println(sb);
    }

    static int choice(int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(map[i][j + start]);
            }
        }

        return check(sb.toString());
    }

    static int check(String sb) {

        int result = 10;
        String[] num = new String[10];

        num[0] = "####.##.##.####";
        num[2] = "###..#####..###";
        num[3] = "###..####..####";
        num[5] = "####..###..####";
        num[6] = "####..####.####";
        num[7] = "###..#..#..#..#";
        num[8] = "####.#####.####";
        num[9] = "####.####..####";

        for (int i = 0; i < num.length; i++) {
            if (sb.equals(num[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    static int checkIsNum(boolean isOne, int start) {
        int tc = isOne ? 2 : 10;

        for (int t = 0; t < tc; t++) {
            boolean isNum = true;
            c:
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 3; j++) {
                    if (isOne) {
                        if (ones[t][i][j] != map[i][j + start]) {
                            isNum = false;
                            break c;
                        }
                    } else {
                        if (nums[t][i][j] != map[i][j + start]) {
                            isNum = false;
                            break c;
                        }
                    }

                }
            }
            if (isNum)
                return isOne ? 1 : t;
        }
        if (isOne) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 2; j++) {
                    if (one2[i][j] != map[i][j + start]) {
                        return -1;
                    }
                }
            }
            return 1;
        } else
            return -1;
    }

    static void visitCheck(boolean isOne, int start) {
        int limit = isOne ? 2 : 3;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < limit; j++) {
                visited[i][j + start] = true;
            }
        }
    }
}
