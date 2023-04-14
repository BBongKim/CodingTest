// 과제 진행하기

// 시뮬레이션(?)

// 조심할점
// 과제가 밀려서 time이 24시간을 넘어서 끝날 수 있고, 최대 10만까지는 생각해주어야 한다.

import java.util.*

data class Plan(val name: String, val start: Int, val end: Int): Comparable<Plan> {
    override fun compareTo(other: Plan) = this.start - other.start
}

class Solution {
    fun getTime(time: String): Int {
        val (hour, min) = time.split(":").map { it.toInt() }
        return hour * 60 + min
    }
    
    fun solution(plans: Array<Array<String>>): List<String> {
        val answer = mutableListOf<String>()
        val list = mutableListOf<Plan>()
        val queue = ArrayDeque<Pair<Int, String>>()
        
        // 초기화
        for (plan in plans) {
            val start = getTime(plan[1])
            val end = start + plan[2].toInt()
            list.add(Plan(plan[0], start, end))
        }
        
        // 정렬
        list.sort()

        // 시뮬레이션 시작
        var curIdx = 0
        var cur = list[curIdx]
    
        for (t in cur.start until 100001) {
            // 과제 끝났는지 확인
            if (t == cur.end) {
                answer.add(cur.name)
                
                // 중단된 과제 있는지 확인
                if (queue.isNotEmpty() && 
                        !(curIdx + 1 < list.size && t == list[curIdx + 1].start)) {
                    val p = queue.removeFirst()
                    cur = Plan(p.second, t, t + p.first)
                }
            }
        
            // 새로운 과제 있는지 확인
            if (curIdx + 1 < list.size && t == list[curIdx + 1].start) {
                if (cur.end - t > 0) queue.addFirst(Pair(cur.end - t, cur.name))
                cur = list[++curIdx]
            }           
        }
        
        return answer
    }
}