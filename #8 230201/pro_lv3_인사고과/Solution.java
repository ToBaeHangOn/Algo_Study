import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        int wonHoSum = scores[0][0] + scores[0][1];
        PriorityQueue<Person> pq = new PriorityQueue<>();

        for (int i = 0; i < scores.length; i++) {
            Person person = new Person(scores[i][0], scores[i][1]);
            if (i == 0)
                person.isWonho = true;
            pq.offer(person);
        }
        //System.out.println(pq);

        int min = -1;
        while (!pq.isEmpty()) {
            Person temp = pq.poll();
            if (min < temp.q2)
                min = temp.q2;
            if (min > temp.q2) {
                if (temp.isWonho)
                    return -1;
                else continue;
            }
            if (temp.q1 + temp.q2 > wonHoSum)
                answer++;
        }
        return answer;
    }

    static class Person implements Comparable<Person> {
        int q1;
        int q2;
        boolean isWonho = false;

        public Person(int q1, int q2) {
            this.q1 = q1;
            this.q2 = q2;
        }

        @Override
        public int compareTo(Person p) {
            if (this.q1 == p.q1)
                return this.q2 - p.q2;
            else return p.q1 - this.q1;
        }

        public String toString() {
            return q1 + ", " + q2 + ", " + isWonho;
        }
    }
}