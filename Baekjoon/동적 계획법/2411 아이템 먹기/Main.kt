// 아이템 먹기
// https://www.acmicpc.net/problem/2411

// DP

// 일반적인 경로 개수 구하는 DP를 사용하면 된다.

// 단, 몇가지를 추가적으로 생각했다.
// 1. 좌표가 이상해서 맵 전체를 상하 반전시킨 경우로 생각한다.
// 2. 그러면, 오른쪽 or 아래로만 이동이 가능하다.
// 3, 그리고, 아이템을 다 먹어야 하기 때문에, 아이템간의 경로 개수만 구해야 한다.
// 4. 아이템의 좌표는 좌상단에서 우하단 순으로 정렬해야한다.

// 처음에 전체적인 아이디어는 맞았다.
// 근데, 계속 WA가 나왔다.
// 그래서 한참 해멨는데, 알고보니 45번 라인부터 dp를 계산하는 조건문에 오류가 있었다...

package Study09

const val ITEM = 2
const val WALL = 1

data class Pos2(val y: Int, val x: Int)

object Main2 {

    fun solution(N: Int, M: Int, A: Int, map: Array<IntArray>, items: List<Pos2>): Int {
        val dp = Array(N + 1) { IntArray(M + 1) { 0 } }

        val sets = items.toMutableSet()
        sets.add(Pos2(1, 1))
        sets.add(Pos2(N, M))
        val points = sets.toList().sortedWith(compareBy<Pos2> { it.y }.thenBy { it.x })

        for (i in 0 until points.size - 1) {
            val start = points[i]
            val end = points[i + 1]

            for (y in start.y..end.y) {
                for (x in start.x..end.x) {

                    if (y == 1 && x == 1) dp[y][x] = 1
                    else if (map[y][x] == WALL) dp[y][x] = 0
                    else if (map[y - 1][x] == WALL && map[y][x - 1] == WALL) dp[y][x] = 0
                    else if (map[y][x - 1] == WALL) dp[y][x] = dp[y - 1][x]
                    else if (map[y - 1][x] == WALL) dp[y][x] = dp[y][x - 1]
                    else dp[y][x] = dp[y][x - 1] + dp[y - 1][x]
                }
            }
        }

        return dp[N][M]
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M, A, B) = readLine().split(" ").map { it.toInt() }

    val items = List(A) {
        val (y, x) = readLine().split(" ").map { it.toInt() }
        Pos2(y, x)
    }

    val walls = List(B) {
        val (y, x) = readLine().split(" ").map { it.toInt() }
        Pos2(y, x)
    }

    val map = Array(N + 1) { IntArray(M + 1) { 0 } }

    items.forEach { map[it.y][it.x] = ITEM }
    walls.forEach { map[it.y][it.x] = WALL }

    println(Main2.solution(N, M, A, map, items))
}