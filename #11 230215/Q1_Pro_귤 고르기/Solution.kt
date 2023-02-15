class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        var ans = 0

        tangerine.groupBy { it } // 귤의 무게가 같은 것 끼리 묶음 -> hashMap 형태
            .values              // hashMap의 value 추출 -> List<List<Int>>
            .map { it.size }     // 내부 list의 사이즈로 변환 = 귤의 개수의 리스트로 변환 -> List<Int>
            .sortedBy { -it }    // 귤의 개수가 많은 순으로 정렬 -> List<Int>
            .fold(0) { sum, count ->     // 정렬한 것을 순회하며 값(count)을 누적(sum)시킨다
                if (sum >= k) sum else { // 누적한 것이 할당량을 초과하면 더 이상 누적하지 않음
                    ans++                     // 할당량을 못채웠으면 누적시키며 종류 증가
                    sum + count
                }
            }

        return ans
    }
}