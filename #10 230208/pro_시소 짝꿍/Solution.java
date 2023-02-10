import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static long solution(int[] weights) {
        float[] mul = {8, 9, 12};
        float div = 6; // 4/3배, 3/2배, 2배 연산을 하기 위함
        long answer = 0;
        Map<Integer, Long> map = new HashMap<>();
        long val;
        for(int i : weights){
            if(map.containsKey(i)){
                val = map.get(i);
                map.remove(i);
                map.put(i, ++val);
            } // value 값을 바꿔주기 위해 원래 있던 key를 지우고 다시 put
            else {
                val = 1;
                map.put(i, val);
            }
        }
        for(int i : map.keySet()){
            val = map.get(i);
            if(val > 1){
                answer += val * (val - 1) / 2;
            }
            for(int j = 0; j < 3; j++) {
                float friend = (float) i * mul[j] / div;
                if(friend - (int) friend > 0) continue;
                if (map.containsKey((int) friend)){
                    answer += val * map.get((int) friend);
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        int[] weights = {100,100,100,120,131,787,200,262,366};
        System.out.println(solution(weights));
    }
}
