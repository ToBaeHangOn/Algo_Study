import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        solution(k, dungeons);
    }
    static boolean[] visited = new boolean[8];
    static int max = 0;
    static int solution(int k, int[][] dungeons) {
        int answer = -1;
        answer = dfs(0, k, dungeons);
        return answer;
    }
    static int dfs(int depth, int k, int[][] dungeons){
        for(int i = 0; i < dungeons.length; i++){
            if(!visited[i] && k >= dungeons[i][0]){
                visited[i] = true;
                dfs(depth + 1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
        max = Math.max(max, depth);
        return max;
    }
}
