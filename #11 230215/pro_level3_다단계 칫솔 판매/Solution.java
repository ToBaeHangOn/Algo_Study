import java.util.HashMap;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        // 자신과 부모를 연결
        HashMap<String, String> parentMap = new HashMap<>();
        // 자신과 자신의 순서 -는 패런트
        HashMap<String, Integer> memberIndexMap = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            parentMap.put(enroll[i], referral[i]);
            memberIndexMap.put(enroll[i], i);
        }

        for (int i = 0; i < seller.length; i++) {
            // 현재 판매원
            String now = seller[i];
            // 이익 * 100 %
            int profit = 100 * amount[i];

            // 현재 판매원으로부터 루트노드까지 계산
            while (!now.equals("-")) {
                // 부모가 가져가는 이익
                int parentProfit = profit / 10;
                // 자신이 가져갈 이익
                int myProfit = profit - parentProfit;

                // 자신의 인덱스에 이익을 더한다.
                answer[memberIndexMap.get(now)] += myProfit;

                // 노드는 부모로 이동하면서 수익을 부모에게 넘겨준 금액으로 초기화한다
                now = parentMap.get(now);
                profit /= 10;

                // 10%의 금액이 1원 미만인 경우 모두 가진다.
                if (profit < 1) {
                    break;
                }
            }
        }

        return answer;
    }
}