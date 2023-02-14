import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        Arrays.sort(book_time, (a, b) -> toInt(a[0]) == toInt(b[0]) ? toInt(a[1]) - toInt(b[1]) : toInt(a[0]) - toInt(b[0]));
        for (String[] time : book_time) {
            System.out.println(time[0] + " ~ " + time[1]);
        }
        ArrayList<Reservation> list = new ArrayList<>();
        for (String[] time : book_time) {
            if (list.size() > 0) {
                boolean isAdded = false;
                for (Reservation reservation : list) {
                    if (reservation.endMinute <= getMinutes(time[0])) {
                        reservation.addReserv(time[1]);
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded)
                    list.add(new Reservation(time));
            } else {
                list.add(new Reservation(time));
            }
        }
        answer = list.size();
        return answer;
    }

    static int toInt(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        StringBuilder sb = new StringBuilder();
        sb.append(st.nextToken()).append(st.nextToken());
        return Integer.parseInt(sb.toString());
    }

    static int getMinutes(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());

        return hour * 60 + minute;
    }

    static class Reservation {
        int startMinute;
        int endMinute;

        public Reservation(String[] time) {
            this.startMinute = getMinutes(time[0]);
            // 종료시간 10분 더하기
            this.endMinute = getMinutes(time[1]) + 10;
        }


        public void addReserv(String endTime) {
            int min = getMinutes(endTime);
            this.endMinute = min + 10;
        }
    }
}