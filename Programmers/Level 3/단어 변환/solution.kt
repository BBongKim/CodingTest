// BFS 문제
// 문자가 1글자만 차이나고 아직 방문하지 않았을 때만 큐에 넣어주면 된다.

import java.util.*

data class Pos(val value: String, val cnt: Int)

class Solution {
    
    fun isPossible(s1: String, s2: String): Boolean {
        var cnt = 0
        for (idx in 0 until s1.length) {
            if (s1[idx] != s2[idx]) cnt++
            if (cnt > 1) return false
        }
        
        return true
    }
    
    fun solution(begin: String, target: String, words: Array<String>): Int {
        var answer = 0
        val visited = BooleanArray(words.size) { false }
        val queue: Queue<Pos> = LinkedList<Pos>()
        
        queue.offer(Pos(begin, 0))
        
        while(!queue.isEmpty()) {
            val cur = queue.poll()
            
            if (cur.value == target) {
                return cur.cnt
            }
            
            for (idx in words.indices) {
                if(visited[idx]) continue
                
                if(isPossible(cur.value, words[idx]) && !visited[idx]) {
                    visited[idx] = true
                    queue.offer(Pos(words[idx], cur.cnt + 1))
                }
            }
        }
                
        return answer
    }
}