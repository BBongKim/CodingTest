// 연속 부분 수열 합의 개수
// https://school.programmers.co.kr/learn/courses/30/lessons/131701

// 투 포인터 (풀이법은 다양해 보임)

// 개인적으로 부분합 구할때는 투 포인터가 편해서, 이를 이용해서 풀었음.
// 시간 복잡도는 O(N^2)

class Solution {
    fun solution(elements: IntArray): Int {
        var answer: Int = 0
        val N = elements.size
        val set = mutableSetOf<Int>()
        
        for (i in 1 .. N) {
            var sum = elements.slice(0 .. i - 1).sumOf { it }
            set.add(sum)
            
            var left = 0
            var right = i - 1
            
            while(left < N) {
                right++
                right %= N
                sum += elements[right]
                sum -= elements[left++]
                set.add(sum)
            }
        }
        
        return set.size
    }
}