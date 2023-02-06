import java.io.IOException;
import java.util.*;

public class Solution {
    static Integer[] require_room = new Integer[24 * 60 + 10];
    static List<Book> book_list = new ArrayList<>();
    static StringTokenizer st;
    static class Book{
        int start, end;
        public Book(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static int solution(String[][] book_time) {
        Arrays.fill(require_room, 0);
        for(int i = 0; i < book_time.length; i++){
            time_to_book(book_time[i]);
        }
        for(int i = 0; i < book_list.size(); i++){
            for(int j = book_list.get(i).start; j < book_list.get(i).end; j++){
                require_room[j]++;
            }
        }
        Arrays.sort(require_room, Collections.reverseOrder());
        return require_room[0];
    }
    static void time_to_book(String[] str){
        int start = 0, end = 0;
        st = new StringTokenizer(str[0], ":");
        start += 60 * Integer.parseInt(st.nextToken());
        start += Integer.parseInt(st.nextToken());

        st = new StringTokenizer(str[1], ":");
        end += 60 * Integer.parseInt(st.nextToken());
        end += Integer.parseInt(st.nextToken()) + 10;
        book_list.add(new Book(start, end));
    }
    public static void main(String[] args) throws IOException {
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        System.out.println(solution(book_time));
    }
}
