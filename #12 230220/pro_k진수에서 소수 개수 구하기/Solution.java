import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int solution(int n, int k) {
        int answer = 0;
        Queue<Integer> que = new LinkedList<>();
        while(n > 0){
            que.offer(n % k);
            n /= k;
        } // 큐에 n을 k진수로 변환하여 저장
        long ten = 1;
        long sum = 0;
        long temp;
        while(!que.isEmpty()){
            temp = que.poll();
            if(temp == 0){
                if(isPrime(sum)){
                    answer++;
                }
                ten = 1;
                sum = 0;
            }
            else{
                sum += ten * temp;
                ten *= 10;
            }
        } // 뒷 자리부터 0이 나올때까지 10진수 숫자를 쌓아가다 0이 나왔을 때 소수인지 판단
        if(sum != 0){
            if(isPrime(sum)) answer++;
        } // 남아있는 숫자도 소수인지 판단
        return answer;
    }
    static boolean isPrime(long sum){
        if(sum < 2) return false;
        if(sum == 2) return true;
        for(int i = 2; i <= Math.sqrt(sum); i++){
            if(sum % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException{
        int n = 110011;
        int k = 10;
        System.out.println(solution(n, k));
    }
}
