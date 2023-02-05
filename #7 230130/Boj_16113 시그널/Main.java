import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int N, part, index;
    static String board;
    static char[][] chars;
    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        board = br.readLine();
        part = N / 5;
        chars = new char[5][part];
        for(int i = 0; i < 5; i++){
            chars[i] = board.substring(i * part, (i + 1) * part).toCharArray();
        }
    }
    static void solve(){
        index = 0;
        while(index < part){
            if(chars[0][index] == '#'){
                if(index + 1 < part){
                    if(chars[0][index + 1] == '#')
                        sb.append(startByThree());
                    else
                        sb.append(startByOne());
                }
                else {
                    sb.append(1);
                    index++;
                }
            }
            else index++;
        }
    }
    static int startByThree(){ // 0,2,3,5,6,7,8,9
        int temp;
        if(chars[1][index] == '#'){ // 0,5,6,8,9
            if(chars[2][index + 1] == '#'){ // 5,6,8,9
                if(chars[3][index] == '#'){ // 6,8
                    if(chars[1][index + 2] =='#')
                        temp = 8;
                    else
                        temp = 6;
                }
                else{ // 5,9
                    if(chars[1][index + 2] =='#')
                        temp = 9;
                    else
                        temp = 5;
                }
            }
            else temp = 0;
        }
        else{ // 2,3,7
            if(chars[2][index] == '#'){
                if(chars[3][index] == '#')
                    temp = 2;
                else
                    temp = 3;
            }
            else
                temp = 7;
        }
        index += 3;
        return temp;
    }
    static int startByOne(){ // 1,4
        if(chars[4][index] == '#') { // 맨 밑줄에 #이 있는 경우는 무조건 1
            index += 1;
            return 1;
        }
        else {
            index += 3;
            return 4;
        }
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
