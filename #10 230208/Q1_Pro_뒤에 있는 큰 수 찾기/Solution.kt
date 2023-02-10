import java.util.LinkedList

class Solution {
    fun solution(numbers: IntArray): IntArray {
        val stack = LinkedList<Int>()
        val n = numbers.size
        var i = 0 // numbers에 접근할 인덱스

        val ans = IntArray(n)
        while (i < n) {
            val num = numbers[i]

            when {
                // case 1: 스택이 비어있음 -> 단순 삽입
                stack.isEmpty() -> stack.push(i++ to num)

                // case 2: 스택의 top이 현재 수 보다 작음 -> top의 뒷 큰수 저장
                stack.top() < num -> {
                    val (_, index) = stack.pop() to stack.pop()
                    ans[index] = num
                }

                // case 3: 스택의 top이 현재 수와 같거나 큼 -> 현재 수 삽입
                stack.top() >= num -> stack.push(i++ to num)
            }
        }

        // 뒷 큰수가 없는 수에 -1 저장
        while (stack.isNotEmpty()) {
            val (_, index) = stack.pop() to stack.pop()
            ans[index] = -1
        }

        return ans
    }
}

fun LinkedList<Int>.top(): Int = peekFirst()

fun LinkedList<Int>.push(info: Pair<Int, Int>) = apply {
    push(info.first)
    push(info.second)
}