import java.io.IOException;
public class Solution {
    static int answer = 0;
//    static int solution(int storey) {
//        System.out.println(storey);
//        if(storey == 0){
//            return answer;
//        }
//        int ten = 1;
//        while(storey >= ten){
//            ten *= 10;
//        }
//        if(ten * 55 > 100 * storey){
//            storey -= ten / 10;
//            answer++;
//            solution(storey);
//        }
//        else{
//            storey = ten - storey;
//            answer++;
//            solution(storey);
//        }
//        return answer;
//    }
    // new code start
    static int solution(int storey) {
        fun(storey);
        return answer;
    }
    static void fun(int storey){
        System.out.println(storey);
        if(storey == 0){
            return;
        }
        int ten = 1;
        while(storey >= ten){
            ten *= 10;
        }
        System.out.println(ten);
        int highNum = storey / (ten / 10); // 맨 앞자리 수
        if(ten * 0.55 > storey){
            storey -= highNum * (ten / 10);
            answer += highNum;
            fun(storey);
        }
        else{
            storey = ten - storey;
            answer++;
            fun(storey);
        }
    }
    // new code end
    public static void main(String[] args) throws IOException {
        int storey = 10;
        System.out.println(solution(storey));
    }
}
