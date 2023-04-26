// 귤 고르기
// https://school.programmers.co.kr/learn/courses/30/lessons/138476

// 그리디

// 우선순위 큐로 풀었다.

// 개수가 가장 적은 귤부터 제거를 해주면, 남은 귤의 종류 수가 답이 된다.
// 적은 귤을 고를때, 우선순위 큐를 이용해서 뽑아냈다.

import java.util.*

data class Tangerine(val weight: Int, var count: Int) : Comparable<Tangerine> {
    override fun compareTo(other: Tangerine): Int = this.count - other.count
}

class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        var answer: Int = 0
        
        val map = tangerine.toList().groupingBy { it }.eachCount()
        val queue = PriorityQueue<Tangerine>()
        
        var totalCount = 0
        
        for (m in map) {
            totalCount += m.value
            queue.offer(Tangerine(m.key, m.value))
        }
        
        repeat(totalCount - k) {
            val tan = queue.poll()
            tan.count--
            if (tan.count > 0) queue.offer(tan)
        }
         
        return queue.size
    }
}