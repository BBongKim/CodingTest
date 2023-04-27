// 테이블 해시 함수
// https://school.programmers.co.kr/learn/courses/30/lessons/147354

// 구현

// 하라는데로 정렬해주고, xor 누적 해주면된다.

class Solution {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
        var answer: Int = 0 
        
        data.sortWith(compareBy<IntArray> { it[col - 1] }.thenByDescending { it[0] })
        
        for (i in row_begin .. row_end) {
            var sum = 0
            for (d in data[i - 1]) {
                sum += d.rem(i)
            }
            answer = answer xor sum
        }
        
        return answer
    }
}