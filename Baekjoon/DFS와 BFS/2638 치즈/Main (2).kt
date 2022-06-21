import java.util.*

// 처음 구역을 나눌 때 치즈와 맞닿으면 치즈의 값을 증가시켜 치즈가 외부공기와 접촉했음을 더 쉽게 구분할 수 있다.
// 해당 풀이로 풀어봤다.
// 실행시간이 200ms 정도 줄어듦

lateinit var map: Array<IntArray>
lateinit var visited: Array<BooleanArray>
val x_move = intArrayOf(1, 0, -1, 0)
val y_move = intArrayOf(0, -1, 0, 1)
var totalCheese = 0

data class Pos(val y: Int, val x: Int)

fun divide(N: Int, M: Int) {
    val queue = LinkedList<Pos>()

    for (y in 0 until N) {
        for (x in 0 until M) {
            if (map[y][x] >= 1) map[y][x] = 1
            visited[y][x] = false
        }
    }

    visited[0][0] = true
    queue.offer(Pos(0, 0))

    while (!queue.isEmpty()) {
        val cur = queue.poll()

        for (i in 0 until 4) {
            val next = Pos(cur.y + y_move[i], cur.x + x_move[i])

            if (next.y < 0 || next.y >= N || next.x < 0 || next.x >= M) continue
            if (map[next.y][next.x] >= 1) {
                map[next.y][next.x]++
                continue
            }
            if (visited[next.y][next.x]) continue

            visited[next.y][next.x] = true
            queue.offer(next)
        }
    }
}

fun solution(N: Int, M: Int): Int {
    val checkQueue = LinkedList<Pos>()
    var answer = 0

    while (totalCheese > 0) {
        for (y in 0 until N) {
            for (x in 0 until M) {
                if (map[y][x] == 0) continue

                if (map[y][x] - 1 >= 2) checkQueue.offer(Pos(y, x))
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