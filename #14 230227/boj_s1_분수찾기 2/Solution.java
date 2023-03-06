import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long num = Long.parseLong(br.readLine());
        // 현재 수가 속하는 수열 찾기
        long basePosition = getBasePosition(num);
        // 현재 수가 속하는 수열의 실제 순서
        long temp = (basePosition * basePosition - basePosition) / 2L + 1;

        // 분자, 분모
        long numerator, denominator;
        //만약 수열이 짝수일 경우
        if (basePosition % 2 == 0L) {
            // 분자는 num에서 temp를 뺀값
            numerator = 1 + (num - temp);
            // 분모는 base수열의 값에서 num에서 temp를 뺀 값
            denominator = basePosition - (num - temp);
        }
        // 홀수는 반대로
        else {
            numerator = basePosition - (num - temp);
            denominator = 1 + (num - temp);
        }

        System.out.println(numerator + "/" + denominator);

//        BigInteger X = new BigInteger(br.readLine());
//        BigInteger n = BigInteger.ZERO;
//        BigInteger top = BigInteger.ONE;
//        BigInteger bot = BigInteger.ONE;
//        BigInteger cnt = BigInteger.ZERO;
//
//        BigInteger cross_count = BigInteger.ONE;
//        BigInteger prev_count_sum = BigInteger.ZERO;
//
//        while (true) {
//            // 직전 대각선 누적합 + 해당 대각선 개수 이용한 범위 판별
//            if (X.compareTo(prev_count_sum.add(cross_count)) <= 0) {
//
//                if (cross_count.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {    // 대각선의 개수가 홀수라면
//                    // 분자가 큰 수부터 시작
//                    // 분자는 대각선상 블럭의 개수 - (X 번째 - 직전 대각선까지의 블럭 개수 - 1)
//                    // 분모는 X 번째 - 직전 대각선까지의 블럭 개수
//                    System.out.print((cross_count.subtract(X.subtract(prev_count_sum.subtract(BigInteger.ONE))) + "/" + X.subtract(prev_count_sum)));
//                    break;
//                } else {    // 대각선의 개수가 짝수라면
//                    // 홀수일 때의 출력을 반대로
//                    System.out.print(X.subtract(prev_count_sum) + "/" + cross_count.subtract(X.subtract(prev_count_sum.subtract(BigInteger.ONE))));
//                    break;
//                }
//
//            } else {
//                prev_count_sum = prev_count_sum.add(cross_count);
//                cross_count = cross_count.add(BigInteger.ONE);
//            }
//        }
//
//        if (X.equals(BigInteger.ONE)) {
//            System.out.println("1/1");
//        } else {
//            while (cnt.compareTo(X) < 0) {
//                n = n.add(BigInteger.ONE);
//                cnt = n.multiply(n.add(BigInteger.ONE)).divide(BigInteger.valueOf(2));
//            }
////            System.out.println("n : " + n + ", cnt : " + cnt);
//            BigInteger num = X.subtract(n.subtract(BigInteger.ONE)).multiply(n).divide(BigInteger.valueOf(2));
////            System.out.println("n : " + n + ", num : " + num);
//            if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
//                top = num;
//                bot = n.subtract(num).add(BigInteger.ONE);
//            } else {
//                top = n.subtract(num).add(BigInteger.ONE);
//                bot = num;
//            }
//            System.out.println(top + "/" + bot);
//        }
    }

    // 준영님의 코드를 참조
    static long getBasePosition(long num) {
        long left = 0L;
        long right = Integer.MAX_VALUE;
        long target = 0L;

        while (left <= right) {
            // 중간 지점 찾기
            long mid = (left + right) / 2L;
            // n * (n - 1) / 2 + 1 => 1/1, 2/2, 3/3 의 순서
            long result = (mid * mid - mid) / 2L + 1;
            // 만약 이 값이 num이라면
            if (result == num) {
                target = mid;
                break;
            }
            // 그렇지않다면 둘로 나누어 탐색
            else if (result < num) {
                left = mid + 1;
                target = mid;
            } else {
                right = mid - 1;
            }
        }

        return target;
    }

}
