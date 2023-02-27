import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, Q;
    static SegmentTree segmentTree;
    static class SegmentTree{
        long tree[]; //각 원소가 담길 트리
        int treeSize; // 트리의 크기

        public SegmentTree(int arrSize){
            // 트리 높이 구하기
            int h = (int) Math.ceil(Math.log(arrSize)/ Math.log(2));
            // 높이를 이용한 배열 사이즈 구하기
            this.treeSize = (int) Math.pow(2,h+1);
            // 배열 생성
            tree = new long[treeSize];
        }
        // arr = 원소배열, node = 현재노드, start = 현재구간 배열 시작, start = 현재구간 배열 끝
        public long init(long[] arr, int node, int start,int end){
            // 배열의 시작과 끝이 같다면 leaf 노드이므로
            // 원소 배열 값 그대로 담기
            if(start == end){
                return tree[node] = arr[start];
            }

            // leaf 노드가 아니면, 자식노드 합 담기
            return tree[node] =
                    init(arr,node*2,start,(start+ end)/2)
                            + init(arr,node*2+1,(start+end)/2+1,end);
        }
        // node: 현재노드 idx, start: 배열의 시작, end:배열의 끝
        // idx: 변경된 데이터의 idx, diff: 원래 데이터 값과 변경 데이터값의 차이
        public void update(int node, int start, int end, int idx, long diff){
            // 만약 변경할 index 값이 범위 바깥이면 확인 불필요
            if(idx < start || end < idx) return;

            // 차를 저장
            tree[node] += diff;

            // 리프노드가 아니면 아래 자식들도 확인
            if(start != end){
                update(node*2, start, (start+end)/2, idx, diff);
                update(node*2+1, (start+end)/2+1, end, idx, diff);
            }
        }
        // node: 현재 노드, start : 배열의 시작, end : 배열의 끝
        // left: 원하는 누적합의 시작, right: 원하는 누적합의 끝
        public long sum(int node, int start, int end, int left, int right){
            // 범위를 벗어나게 되는 경우 더할 필요 없음
            if(left > end || right < start){
                return 0;
            }

            // 범위 내 완전히 포함 시에는 더 내려가지 않고 바로 리턴
            if(left <= start && end <= right){
                return tree[node];
            }

            // 그 외의 경우 좌 / 우측으로 지속 탐색 수행
            return sum(node*2, start, (start+end)/2, left, right)+
                    sum(node*2+1, (start+end)/2+1, end, left, right);
        }
    }
    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        segmentTree = new SegmentTree(N);
    }
    static void solve() throws IOException{
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            switch (query){
                case 1:
                    int idx = Integer.parseInt(st.nextToken());
                    int diff = Integer.parseInt(st.nextToken());
                    segmentTree.update(1, 1, N, idx, diff);
                    break;
                case 2:
                    int left = Integer.parseInt(st.nextToken());
                    int right = Integer.parseInt(st.nextToken());
                    sb.append(segmentTree.sum(1, 1, N, left, right)).append("\n");
                    break;
                default:
                    break;
            }
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
