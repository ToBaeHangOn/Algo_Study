import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> result = new ArrayList<>();
        // 장르 탐색용
        int index = 0;
        // 장르 구별용
        HashMap<String, Integer> map = new HashMap<>();
        // 장르와 노래 저장
        ArrayList<PriorityQueue<Song>> list = new ArrayList<>();
        ArrayList<Genre> playTimeList = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            // 처음 들어온 장르라면
            if (!map.containsKey(genres[i])) {
                map.put(genres[i], index);
                list.add(new PriorityQueue<>());
                list.get(index).add(new Song(i, plays[i]));
                playTimeList.add(index, new Genre(index, plays[i]));
                index++;
            }
            // 이전에 들어온 장르라면
            else {
                list.get(map.get(genres[i])).add(new Song(i, plays[i]));
                playTimeList.get(map.get(genres[i])).totalPlayTimes += plays[i];
            }
        }
        // 총 재생횟수로 정렬.
        playTimeList.sort(Genre::compareTo);

//        for (PriorityQueue<Song> p : list) {
//            for (Song s : p) {
//                System.out.println(s);
//            }
//        }

        // 출력을 위해 탐색하기
        for (Genre g : playTimeList) {
            // 2보다 작으면 pq 사이즈, 아니면 2로 한계 설정.
            int limit = list.get(g.index).size() > 2 ? 2 : list.get(g.index).size();
//            System.out.println(g);
            // id 값 뽑아내기
            for (int i = 0; i < limit; i++) {
                result.add(list.get(g.index).poll().id);
            }
        }
        // 제출용 답변 생성
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    // 장르를 저장하는 클래스
    static class Genre implements Comparable<Genre> {
        int index;
        int totalPlayTimes;

        public Genre(int index, int totalPlayTimes) {
            this.index = index;
            this.totalPlayTimes = totalPlayTimes;
        }

        @Override
        public int compareTo(Genre g) {
            // 총 재생 횟수 높은 순으로 정렬.
            return g.totalPlayTimes - this.totalPlayTimes;
        }

        @Override
        public String toString() {
            return "index : " + index + ", total : " + totalPlayTimes;
        }
    }

    // 노래를 저장하는 클래스
    static class Song implements Comparable<Song> {
        int id;
        int playTimes;

        public Song(int id, int playTimes) {
            this.id = id;
            this.playTimes = playTimes;
        }

        @Override
        public int compareTo(Song s) {
            if (s.playTimes == this.playTimes) {
//                System.out.println(s.id + ", " + s.playTimes + " : " + this.id + ", " + this.playTimes);
                // id 작은 순으로 정렬
                return this.id - s.id;
            }
            // 플레이 타임 높은 순으로 정렬
            return s.playTimes - this.playTimes;
        }

        @Override
        public String toString() {
            return "id : " + id + ", pt : " + playTimes;
        }
    }
}