// 소문난 칠공주
// https://www.acmicpc.net/problem/1941

// 브루트포스, 백트래킹, BFS

// 이 문제는 풀이법이 많을 것 같다.

// 나는 조합을 통한 브루트포스와 BFS를 통해 풀었다.

// 총 25개에서 7개의 위치를 뽑는 경우의수는 48만개 정도기 때문에, 모든 조합을 고려해도 된다.
// 그리고, 뽑은 7개의 위치가 칠공주 조건을 만족하는지를 BFS로 확인해보면 된다.

package Study11

val moveX = intArrayOf(1, 0, -1, 0)
val moveY = intArrayOf(0, -1, 0, 1)

data class Pos(val y: Int, val x: Int)

var answer = 0

val list = listOf(
    Pos(0, 0),
    Pos(0, 1),
    Pos(0, 2),
    Pos(0, 3),
    Pos(0, 4),
    Pos(1, 0),
    Pos(1, 1),
    Pos(1, 2),
    Pos(1, 3),
    Pos(1, 4),
    Pos(2, 0),
    Pos(2, 1),
    Pos(2, 2),
    Pos(2, 3),
    Pos(2, 4),
    Pos(3, 0),
    Pos(3, 1),
    Pos(3, 2),
    Pos(3, 3),
    Pos(3, 4),
    Pos(4, 0),
    Pos(4, 1),
    Pos(4, 2),
    Pos(4, 3),
    Pos(4, 4),
)

val visited = Array(5) { BooleanArray(5) { false } }

object Main2 {
    fun solution(map: List<List<String>>): Int {
        permutation(map, list, 0, 0)
        return answer
    }

    private fun permutation(map: List<List<String>>, list: List<Pos>, start: Int, M: Int) {
        if (M == 7) {
            bfs(map, visited)
            return
        }

        for (i in start until list.size) {
            val temp = list[i]
            if (!visited[temp.y][temp.x]) {
                visited[temp.y][temp.x] = true
                permutation(map, list, i + 1, M + 1)
                visited[temp.y][temp.x] = false
            }
        }
    }

    private fun bfs(originMap: List<List<String>>, roadMap: Array<BooleanArray>) {
        var start = Pos(0, 0)

        for (y in 0..4) {
            for (x in 0..4) {
                if (roadMap[y][x]) {
                    start = Pos(y, x)
                    break
                }
            }
        }

        val queue = ArrayDeque<Pos>()
        val visited = Array(5) { BooleanArray(5) { false } }

        var countS = 0
        var countTotal = 0

        visited[start.y][start.x] = true
        queue.addLast(start)

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            countTotal++
            if (originMap[cur.y][cur.x] == "S") countS++

            for (i in 0..3) {
                val nextY = cur.y + moveY[i]
                val nextX = cur.x + moveX[i]

                if (nextY < 0 || nextY >= 5 || nextX < 0 || nextX >= 5) continue
                if (visited[nextY][nextX]) continue
                if (!roadMap[nextY][nextX]) continue

                visited[nextY][nextX] = true
                queue.addLast(Pos(nextY, nextX))
            }
        }

        if (countS >= 4 && countTotal == 7) answer++
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val map = List(5) { readLine().chunked(1) }

    println(Main2.solution(map))
}