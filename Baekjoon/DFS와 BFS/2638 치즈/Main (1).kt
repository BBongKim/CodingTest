import java.util.*

// BFS 문제
// 먼저 (0,0)부터 BFS를 시작하여 각 구역별로 다른 값으로 설정했다. (치즈 내부는 0 이상의 아닌 다른 값)
// 그 다음에, 각 치즈마다 외부 공기(0 값)개수가 2이상이면 임시 큐에 넣었다가 한번에 0으로 초기화 해주었다.

// 다른 풀이를 보니, 처음 구역을 나눌 때 치즈와 맞닿으면 치즈의 값을 증가시켜 치즈가 외부공기와 접촉했음을 더 쉽게 구분할 수 있다.

lateinit var map: Array<IntArray>
lateinit var visited: Array<BooleanArray>
val x_move = intArrayOf(1, 0, -1, 0)
val y_move = intArrayOf(0, -1, 0, 1)
var totalCheese = 0

data class Pos(val y: Int, val x: Int)

fun divide(N: Int, M: Int) {
    val queue = LinkedList<Pos>()
    var num = 0

    for (y in 0 until N) {
        for (x in 0 until M) {
            if (map[y][x] == 1 || visited[y][x]) continue

            map[y][x] = num
            visited[y][x] = true
            queue.offer(Pos(y, x))

            while (!queue.isEmpty()) {
                val cur = queue.poll()

                for (i in 0 until 4) {
                    val next = Pos(cur.y + y_move[i], cur.x + x_move[i])

                    if (next.y < 0 || next.y >= N || next.x < 0 || next.x >= M) continue
                    if (visited[next.y][next.x] || map[next.y][next.x] == 1) continue

                    map[next.y][next.x] = num
                    visited[next.y][next.x] = true
                    queue.offer(next)
                }
            }

            num += 2
        }
    }

    visited = Array(N) { BooleanArray(M) { false } }
}

fun solution(N: Int, M: Int): Int {
    val checkQueue = LinkedList<Pos>()
    var answer = 0

    while (totalCheese > 0) {
        for (y in 0 until N) {
            for (x in 0 until M) {
                if (map[y][x] != 1) continue

                var cnt = 0

                for (i in 0 until 4) {
                    val next = Pos(y + y_move[i], x + x_move[i])

                    if (next.y < 0 || next.y >= N || next.x < 0 || next.x >= M) continue
                    if (map[next.y][next.x] == 0) cnt++
                }

                if (cnt >= 2) checkQueue.offer(Pos(y, x))
            }
        }

        totalCheese -= checkQueue.size
        for (pos in checkQueue) {
            map[pos.y][pos.x] = 0
        }
        checkQueue.clear()
        divide(N, M)
        answer++
    }

    return answer
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    map = Array(N) { IntArray(M) { 0 } }
    visited = Array(N) { BooleanArray(M) { false } }

    repeat(N) { idx ->
        map[idx] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    for (y in 0 until N) {
        for (x in 0 until M) {
            if (map[y][x] == 1) totalCheese++
        }
    }

    divide(N, M)
    println(solution(N, M))
}