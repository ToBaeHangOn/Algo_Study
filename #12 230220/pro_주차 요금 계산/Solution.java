import java.io.IOException;
import java.util.*;

public class Solution {
    static int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        Map<String, Integer> parking = new HashMap<>(); // 차량번호(Key), 입차 시간(Value)
        Map<String, Integer> cars = new HashMap<>(); // 차량번호(Key), 누적 시간(Value)
        int time;
        String car_num;
        StringTokenizer st;
        for(int i = 0; i < records.length; i++) {
            st = new StringTokenizer(records[i], ":, ");
            time = 60 * Integer.parseInt(st.nextToken());
            time += Integer.parseInt(st.nextToken());
            car_num = st.nextToken();
            if(parking.containsKey(car_num)) {
                time -= parking.get(car_num);
                if(cars.containsKey(car_num)){
                    time += cars.get(car_num);
                } // 시간 누적
                cars.put(car_num, time);
                parking.remove(car_num); // 출차
            }
            else parking.put(car_num, time); // 입차
        }

        for(String str : parking.keySet()){ // 출차하지 않은 차들 출차
            time = 24 * 60 - 1;
            time -= parking.get(str);
            if(cars.containsKey(str)){
                time += cars.get(str);
            }
            cars.put(str, time);
        }
        List<String> list = new ArrayList<>(cars.keySet());
        Collections.sort(list); // 차량번호 기준 오름차순 정렬
        answer = new int[list.size()];
        int i = 0;
        for(String key : list){
            answer[i++] = cost(cars.get(key), fees);
        }
        return answer;
    }
    static int cost(int time, int[] fees){
        if(time <= fees[0]) return fees[1];
        else return fees[1] + (int)Math.ceil((time - fees[0]) / (double)fees[2]) * fees[3];
    } // 요금 계산
    public static void main(String[] args) throws IOException {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(solution(fees, records));
    }
}
