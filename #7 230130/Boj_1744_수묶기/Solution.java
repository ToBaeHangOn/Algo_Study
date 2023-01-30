import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int ans = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //  조건 1. 음수 * 음수, 양수 * 양수 최대값, 단! (양수 *1)은 (양수+1)값보다 작으므로 1보다 커야 함.
        //  조건 2. 음수 * 0은 최대값, 그러므로 1보다 작을 때 곱해야 함.
        Arrays.sort(arr);
        // 왼쪽 오른쪽에서 각각 접근
        int left = 0;
        int right = N - 1;
        while (left < right) {
            if (arr[left] < 1 && arr[left + 1] < 1) {
                ans += arr[left] * arr[left + 1];
                left += 2;
            } else {
                break;
            }
        }
        while (right > 0) {
            if (arr[right] > 1 && arr[right - 1] > 1) {
                ans += arr[right] * arr[right - 1];
                right -= 2;
            } else {
                break;
            }
        }

        while (right >= left) {
            ans += arr[right--];
        }
        System.out.println(ans);
    }
}
