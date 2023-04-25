// 요격 시스템

// 정렬, 그리디

// 끝나는 시간, 시작 시간 순으로 오름차순 정렬한다.
// 그리고, 다음 시작이 이전 끝보다 크거나 같으면 새로운 미사일이 필요한 것으로 처리한다.

// 백준 회의실 배정하고 비슷함

class Solution {
    fun solution(targets: Array<IntArray>): Int {
        var answer: Int = 1
        
        val list = targets.sortedWith(compareBy<IntArray> { it[1] }.thenBy { it[0] })
        
        var end = list[0][1]
        
        for (target in list) {
            if (target[0] >= end) {
                end = target[1]
                answer++
            }
        }
           
        return answer
    }
}