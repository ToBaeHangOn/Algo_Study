import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        Arrays.sort(book_time, (a, b) -> toInt(a[0]) == toInt(b[0]) ? toInt(a[1]) - toInt(b[1]) : toInt(a[0]) - toInt(b[0]));
        for (String[] time : book_time) {
            System.out.println(time[0] + " ~ " +time[1]);
        }
        ArrayList<Reservation> list = new ArrayList<>();
        for(String[] time : book_time) {
            if(list.size() > 0) {
                boolean isAdded = false;
                for (Reservation reservation : list) {
                    if (reservation.endTime <= toInt(time[0])) {
                        reservation.addReserv(time);
                        isAdded = true;
                        break;
                    }
                }
                if(!isAdded)
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

    static class Reservation {
        int endTime;

        public Reservation(String[] endTime) {
            addReserv(endTime);
        }

        public void addReserv(String[] endTime) {
            StringTokenizer st = new StringTokenizer(endTime[1], ":");
            st.nextToken();
            int min = Integer.parseInt(st.nextToken());
            this.endTime = min + 10 >= 60 ? toInt(endTime[1]) + 100 - min : toInt(endTime[1]) + 10;
        }
    }
}