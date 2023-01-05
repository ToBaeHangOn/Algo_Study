import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for(int i = 0; i < tc; i++){
            int cnt = 0;
            int F = sc.nextInt();
            List<String> list = new ArrayList<>();

            parent = new int[F * 2];
            for(int j = 0; j < F * 2; j++){
                parent[j] = j;
            }

            for(int j = 0; j < F; j++){
                String f1 = sc.next();
                int a = cnt;

                int index = list.indexOf(f1);
                if(index == -1){list.add(f1); cnt++;}
                else{a = index;}

                String f2 = sc.next();
                int b = cnt;

                index = list.indexOf(f2);
                if(index == -1){list.add(f2); cnt++;}
                else{b = index;}

                union(a, b);
                int union_cnt = 0;
                for(int k = 0; k < list.size(); k++){
                    if(find(k) == find(a)) union_cnt++;
                }
                System.out.println(union_cnt);
            }
        }
    }

    static int find(int a){
        if(parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b)
            parent[b] = a;
    }
}
