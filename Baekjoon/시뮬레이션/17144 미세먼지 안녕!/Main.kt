// 시뮬레이션 문제
// 그냥 하라는데로 구현하면 된다.
// 먼지가 동시에 확산하기 때문에, 임시로 먼지값을 합산할 map 2차원 배열을 하나 더 사용하였다.

import java.util.StringTokenizer

lateinit var map: Array<IntArray>
val x_move = intArrayOf(1, 0, -1, 0)
val y_move = intArrayOf(0, -1, 0, 1)
var first = true
var airUp = 0
var airDown = 0

fun solution(N: Int, M: Int, _T: Int): Int {
    var T = _T
    while (T-- > 0) {

        val map2 = Array(N) { IntArray(M) { 0 } }
        // 확산
        for (y in 0 until N) {
            for (x in 0 until M) {
                if (map[y][x] != 0) {
                    val nextDust = map[y][x] / 5
                    var cnt = 0

                    for (i in 0 until 4) {
                        val next_x = x + x_move[i]
                        val next_y = y + y_move[i]

                        if (0 > next_x || next_x >= M || 0 > next_y || next_y >= N || map[next_y][next_x] == -1) continue

                        cnt++
                        map2[next_y][next_x] += nextDust
                    }
                    map2[y][x] += map[y][x] - nextDust * cnt
                }
            }
        }

        // 공기청정기
        // 위에 반시계
        map2[airUp - 1][0] = 0
        for (y in airUp - 2 downTo 0) {
            map2[y + 1][0] = map2[y][0]
        }

        for (x in 1 until M) {
            map2[0][x - 1] = map2[0][x]
        }

        for (y in 1..airUp) {
            map2[y - 1][M - 1] = map2[y][M - 1]
        }

        for (x in M - 1 downTo 1) {
            map2[airUp][x] = map2[airUp][x - 1]

            if (x == 1) map2[airUp][x] = 0
        }

        // 밑에 시계
        map2[airDown + 1][0] = 0
        for (y in airDown + 2 until N) {
            map2[y - 1][0] = map2[y][0]
        }

        for (x in 1 until M) {
            map2[N - 1][x - 1] = map2[N - 1][x]
        }

        for (y in N - 2 downTo airDown) {
            map2[y + 1][M - 1] = map2[y][M - 1]
        }

        for (x in M - 1 downTo 1) {
            map2[airDown][x] = map2[airDown][x - 1]

            if (x == 1) map2[airDown][x] = 0
        }


        map = map2
    }

    var sum = 0
    for (i in map.indices) {
        for (k in map[0].indices) {
            if (map[i][k] == -1) continue
            sum += map[i][k]
        }
    }

    return sum
}

fun main() {
    val (N, M, T) = readln().split(" ").map { it.toInt() }

    map = Array(N) { IntArray(M) { 0 } }

    for (y in map.indices) {
        val st = StringTokenizer(readln(), " ")

        for (x in map[0].indices) {
            map[y][x] = st.nextToken().toInt()

            if (map[y][x] == -1) {
                if (first) {
                    airUp = y
                    first = false
                } else {
                    airDown = y
                }
            }
        }
    }

    print(solution(N, M, T))
}