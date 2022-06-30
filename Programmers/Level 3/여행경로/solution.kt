// DFS 문제
// 단순하게 ICN부터 시작하여 가능한 티켓을 구한 후, 알파벳 순으로 정렬한다.
// 그리고, 알파벳이 빠른 티켓부터 재귀 DFS를 실행한다.
// count 수가 티켓의 총 갯수와 같다면 모든 티켓을 사용한 것이므로 배열에 답을 저장한다.
// 처음 도달할 때가 정답이기 때문에 조건문을 걸어주었다.

data class Ticket(val idx: Int, val to: String)

class Solution {
    lateinit var visited: BooleanArray
    val answer = ArrayList<String>()
    val path = ArrayList<Int>()
    var first = true
    
    fun dfs(tickets: Array<Array<String>>, from: String, cnt: Int) {
        if (cnt == tickets.size) {
            if (first) {
                for (p in path) {
                    answer.add(tickets[p][1])
                }
                first = false
            }
            return
        }
        
        val toList = ArrayList<Ticket>()
        
        for (idx in tickets.indices) {
            if (visited[idx]) continue
            
            val(ticketFrom, ticketTo) = tickets[idx]
            
            if (from == ticketFrom) {
                toList.add(Ticket(idx, ticketTo))
            }
        }
        
        toList.sortWith(compareBy<Ticket> { it.to })
        
        for (ticket in toList) {
            visited[ticket.idx] = true
            path.add(ticket.idx)
            dfs(tickets, ticket.to, cnt + 1)
            path.remove(ticket.idx)
            visited[ticket.idx] = false
        }   
    }
    
    fun solution(tickets: Array<Array<String>>): Array<String> {
        visited = BooleanArray(tickets.size) { false }
        
        answer.add("ICN")
        dfs(tickets, "ICN", 0)

        return answer.toTypedArray()
    }
}