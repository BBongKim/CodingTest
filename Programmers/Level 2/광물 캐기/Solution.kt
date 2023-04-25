// 광물 캐기
//https://school.programmers.co.kr/learn/courses/30/lessons/172927#

// 그리디

// 처음에는 다이아 -> 철 -> 돌 곡괭이 순으로만 광물을 캐면 될 줄암.
// 하지만, 다이아 곡괭이를 뒤에서 캐야지 더 피로도가 낮을 수 있음.

// 그래서, 광물을 5개씩 그룹으로 묶음.
// 그룹별 비용을 내림차순으로 정렬.
// 가장 비용이 높은 그룹부터 다이아 -> 철 -> 돌 곡괭이 순으로 캐면 됨.

class Solution {
    
    val DIA = 0
    val IRON = 1
    val STONE = 2
    
    fun hasNoPick(picks: IntArray): Boolean {
        picks.forEach { if (it != 0) return false }
        return true
    }
    
    fun hasNoMinerals(visited: BooleanArray): Boolean {
        visited.forEach { if (!it) return false }
        return true
    }
    
    fun select(picks: IntArray): Int {
        for (i in picks.indices) {
            if (picks[i] > 0) {
                picks[i]--
                return i
            }
        }
        
        return -1
    }
    
    fun solution(picks: IntArray, minerals: Array<String>): Int {
        var answer: Int = 0
        val visited = BooleanArray(minerals.size) { false }
        
        val cntMinerals = picks.sumOf { it } * 5
        val groups = minerals.toList().take(cntMinerals).chunked(5)
        
        val sortedGroups = groups.sortedByDescending { group ->
            var sum = 0
            
            group.forEach { 
                sum += when(it) {
                    "diamond" -> 25
                    "iron" -> 5
                    "stone" -> 1
                    else -> 0
                }
            }
            
            sum
        }

        var idx = 0
        
        while (!hasNoPick(picks)) {
            val pick = select(picks)
            
            if (idx >= sortedGroups.size) break

            for (i in sortedGroups[idx].indices) {   
                when(pick) {
                    DIA -> { answer++ }
                    
                    IRON -> {
                        when (sortedGroups[idx][i]) {
                            "diamond" -> { answer += 5 }
                            "iron" -> { answer++ }
                            "stone" -> { answer++ }
                        }   
                    }
                    
                    STONE -> {
                        when (sortedGroups[idx][i]) {
                            "diamond" -> { answer += 25 }
                            "iron" -> { answer += 5 }
                            "stone" -> { answer++ }
                        }    
                    }                         
                }   
            }
            
            idx++
        }
        
        return answer
    }
}