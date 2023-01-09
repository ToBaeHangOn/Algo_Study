import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        solution(genres, plays);
    }
    static int[] solution(String[] genres, int[] plays) {
        int[] answer = new int[200];
        int cnt = 0;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < genres.length; i++){
            if(!map.containsKey(genres[i])){
                map.put(genres[i], plays[i]);
            }
            else{
                map.put(genres[i], map.get(genres[i]) + plays[i]);
            }
        }
        List<String> genreList = new ArrayList<>();
        for(String key : map.keySet()){
            genreList.add(key);
        }
        Collections.sort(genreList, (o1, o2) -> map.get(o2) - map.get(o1)); // value 값으로 내림차순 list 정렬

        for(int i = 0; i < genreList.size(); i++){
            int max = 0;
            int firstIdx = -1;
            for(int j = 0; j < genres.length; j++){
                if(genreList.get(i).equals(genres[j]) && max < plays[j]){
                    max = plays[j];
                    firstIdx = j;
                }
            }

            max = 0;
            int secondIdx = -1;
            for(int j = 0; j < genres.length; j++){
                if(genreList.get(i).equals(genres[j]) && max < plays[j] && j != firstIdx){
                    max = plays[j];
                    secondIdx = j;
                }
            }
            answer[cnt++] = firstIdx;
            if(secondIdx != -1){ answer[cnt++] = secondIdx;}
        }

        int[] result = new int[cnt];
        for(int i = 0; i < cnt; i++){
            result[i] = answer[i];
        }
        return result;
    }

}
