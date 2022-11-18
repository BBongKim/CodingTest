// 거리두기 확인하기
// https://school.programmers.co.kr/learn/courses/30/lessons/81302?language=kotlin

// 'P' 일 경우 상하좌우에 'P'가 1명이라도 더 있으면, 답은 0
// 'O' 일 경우 상하좌우에 'P'가 2명이상 있으면, 답은 0

val moveX = arrayOf(1, 0, -1, 0)
val moveY = arrayOf(0, -1, 0, 1)

fun solution(places: Array<Array<String>>): IntArray {
    val answer = IntArray(places.size) { 1 }

    val maps =
        Array(places.size) { mapIdx ->
            Array(places[mapIdx].size) { row ->
                Array(places[mapIdx][row].length) {
                    places[mapIdx][row][it]
                }
            }
        }

    for (mapIdx in maps.indices) {
        val map = maps[mapIdx]

        for (y in map.indices) {
            for (x in map[y].indices) {
                if (map[y][x] == 'P') {
                    for (i in 0..3) {
                        if (!isInBoundary(y, x, i)) continue
                        if (map[y + moveY[i]][x + moveX[i]] == 'P') {
                            answer[mapIdx] = 0
                            break
                        }
                    }
                }

                if (map[y][x] == 'O') {
                    var cnt = 0
                    for (i in 0..3) {
                        if (!isInBoundary(y, x, i)) continue
                        if (map[y + moveY[i]][x + moveX[i]] == 'P') cnt++
                        if (cnt > 1) {
                            answer[mapIdx] = 0
                            break
                        }
                    }
                }
            }
        }
    }

    return answer
}

fun isInBoundary(y: Int, x: Int, moveIdx: Int): Boolean {
    if (y + moveY[moveIdx] >= 5 || y + moveY[moveIdx] < 0) return false
    if (x + moveX[moveIdx] >= 5 || x + moveX[moveIdx] < 0) return false

    return true
}

fun main() {
    val places = arrayOf(
        arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
        arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
        arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
        arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
        arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP")
    )
    for (ans in solution(places)) print("$ans ")
}