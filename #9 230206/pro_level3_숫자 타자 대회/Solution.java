import java.util.Arrays;

class Solution {
    // 0 ~ 9까지 이동할 때, 각각의 인덱스에서 숫자까지의 가중치
    static int[][] cost = {
            {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
            {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
            {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
            {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
            {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
            {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
            {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
            {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
            {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
            {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
    };
    //dp[index][left][right]
    static int[][][] dp;
    static String arr;
    static int N;

    public int solve(int index, int L, int R) {
        // N만큼 탐색했다면
        if (index == N)
            return 0;

        if (dp[index][L][R] != -1)
            return dp[index][L][R];

        int num = arr.charAt(index) - '0';

        int result = Integer.MAX_VALUE;

        //왼쪽 손가락으로 움직이기
        if (num != R)
            result = Math.min(solve(index + 1, num, R) + cost[L][num], result);

        //오른 손가락으로 움직이기
        if (num != L)
            result = Math.min(solve(index + 1, L, num) + cost[R][num], result);
        
        // 왼손과 오른손으로 했을 떄 가장 작은 가중치를 가지는 것이 result
        dp[index][L][R] = result;
        return dp[index][L][R];
    }

    public int solution(String numbers) {
        int answer = 0;
        arr = numbers;
        N = numbers.length();
        // 길이가 N 이므로
        dp = new int[N + 1][10][10];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }
        answer = solve(0, 4, 6);
        return answer;
    }
}