import java.io.IOException;
public class Solution {
    static int[] solution(int e, int[] starts) {
        int[] answer = {};
        int[] divisor = new int[e + 1];
//        for(int i = 1; i <= e; i++){
//            divisor[i] = div(i);
//        }
        // New code start
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e / i; j++) {
                divisor[i * j]++;
            }
        }
        // New code end
        int max = 0;
        int[] dp = new int[e + 1];
        for(int i = e; i > 0; i--){
            if(divisor[i] >= max){
                max = divisor[i];
                dp[i] = i;
            }
            else dp[i] = dp[i + 1];
        }
        answer = new int[starts.length];
        for(int i = 0; i < starts.length; i++){
            answer[i] = dp[starts[i]];
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        int e = 8;
        int[] starts = {1,3,7};
        System.out.println(solution(e, starts));
    }
}
