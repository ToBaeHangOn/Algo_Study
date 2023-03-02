import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int n, m, k;
    static boolean[] visited;

    static int[] arr;
    //    static int[] results;
    static int[] selected;
    //    static Stack<Integer> stack;
    static Set<Integer> set;
    static int[] bobList;
    static int[] aliceList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int temp = Integer.parseInt(st.nextToken());
                arr[i] = temp;
            }
            visited = new boolean[n];
            set = new HashSet<>();
//            stack = new Stack<>();
            selected = new int[k];
            rec(0, n);
            bobList = new int[set.size()];
            int index = 0;
            for (Integer i : set)
                bobList[index++] = i;

            //alice
            arr = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int temp = Integer.parseInt(st.nextToken());
                arr[i] = temp;
            }
            visited = new boolean[m];
//            stack = new Stack<>();
            set = new HashSet<>();
            selected = new int[k];
            rec(0, m);
            aliceList = new int[set.size()];
            index = 0;
            for (Integer i : set)
                aliceList[index++] = i;
            Arrays.sort(aliceList);
            Arrays.sort(bobList);
            int min = Integer.MAX_VALUE, max = 0;
            max = Math.max(Math.abs(aliceList[aliceList.length - 1] - bobList[0]), Math.abs(bobList[bobList.length - 1] - aliceList[0]));
            int indexBob = 0;
            int indexAlice = 0;

            // 원하는 최솟값 탐색
            while (indexBob < bobList.length && indexAlice < aliceList.length) {
                int temp = Math.abs(bobList[indexBob] - aliceList[indexAlice]);
                if (temp < min) {
                    min = temp;
                } else if (bobList[indexAlice] <= aliceList[indexBob])
                    indexBob++;
                else
                    indexAlice++;
            }
            sb.append(min).append(' ').append(max).append('\n');

//            PriorityQueue<Integer> bobPq = new PriorityQueue<>();
//            PriorityQueue<Integer> bobRevPq = new PriorityQueue<>(Comparator.reverseOrder());
//            PriorityQueue<Integer> alicePq = new PriorityQueue<>();
//            PriorityQueue<Integer> aliceRevPq = new PriorityQueue<>(Comparator.reverseOrder());
//            st = new StringTokenizer(br.readLine());
//            for (int i = 0; i < n; i++) {
//                int temp = Integer.parseInt(st.nextToken());
//                bobPq.add(temp);
//                bobRevPq.add(temp);
//            }
//            st = new StringTokenizer(br.readLine());
//            for (int i = 0; i < m; i++) {
//                int temp = Integer.parseInt(st.nextToken());
//                alicePq.add(temp);
//                aliceRevPq.add(temp);
//            }
//            int bobMax = 0, bobMin = 0, aliceMax = 0, aliceMin = 0;
//            for (int i = 0; i < k; i++) {
//                bobMin += bobPq.poll();
//                bobMax += bobRevPq.poll();
//                aliceMin += alicePq.poll();
//                aliceMax += aliceRevPq.poll();
//            }
//            int min = 0, max = 0;
//            min = Math.min(Math.min(Math.abs(bobMin - aliceMin), Math.abs(bobMax - aliceMax)), Math.min(Math.abs(bobMax - aliceMin), Math.abs(bobMin - aliceMax)));
//            max = Math.max(Math.max(Math.abs(bobMin - aliceMin), Math.abs(bobMax - aliceMax)), Math.max(Math.abs(bobMax - aliceMin), Math.abs(bobMin - aliceMax)));
//            sb.append(min).append(' ').append(max).append('\n');
        }
        System.out.println(sb);
    }

    static void rec(int cnt, int limit) {
//        System.out.println("rec : " + limit);
        if (cnt == k) {
            int temp = 0;
            for (int i : selected)
                temp += i;
            set.add(temp);
            return;
        }

        for (int i = 0; i < limit; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[cnt] = arr[i];
                rec(cnt + 1, limit);
                visited[i] = false;
            }
        }
    }


}
