class Solution {
    public int solution(int storey) {
        int answer = 0;
//        String str = storey + "";
//        StringBuilder sb = new StringBuilder(str);
//        String rev = sb.reverse().toString();
//        int addNum = 0;
//        for (int i = 0; i < rev.length(); i++) {
//            int temp = rev.charAt(i) - '0' + addNum;
//            if(temp >= 10) {
//                if(i == rev.length() - 1) {
//                    answer += 1;
//                } else {
//                    temp = temp - 10;
//                    answer += temp;
//                    addNum = 1;
//                }
//            }
//            else if(temp > 5) {
//                if(i == rev.length() - 1) {
//                    answer += (10 - temp) + 1;
//                } else {
//                    answer += (10 - temp);
//                    addNum = 1;
//                }
//            }
//            else {
//                if(i == rev.length() - 2 && temp == 5) {
//                    if(rev.charAt(i + 1) - '0' > 5) {
//                        answer += temp;
//                        addNum = 1;
//                    } else {
//                        answer += temp;
//                        addNum = 0;
//                    }
//                } else{
//                    answer += temp;
//                    addNum = 0;
//                }
//
//            }
//        }

        // 방식은 이전과 동일하다.
        // 하지만 4이하일 떄, 5일때, 6이상 일 떄 3 케이스로 구분해야 한다.
        // 5일 경우 앞자리의 수가 4이하면 빼고, 5이상이면 더하는게 이득이다.
        
        String intToString = String.valueOf(storey);
        char[] chars = intToString.toCharArray();
        int[] nums = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            nums[i] = chars[i] - '0';
        }


        for (int i = nums.length - 1; i >= 0; i--) {
            int temp = nums[i];
            // 이전 연산을 통해 10이 되었을 떄
            if (temp == 10) {
                // 마지막 숫자일 때
                if (i - 1 < 0) {
                    answer += 1;
                    continue;
                }
                // 이미 0으로 바뀌었음으로 다음 숫자를 업데이트 한다.
                nums[i - 1]++;
                continue;
            }
            // 6 이상 일 때
            if (temp >= 6) {
                answer += (10 - temp);
                // 마지막 숫자라면
                if (i - 1 < 0) {
                    answer += 1;
                    continue;
                }
                nums[i - 1]++;
            }
            // 4이하 일 때
            else if (temp <= 4) {
                answer += temp;
            }
            // 5일 때
            else {
                // 마지막 숫자라면
                if (i - 1 < 0) {
                    answer += 5;
                }
                // 그렇지 않다면 다음 숫자가 5보다 작은지 큰지 비교
                else {
                    if (nums[i - 1] < 5) {
                        answer += temp;
                    } else {
                        answer += 5;
                        nums[i - 1]++;
                    }
                }
            }
        }
        return answer;
    }
}