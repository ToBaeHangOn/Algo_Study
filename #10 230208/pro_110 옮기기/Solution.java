import java.io.IOException;
public class Solution {
    static String[] solution(String[] s) {
        int len = s.length;
        String[] answer = new String[len];
        for(int i = 0; i < len; i++){
            answer[i] = changeStr(s[i]);
        }
        return answer;
    }
    static String changeStr(String s){
        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        // Try 1 start
        // sb에서 110의 위치를 찾고 지워주는 것을 반복
        // 시간초과가 났지만 indexOf를 다른 상황에서 유용하게 쓸 수 있을 것 같음
        /*
        while(true){
            int start = sb.indexOf("110");
            if(start == -1) break;
            sb.delete(start, start + 3);
            cnt++;
        }
        */
        // Try 1 end

        // Try 2 start
        // sb를 stack처럼 사용
        for(int i = 0; i < s.length(); i++){
            sb.append(s.charAt(i));
            int len = sb.length();
            if(len < 3) continue;
            else if(sb.substring(len - 3, len).equals("110")) {
                sb.delete(len - 3, len);
                cnt++;
                // 현재 인덱스까지 sb에 쌓인 최근 3개 값이 110이면
                // 지워주고 그 숫자를 세어줌
            }
        }
        // Try 2 end

        int lastZero = 0;
        lastZero = sb.lastIndexOf("0");
        while(cnt > 0) {
            sb.insert(lastZero + 1, "110");
            cnt--;
        }
        // 남은 문자열 중 마지막으로 0이 나오는 위치의 다음부터
        // 지웠던 110 만큼 삽입
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        String[] s = {"1011110","01110","101101111010"};
        solution(s);
    }
}
