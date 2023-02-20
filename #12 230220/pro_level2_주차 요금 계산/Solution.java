import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 현재 주차중인 차들을 저장
        HashMap<String, Integer> parking = new HashMap<>();
        // 차 각각의 누적 분을 저장
        HashMap<String, Integer> times = new HashMap<>();
        // 차량 리스트
        ArrayList<String> cars = new ArrayList<>();

        StringTokenizer st = null;
        for (String record : records) {
            st = new StringTokenizer(record);
            int time = getMin(st.nextToken());
            String car = st.nextToken();

            // 새로운 차 일 경우
            if (!cars.contains(car)) {
                cars.add(car);
                times.put(car, 0);
            }

            // 이미 주차되어 있는 차 일 경우
            if (parking.containsKey(car)) {
                // 현재 주차가 되어있다면, 출차를 해야 한다.
                times.put(car, times.get(car) + (time - parking.get(car)));
                parking.remove(car);
            } else {
                // 없다면 차를 주차한다.
                parking.put(car, time);
            }
        }
        // 차량의 개수만큼 생성
        int[] answer = new int[cars.size()];
        // 차 번호 순으로 정렬
        Collections.sort(cars);
        // 마지막 출차 시간
        int lastTime = getMin("23:59");
        for (int i = 0; i < cars.size(); i++) {
            // 기본요금을 저장한다.
            answer[i] = fees[1];
            String car = cars.get(i);
            // 누적된 시간 중 기본 시간을 뺀다.
            int time = times.get(car) - fees[0];
            // 주차가 되어있다면 마지막 시간을 기준으로 더한다.
            if (parking.containsKey(car))
                time += (lastTime - parking.get(car));
            // 만약 시간이 남았다면 정산한다.(올림)
            if (time > 0)
                answer[i] += (Math.ceil(time / (fees[2] * 1.0)) * fees[3]);
        }

        return answer;
    }

    // 분으로 바꾸기
    public int getMin(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }
}