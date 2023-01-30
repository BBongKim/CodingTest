// 미친 로봇
// https://www.acmicpc.net/problem/1405

// DFS 문제

// 조건부 확률을 계산해야 하기 때문에 DFS를 사용했다.
// DFS를 통해, 단순한 경로일 때만을 탐색한다.
// 남은 이동 횟수가 0이 될때, 조건부 확률을 누적하면 된다.

// 탐색을 위해, 상하좌우 최대로 이동가능한 횟수인 14를 고려하여, 29x29 크기의 맵을 사용했다.

package Study05

object Main {
    data class Pos(val y: Int, val x: Int)

    val moveX = intArrayOf(1, -1, 0, 0)
    val moveY = intArrayOf(0, 0, 1, -1)

    private var answer = 0.0

    private lateinit var visited: Array<Array<Boolean>>

    fun solution(N: Int, chances: List<Int>): Double {

        visited = Array(29) { Array(29) { false } }

        visited[14][14] = true
        dfs(chances, Pos(14, 14), N, 1.0)

        return answer
    }

    private fun dfs(chances: List<Int>, cur: Pos, n: Int, chanceMulti: Double) {
        if (n == 0) {
            answer += chanceMulti
        } else {
            for (i in 0 until 4) {
                if (chances[i] == 0) continue

                val nextY = cur.y + moveY[i]
                val nextX = cur.x + moveX[i]

                if (nextY < 0 || nextX < 0) continue
                if (visited[nextY][nextX]) continue

                visited[nextY][nextX] = true
                dfs(chances, Pos(nextY, nextX), n - 1, chanceMulti * (chances[i] * 0.01))
                visited[nextY][nextX] = false
            }
        }
    }
}

fun main() {
    val input = readln().split(" ").map { it.toInt() }
    val N = input[0]
    val chances = input.subList(1, 5)

    println(Main.solution(N, chances))
}