class Solution {
    public int solution(int storey) {
        int answer = 0;
        String str = storey+"";
        StringBuilder sb = new StringBuilder(str);
        String rev = sb.reverse().toString();
        int addNum = 0;
        for (int i = 0; i < rev.length(); i++) {
            int temp = rev.charAt(i) - '0' + addNum;
            if(temp >= 10) {
                if(i == rev.length() - 1) {
                    answer += 1;
                } else {
                    temp = temp - 10;
                    answer += temp;
                    addNum = 1;
                }
            }
            else if(temp > 5) {
                if(i == rev.length() - 1) {
                    answer += (10 - temp) + 1;
                } else {
                    answer += (10 - temp);
                    addNum = 1;
                }
            }
            else {
                if(i == rev.length() - 2 && temp == 5) {
                    if(rev.charAt(i + 1) - '0' > 5) {
                        answer += temp;
                        addNum = 1;
                    } else {
                        answer += temp;
                        addNum = 0;
                    }
                } else{
                    answer += temp;
                    addNum = 0;
                }

            }
        }
        return answer;
    }
}