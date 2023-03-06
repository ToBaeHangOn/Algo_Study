import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, q;
    static char c;
    static class Pair{
        int query;
        char ch;
        public Pair(int query, char ch) {
            this.query = query;
            this.ch = ch;
        }
    }
    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
    }
    static void solve() throws IOException{
        Stack<Pair> stack = new Stack<>();
        StringBuilder front = new StringBuilder();
        StringBuilder back = new StringBuilder();
        // 스택에 1,2가 나오면 쌓고 3이 나오면 빼준다
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            q = Integer.parseInt(st.nextToken());
            if(q == 3){
                if(!stack.isEmpty()) stack.pop();
            }
            else {
                stack.push(new Pair(q, st.nextToken().charAt(0)));
            }
        }
        // 스택이 비어있으면 0 출력
        if(stack.isEmpty()) {
            sb.append("0");
            return;
        }
        // 뒤에 붙일 문자와 앞에 붙일 문자를 구분해서 빼준다
        for(Pair pair : stack){
            if(pair.query == 1){
                back.append(pair.ch);
            }
            else{
                front.append(pair.ch);
            }
        }
        // 앞에 붙일 문자들은 역순으로 붙여준다.
        for(int i = front.length() - 1; i >= 0; i--){
            sb.append(front.charAt(i));
        }
        sb.append(back);
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
