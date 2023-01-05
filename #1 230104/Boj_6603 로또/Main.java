import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        while(true){
            int k = sc.nextInt();
            if(k == 0) break;
            int[] arr = new int[k];
            boolean[] visited = new boolean[k];

            for(int i = 0; i < k; i++){
                arr[i] = sc.nextInt();
            }

            comb(arr, visited, 0, k, 6);
            System.out.println();
        }
    }

    static void comb(int[] arr, boolean[] visited, int start, int k, int n){
        if(n == 0){
            for(int i = 0; i < k; i++){
                if(visited[i]){
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
            return;
        }

        for(int i = start; i < k; i++){
            visited[i] = true;
            comb(arr, visited, i + 1, k, n -1);
            visited[i] = false;
        }
    }
}
