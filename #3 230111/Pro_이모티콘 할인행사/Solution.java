class Solution {
    static int N, M;
    static int[] emoteRatio;
    static int[] ans;
    static int[] emos;
    static int[][] user;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        ans = new int[]{0, -1};
        N = emoticons.length;
        M = users.length;
        emoteRatio = new int[N];
        emos = emoticons;
        user = users;

        rec(0);
        answer = ans;
        return answer;
    }

    static void rec(int cnt) {
        if (cnt == N) {
            int people = 0;
            int result = 0;
            int[] priceArr = new int[M];
            for (int i = 0; i < N; i++) {
//                System.out.print(emoteRatio[i] + " ");
                for (int j = 0; j < M; j++) {
                    if (user[j][0] <= emoteRatio[i] * 10) {
                        double ratio = emoteRatio[i] / 10.0;
                        int price = ((int) ((double) emos[i] * (1.0 - ratio)));
//                        System.out.print(price + ", ");
                        priceArr[j] += price;
                    }
                }
            }

            for (int i = 0; i < M; i++) {
                if (user[i][1] <= priceArr[i]) {
                    people++;
                } else {
                    result += priceArr[i];
                }
            }
            if (ans[0] < people) {
                ans[0] = people;
                ans[1] = result;
            } else if (ans[0] == people) {
                ans[1] = Math.max(ans[1], result);
            }
            return;
        }

        for (int i = 1; i <= 4; i++) {
            emoteRatio[cnt] = i;
            rec(cnt + 1);
        }
    }
}