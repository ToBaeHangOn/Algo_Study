import java.io.IOException;
public class Solution {
    static int answer = 0;
    static int solution(int storey) {
        System.out.println(storey);
        if(storey == 0){
            return answer;
        }
        int ten = 1;
        while(storey >= ten){
            ten *= 10;
        }
        if(ten * 55 > 100 * storey){
            storey -= ten / 10;
            answer++;
            solution(storey);
        }
        else{
            storey = ten - storey;
            answer++;
            solution(storey);
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        int storey = 10;
        System.out.println(solution(storey));
    }
}
