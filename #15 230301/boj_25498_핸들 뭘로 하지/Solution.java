import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static String s;
    static ArrayList<Integer>[] edges;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n까지의 정점
        n = Integer.parseInt(br.readLine());
        // 문자열 순서
        s = br.readLine();
        // 정점 배열
        edges = new ArrayList[n + 1];
        // 정점에 저장
        for (int i = 0; i <= n; i++)
            edges[i] = new ArrayList<>();
        // 간선 연결
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        System.out.println(getAnswer());
    }

    static char getChar(int idx) {
        // 1부터 시작하므로
        return s.charAt(idx - 1);
    }

    // 답변구하기
    static String getAnswer() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> arr = new ArrayList<>();
        // 방문체크
        boolean[] v = new boolean[n + 1];
        arr.add(1);
        v[1] = true;
        sb.append(getChar(1));
        // bfs
        while (!arr.isEmpty()) {
            ArrayList<Integer> validNodes = null;
            // 문자 최댓값
            char max = '\0';
            for (int cur : arr) {
                for (int next : edges[cur]) {
                    if (v[next])
                        continue;
                    char charOfNext = getChar(next);
                    // 만약 현재 문자가 맥스보다 작으면
                    // 방문할 필요가 없다.
                    if (max > charOfNext)
                        continue;
                    // 만약 리스트가 널이면 후보가 없으니 넣고,
                    // 맥스보다 현재 방문한 문자가 사전순으로 더 뒤에 있으면
                    if (validNodes == null || max < getChar(next)) {
                        // 리스트를 새로 만들어 준다.
                        validNodes = new ArrayList();
                        // 최댓값을 갱신한다
                        max = getChar(next);
                    }
                    // 현재 방문한 문자를 리스트에 추가한다.
                    validNodes.add(next);
                }
            }
            // 만약 리스트가 비어있다면, 모든문자를 탐색했으므로
            if (validNodes == null)
                break;
            // 가장 큰 맥스를 추가한다.
            sb.append(max);
            ArrayList<Integer> tmp = new ArrayList<>();
            // 다음에 방문할 노드들을 갱신한다
            for (int next : validNodes) {
                v[next] = true;
                tmp.add(next);
            }
            arr = tmp;
        }
        return sb.toString();
    }
}
