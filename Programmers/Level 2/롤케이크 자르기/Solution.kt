// 롤케이크 자르기
// https://school.programmers.co.kr/learn/courses/30/lessons/132265

// 해시맵

// 2명에게 (토핑 종류, 개수)의 값을 가지는 해시맵을 부여해준다.
// 그리고, 동생이 모든 토핑을 가지고 있는 상태에서 시작하여
// 앞에서부터 토핑 1개를 철수에게 넘긴다.

// 그때마다, 해시맵의 size를 통해서 철수와 동생이 가지고 있는 토핑 종류 수를 구할 수 있다.

class Solution {
    fun solution(topping: IntArray): Int {
        var answer: Int = 0
        
        val maps = Array(2) { mutableMapOf<Int, Int>() }
        maps[1] = topping.toList().groupingBy { it }.eachCount().toMutableMap()
        
        for (t in topping) {
            maps[1][t] = maps[1][t]!! - 1
            maps[0][t] = maps[0].getOrElse(t) { 0 } + 1
            
            if (maps[1][t] == 0) maps[1].remove(t)
            if (maps[0].size == maps[1].size) answer++
        }
        
        return answer
    }
}