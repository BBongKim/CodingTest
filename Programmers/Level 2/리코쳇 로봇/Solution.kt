// 리코쳇 로봇
// https://school.programmers.co.kr/learn/courses/30/lessons/169199

// BFS

// 일반적인 최단 거리 탐색이다.
// 단, 다음 위치를 벽이나 장애물에 부딛힐때로 정해주면된다.

data class Pos(val y: Int, val x: Int, val cnt: Int)

class Solution {
    lateinit var start: Pos
    lateinit var end: Pos
    
    val moveX = intArrayOf(1, 0, -1, 0)
    val moveY = intArrayOf(0, 1, 0, -1)
    
    var N = 0
    var M = 0
    
    fun solution(board: Array<String>): Int {
        var answer: Int = 0
        
        N = board.size
        M = board[0].length
        
        val map = Array(N) { r ->
            CharArray(M) { c -> board[r][c] } 
        }
             
        for (y in map.indices) {
            for (x in map[y].indices) {
                if (map[y][x] == 'R') start = Pos(y, x, 0)
                if (map[y][x] == 'G') end = Pos(y, x, 0)
            }
        }
        
        val visited = Array(N) { BooleanArray(M) { false } }
        val queue = ArrayDeque<Pos>()
        
        visited[start.y][start.x] = true
        queue.addLast(Pos(start.y ,start.x, 0))
        
        while(queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            
            if (cur.y == end.y && cur.x == end.x) return cur.cnt

            for (i in 0 until 4) {
                var nextY = cur.y
                var nextX = cur.x
                
                while(true) {
                    nextY += moveY[i]
                    nextX += moveX[i]
                    
                    if (nextY < 0) {
                        nextY++
                        break
                    }
                    
                    if (nextY >= N) {
                        nextY--
                        break
                    }
                    
                    if (nextX < 0) {
                        nextX++
                        break
                    }
                    
                    if (nextX >= M) {
                        nextX--
                        break
                    }
                    
                    if (map[nextY][nextX] == 'D') {
                        nextY -= moveY[i]
                        nextX -= moveX[i]
                        break
                    }
                }
                
                if (!visited[nextY][nextX]) {
                    visited[nextY][nextX] = true
                    queue.addLast(Pos(nextY, nextX, cur.cnt + 1))
                }
            }
        }
        
        return -1
    }
}