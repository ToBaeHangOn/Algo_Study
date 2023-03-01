import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger X = new BigInteger(br.readLine());
        BigInteger n = BigInteger.ZERO;
        BigInteger top = BigInteger.ONE;
        BigInteger bot = BigInteger.ONE;
        BigInteger cnt = BigInteger.ZERO;

        BigInteger cross_count = BigInteger.ONE;
        BigInteger prev_count_sum = BigInteger.ZERO;

        while (true) {
            // 직전 대각선 누적합 + 해당 대각선 개수 이용한 범위 판별
            if (X.compareTo(prev_count_sum.add(cross_count)) <= 0) {

                if (cross_count.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {    // 대각선의 개수가 홀수라면
                    // 분자가 큰 수부터 시작
                    // 분자는 대각선상 블럭의 개수 - (X 번째 - 직전 대각선까지의 블럭 개수 - 1)
                    // 분모는 X 번째 - 직전 대각선까지의 블럭 개수
                    System.out.print((cross_count.subtract(X.subtract(prev_count_sum.subtract(BigInteger.ONE))) + "/" + X.subtract(prev_count_sum)));
                    break;
                } else {    // 대각선의 개수가 짝수라면
                    // 홀수일 때의 출력을 반대로
                    System.out.print(X.subtract(prev_count_sum) + "/" + cross_count.subtract(X.subtract(prev_count_sum.subtract(BigInteger.ONE))));
                    break;
                }

            } else {
                prev_count_sum = prev_count_sum.add(cross_count);
                cross_count = cross_count.add(BigInteger.ONE);
            }
        }
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
}
