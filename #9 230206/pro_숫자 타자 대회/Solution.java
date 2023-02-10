import java.io.IOException;
import java.util.Arrays;

public class Solution {
    static int solution(String numbers) {
        int result = Integer.MAX_VALUE;
        int len = numbers.length();
        int[] arr = new int[len];
        for(int i = 0; i < len; i++){
            arr[i] = numbers.charAt(i) - '0';
        }
        int[][][] dp = new int[len + 1][10][10];
        for(int i = 0; i < len + 1; i++){
            for(int j = 0; j < 10; j++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        dp[0][4][6] = 0;
        for(int i = 0; i < len; i++){
            int target = arr[i];
            for(int left = 0; left < 10; left++){
                for(int right = 0; right < 10; right++){
                    if(right == left || dp[i][left][right] == Integer.MAX_VALUE) continue;

                    dp[i + 1][target][right] =
                            Math.min(dp[i + 1][target][right],
                                    dp[i][left][right] + move[left][target]);
                    dp[i + 1][left][target] =
                            Math.min(dp[i + 1][left][target],
                                    dp[i][left][right] + move[right][target]);
                    if(i + 1 == len){
                        result = Math.min(result, Math.min(dp[i + 1][left][target], dp[i + 1][target][right]));
                    }
                }
            }
        }
        return result;
    }
    static int[][] move = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };
    public static void main(String[] args) throws IOException {
        String numbers = "5123";
        System.out.println(solution(numbers));
    }
}
