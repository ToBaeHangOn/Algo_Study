import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int cnt;
        Pair[] dp = new Pair[N];
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = i;
        }
        dp[0] = new Pair(arr[0], 1);

    }
}

class Pair{
    private int first;
    private int second;

    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
    public int getFirst(){return first;}
    public int getSecond(){return second;}
    public void setFirst(int f){this.first = f;}
    public void setSecond(int s){this.second = s;}
}