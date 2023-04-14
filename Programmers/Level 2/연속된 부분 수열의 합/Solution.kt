// 연속된 부분 수열의 합

// 투 포인터

// 투 포인터의 가장 기본적인 문제

class Solution {
    fun solution(sequence: IntArray, k: Int): IntArray {
        var left = 0
        var right = 0
        var sum = sequence[0]
        var minLength = Int.MAX_VALUE
        val answer = IntArray(2) { 0 }
        
        while (right < sequence.size) {
            if (sum == k) {
                val length = right - left + 1
                if (length < minLength) {
                    answer[0] = left
                    answer[1] = right
                    minLength = length
                }
                right++
                if (right < sequence.size) sum += sequence[right]
            } else if (sum < k) {
                right++
                if (right < sequence.size) sum += sequence[right]
            } else if (sum > k) {
                sum -= sequence[left++]
            }
        }
        
        return answer
    }
}