import java.io.IOException;
import java.util.*;

public class Solution {
    static class Employee implements Comparable<Employee>{
        int attitude; // 태도 점수
        int peer; // 동료 평가
        public Employee(int attitude, int peer) {
            this.attitude = attitude;
            this.peer = peer;
        }
        @Override
        public int compareTo(Employee employee){
            if(employee.attitude < attitude) return 1;
            else if (employee.attitude > attitude) return -1;
            else if (employee.peer < peer) return -1;
            else if (employee.peer > peer) return 1;
            return 0;
        } // 태도 점수 기준으로 내림차순 정렬, 태도 점수가 같으면 동료 평가 기준으로 오름차순 정렬
    }
    static int solution(int[][] scores) {
        int answer = 1;
        Employee Wanho = new Employee(scores[0][0], scores[0][1]);
        int Wanho_sum = Wanho.attitude + Wanho.peer;
        List<Employee> employeeList = new ArrayList<>();
        for(int i = 0; i < scores.length; i++){
            employeeList.add(new Employee(scores[i][0], scores[i][1]));
            if(Wanho.attitude < scores[i][0] && Wanho.peer < scores[i][1])
                return -1; // 완호가 인센티브 대상이 아닐 때
        }
        Collections.sort(employeeList, Collections.reverseOrder()); // 내림차순
//        for(Employee e : employeeList){
//            System.out.println(e.attitude + ", " + e.peer);
//        }
        int maxPeer = 0;
        for(int i = 0; i < employeeList.size(); i++){
            Employee employee = employeeList.get(i);
            maxPeer = Math.max(maxPeer, employee.peer); // 최대 동료 평가 점수 갱신
            if(maxPeer > employee.peer) continue;
            // 태도 점수가 같을 경우 동료 평가 기준 오름차순으로 정렬 했으므로
            // 동료 평가가 maxPeer보다 낮다는 것은 곧 두 점수 다 낮은 경우임

            if(employee.attitude + employee.peer > Wanho_sum) {
                answer++;
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        int[][] scores = {{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}};
        System.out.println(solution(scores));
    }
}
