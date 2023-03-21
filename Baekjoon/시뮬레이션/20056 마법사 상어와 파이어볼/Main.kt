// 마법사 상어와 파이어볼
// https://www.acmicpc.net/problem/20056

// 시뮬레이션

// 1.
// 파이어볼을 담고 있는 배열 1개만 사용해서 시뮬레이션 하면, 시간 초과가 난다.
// 그래서, 리스트를 담고 있는 2차원 배열을 만든 다음에 처리하는 파이어볼을 최소화 시켜야 한다.

// 2.
// 파이어볼의 다음 위치가 범위를 넘어 갈때,
// N보다 큰 경우를 먼저 처리하고, 1보다 작아지는 경우를 처리해야한다.

// 안그러면, x나 y가 처리가 제대로 안돼서 0이 되는 경우가 생겨버린다.
// 이 순서를 안지키니 WA가 나와서 한참 헤맸다.

package Study12

data class Fireball(var y: Int, var x: Int, val m: Int, val s: Int, val d: Int)

object Main3 {
    val moveX = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    val moveY = intArrayOf(-1, -1, 0, 1, 1, 1, 0, -1)

    fun solution(N: Int, M: Int, K: Int, fireballs: List<Fireball>): Int {
        var time = K
        var map = Array(N + 1) { Array(N + 1) { mutableListOf<Fireball>() } }
        fireballs.forEach { map[it.y][it.x].add(it) }

        while (time-- > 0) {

            val tempMap = Array(N + 1) { Array(N + 1) { mutableListOf<Fireball>() } }

            for (y in 1..N) {
                for (x in 1..N) {
                    // 파이어볼 이동
                    for (ball in map[y][x]) {
                        ball.y += moveY[ball.d] * ball.s
                        ball.x += moveX[ball.d] * ball.s

                        // 순서 주의
                        if (ball.y > N) ball.y = ball.y % N
                        if (ball.y < 1) ball.y = (ball.y % N) + N
                        if (ball.x > N) ball.x = ball.x % N
                        if (ball.x < 1) ball.x = (ball.x % N) + N

                        tempMap[ball.y][ball.x].add(ball)
                    }
                }
            }

            map = tempMap

            // 2개 이상인 위치에 대한 작업
            for (y in 1..N) {
                for (x in 1..N) {
                    if (map[y][x].size < 2) continue

                    // 파이어볼 합계 계산
                    var isSame = true
                    var totalOdd = 0
                    var totalEven = 0
                    var totalM = 0
                    var totalS = 0
                    var count = 0

                    for (ball in map[y][x]) {
                        totalM += ball.m
                        totalS += ball.s
                        count++

                        if (ball.d.isOdd()) totalOdd++
                        else totalEven++
                    }

                    if (totalEven > 0 && totalOdd > 0) isSame = false

                    // 분할
                    val tempBalls = mutableListOf<Fireball>()
                    val nm = totalM / 5

                    if (isSame && nm > 0) {
                        for (i in 0..6 step 2) tempBalls.add(Fireball(y, x, nm, totalS / count, i))
                    } else if (!isSame && nm > 0) {
                        for (i in 1..7 step 2) tempBalls.add(Fireball(y, x, nm, totalS / count, i))
                    }

                    // 기존 파이어볼 제거 및 추가
                    map[y][x] = tempBalls
                }
            }
        }

        return map.sumOf { list -> list.sumOf { balls -> balls.sumOf { it.m } } }
    }

    private fun Int.isOdd(): Boolean = this % 2 == 1
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M, K) = readLine().split(" ").map { it.toInt() }
    val fireballs = List(M) {
        val (y, x, m, s, d) = readLine().split(" ").map { it.toInt() }
        Fireball(y, x, m, s, d)
    }

    println(Main3.solution(N, M, K, fireballs))
}