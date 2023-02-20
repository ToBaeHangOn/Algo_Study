import java.io.IOException;
public class Solution {
    static int[] solution(int e, int[] starts) {
        int[] answer = {};
        int[] divisor = new int[e + 1];
        for(int i = 1; i <= e; i++){
            divisor[i] = div(i);
        }
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
    static int div(int n){
        int cnt = 0;
        for(int i = 1; i * i <= n; i++){
            if(i * i == n) cnt++;
            else if(n % i == 0) cnt += 2;
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        int e = 8;
        int[] starts = {1,3,7};
        System.out.println(solution(e, starts));
    }
}
