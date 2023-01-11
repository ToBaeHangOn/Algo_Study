import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};
        solution(users, emoticons);
    }

    static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        int emoticons_sum = 0;
        boolean[] plus = new boolean[users.length];

        for(int i = 0; i < emoticons.length; i++){
            emoticons_sum += emoticons[i];
        }

        for(int i = 0; i < users.length; i++){
            int price = (emoticons_sum * (100 - users[i][0])) / 100;
            if(price < users[i][1]){
                plus[i] = false;
            }
            else plus[i] = true;
        }


        return answer;
    }


}
