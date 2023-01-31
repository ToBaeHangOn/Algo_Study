import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static List<Integer> plusList;
    static List<Integer> minusList;
    static void input() throws IOException{
        int temp;
        N = Integer.parseInt(br.readLine());
        plusList = new ArrayList<>();
        minusList = new ArrayList<>();
        for(int i = 0; i < N; i++){
            temp = Integer.parseInt(br.readLine());
            if(temp > 0) plusList.add(temp);
            else minusList.add(temp);
        }
    }
    static void solve(){
        int result = 0; // 2^31 보다 작기 때문에 int 가능
        Collections.sort(plusList, Collections.reverseOrder()); // 내림차순
        Collections.sort(minusList); // 오름차순
        int plusSize = plusList.size();
        int minusSize = minusList.size();
        for(int i = 0; i < plusSize; i++){
            if(i + 1 < plusSize && plusList.get(i) != 1 && plusList.get(i + 1) != 1){
                result += plusList.get(i++) * plusList.get(i);
            }
            else{
                result += plusList.get(i);
            }
        }
        for(int i = 0; i < minusSize; i++){
            if(i + 1 < minusSize){
                result += minusList.get(i++) * minusList.get(i);
            }
            else{
                result += minusList.get(i);
            }
        }
        sb.append(result);
    }
    public static void main(String[] argrs) throws IOException {
        input();
        solve();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
