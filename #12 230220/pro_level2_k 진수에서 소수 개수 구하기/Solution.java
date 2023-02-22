import java.util.StringTokenizer;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        // String으로 변환할 builder
        StringBuilder sb = new StringBuilder();
        // String으로 변환
        while (n != 0) {
            sb.append(n % k);
            n /= k;
        }
        // 거꾸로 해야 정상적인 k진수로 출력
        String str = sb.reverse().toString();
        // 0을 기준으로 자른다.
        StringTokenizer st = new StringTokenizer(str, "0");
        // 모든 토큰을 소수인지 검사한다.
        while (st.hasMoreTokens()) {
            String temp = st.nextToken();
            //System.out.println(temp);
            long num = Long.parseLong(temp);
            if (isPrime(num))
                answer++;
        }

        return answer;
    }

    //에라토스테네스의 체 소수 판별
    public boolean isPrime(long a) {
        if (a < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0)
                return false;
        }
        return true;
    }
}