// 감시
// https://www.acmicpc.net/problem/15683

// 시뮬레이션

// cctv는 통과할 수 있는 것을 주의하면된다.

// 근데, 왜 저번보다 코드가 더 길어졌지...ㅠㅠ

package Study05

object Main4 {
    data class Pos(val y: Int, val x: Int, val dir: Int)
    data class CCTV(val pos: Pos, val type: Int)

    const val RIGHT = 0
    const val DOWN = 1
    const val LEFT = 2
    const val UP = 3

    const val nothing = -1

    const val watching = 8

    var N = 0
    var M = 0

    var answer = Int.MAX_VALUE

    private lateinit var visited: Array<Array<Boolean>>

    fun solution(n: Int, m: Int, map: Array<IntArray>): Int {
        N = n
        M = m
        visited = Array(N) { Array(M) { false } }

        val cctvs = mutableListOf<CCTV>()

        for (y in map.indices) {
            for (x in map[y].indices) {
                if (map[y][x] in (1..5)) cctvs.add(CCTV(Pos(y, x, nothing), map[y][x]))
            }
        }

        dfs(map, cctvs, 0)

        return answer
    }

    private fun dfs(map: Array<IntArray>, cctvs: List<CCTV>, idx: Int) {
        if (idx == cctvs.size) {
            calulateEmpty(map)
        } else {
            for (i in 0 until 4) {
                val unwatchQueue = ArrayDeque<Pos>()
                watch(map, cctvs[idx], i, watching, unwatchQueue)
                dfs(map, cctvs, idx + 1)
                unwatch(map, unwatchQueue)
            }
        }
    }

    private fun calulateEmpty(map: Array<IntArray>) {
        var cnt = 0

        for (ma in map) {
            for (m in ma) {
                if (m == 0) cnt++
            }
        }

        answer = minOf(answer, cnt)
    }

    private fun unwatch(map: Array<IntArray>, unwatchQueue: ArrayDeque<Pos>) {
        while (unwatchQueue.isNotEmpty()) {
            val pos = unwatchQueue.removeFirst()
            map[pos.y][pos.x] = 0
        }
    }

    private fun watch(map: Array<IntArray>, cctv: CCTV, direction: Int, mask: Int, unwatchQueue: ArrayDeque<Pos>) {
        val queue = ArrayDeque<Pos>()

        val curY = cctv.pos.y
        val curX = cctv.pos.x
        val rightNextX = curX + 1
        val leftNextX = curX - 1
        val upNextY = curY - 1
        val downNextY = curY + 1

        when (cctv.type) {
            1 -> {
                when (direction) {
                    RIGHT -> if (isPossible(map, curY, rightNextX, mask)) {
                        if (hasToUnWatchLater(map, curY, rightNextX)) {
                            unwatchQueue.addLast(Pos(curY, rightNextX, RIGHT))
                            map[curY][rightNextX] = mask
                        }
                        queue.addLast(Pos(curY, rightNextX, RIGHT))
                    }

                    DOWN -> if (isPossible(map, downNextY, curX, mask)) {
                        if (hasToUnWatchLater(map, downNextY, curX)) {
                            unwatchQueue.addLast(Pos(downNextY, curX, DOWN))
                            map[downNextY][curX] = mask
                        }
                        queue.addLast(Pos(downNextY, curX, DOWN))
                    }

                    LEFT -> if (isPossible(map, curY, leftNextX, mask)) {
                        if (hasToUnWatchLater(map, curY, leftNextX)) {
                            unwatchQueue.addLast(Pos(curY, leftNextX, LEFT))
                            map[curY][leftNextX] = mask
                        }
                        queue.addLast(Pos(curY, leftNextX, LEFT))
                    }

                    UP -> if (isPossible(map, upNextY, curX, mask)) {
                        if (hasToUnWatchLater(map, upNextY, curX)) {
                            unwatchQueue.addLast(Pos(upNextY, curX, UP))
                            map[upNextY][curX] = mask
                        }
                        queue.addLast(Pos(upNextY, curX, UP))
                    }
                }
            }

            2 -> {
                when (direction) {
                    RIGHT, LEFT -> {
                        if (isPossible(map, curY, rightNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, rightNextX)) {
                                unwatchQueue.addLast(Pos(curY, rightNextX, RIGHT))
                                map[curY][rightNextX] = mask
                            }
                            queue.addLast(Pos(curY, rightNextX, RIGHT))
                        }
                        if (isPossible(map, curY, leftNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, leftNextX)) {
                                unwatchQueue.addLast(Pos(curY, leftNextX, LEFT))
                                map[curY][leftNextX] = mask
                            }
                            queue.addLast(Pos(curY, leftNextX, LEFT))
                        }
                    }

                    DOWN, UP -> {
                        if (isPossible(map, downNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, downNextY, curX)) {
                                unwatchQueue.addLast(Pos(downNextY, curX, DOWN))
                                map[downNextY][curX] = mask
                            }
                            queue.addLast(Pos(downNextY, curX, DOWN))
                        }
                        if (isPossible(map, upNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, upNextY, curX)) {
                                unwatchQueue.addLast(Pos(upNextY, curX, UP))
                                map[upNextY][curX] = mask
                            }
                            queue.addLast(Pos(upNextY, curX, UP))
                        }
                    }
                }
            }

            3 -> {
                when (direction) {
                    RIGHT -> {
                        if (isPossible(map, curY, rightNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, rightNextX)) {
                                unwatchQueue.addLast(Pos(curY, rightNextX, RIGHT))
                                map[curY][rightNextX] = mask
                            }
                            queue.addLast(Pos(curY, rightNextX, RIGHT))
                        }
                        if (isPossible(map, upNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, upNextY, curX)) {
                                unwatchQueue.addLast(Pos(upNextY, curX, UP))
                                map[upNextY][curX] = mask
                            }
                            queue.addLast(Pos(upNextY, curX, UP))
                        }
                    }

                    DOWN -> {
                        if (isPossible(map, curY, rightNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, rightNextX)) {
                                unwatchQueue.addLast(Pos(curY, rightNextX, RIGHT))
                                map[curY][rightNextX] = mask
                            }
                            queue.addLast(Pos(curY, rightNextX, RIGHT))
                        }
                        if (isPossible(map, downNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, downNextY, curX)) {
                                unwatchQueue.addLast(Pos(downNextY, curX, DOWN))
                                map[downNextY][curX] = mask
                            }
                            queue.addLast(Pos(downNextY, curX, DOWN))
                        }
                    }

                    LEFT -> {
                        if (isPossible(map, curY, leftNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, leftNextX)) {
                                unwatchQueue.addLast(Pos(curY, leftNextX, LEFT))
                                map[curY][leftNextX] = mask
                            }
                            queue.addLast(Pos(curY, leftNextX, LEFT))
                        }
                        if (isPossible(map, downNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, downNextY, curX)) {
                                unwatchQueue.addLast(Pos(downNextY, curX, DOWN))
                                map[downNextY][curX] = mask
                            }
                            queue.addLast(Pos(downNextY, curX, DOWN))
                        }
                    }

                    UP -> {
                        if (isPossible(map, curY, leftNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, leftNextX)) {
                                unwatchQueue.addLast(Pos(curY, leftNextX, LEFT))
                                map[curY][leftNextX] = mask
                            }
                            queue.addLast(Pos(curY, leftNextX, LEFT))
                        }
                        if (isPossible(map, upNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, upNextY, curX)) {
                                unwatchQueue.addLast(Pos(upNextY, curX, UP))
                                map[upNextY][curX] = mask
                            }
                            queue.addLast(Pos(upNextY, curX, UP))
                        }
                    }
                }
            }

            4 -> {
                when (direction) {
                    RIGHT -> {
                        if (isPossible(map, curY, rightNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, rightNextX)) {
                                unwatchQueue.addLast(Pos(curY, rightNextX, RIGHT))
                                map[curY][rightNextX] = mask
                            }
                            queue.addLast(Pos(curY, rightNextX, RIGHT))
                        }
                        if (isPossible(map, upNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, upNextY, curX)) {
                                unwatchQueue.addLast(Pos(upNextY, curX, UP))
                                map[upNextY][curX] = mask
                            }
                            queue.addLast(Pos(upNextY, curX, UP))
                        }
                        if (isPossible(map, downNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, downNextY, curX)) {
                                unwatchQueue.addLast(Pos(downNextY, curX, DOWN))
                                map[downNextY][curX] = mask
                            }
                            queue.addLast(Pos(downNextY, curX, DOWN))
                        }
                    }

                    DOWN -> {
                        if (isPossible(map, curY, rightNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, rightNextX)) {
                                unwatchQueue.addLast(Pos(curY, rightNextX, RIGHT))
                                map[curY][rightNextX] = mask
                            }
                            queue.addLast(Pos(curY, rightNextX, RIGHT))
                        }
                        if (isPossible(map, curY, leftNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, leftNextX)) {
                                unwatchQueue.addLast(Pos(curY, leftNextX, LEFT))
                                map[curY][leftNextX] = mask
                            }
                            queue.addLast(Pos(curY, leftNextX, LEFT))
                        }
                        if (isPossible(map, downNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, downNextY, curX)) {
                                unwatchQueue.addLast(Pos(downNextY, curX, DOWN))
                                map[downNextY][curX] = mask
                            }
                            queue.addLast(Pos(downNextY, curX, DOWN))
                        }
                    }

                    LEFT -> {
                        if (isPossible(map, curY, leftNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, leftNextX)) {
                                unwatchQueue.addLast(Pos(curY, leftNextX, LEFT))
                                map[curY][leftNextX] = mask
                            }
                            queue.addLast(Pos(curY, leftNextX, LEFT))
                        }
                        if (isPossible(map, upNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, upNextY, curX)) {
                                unwatchQueue.addLast(Pos(upNextY, curX, UP))
                                map[upNextY][curX] = mask
                            }
                            queue.addLast(Pos(upNextY, curX, UP))
                        }
                        if (isPossible(map, downNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, downNextY, curX)) {
                                unwatchQueue.addLast(Pos(downNextY, curX, DOWN))
                                map[downNextY][curX] = mask
                            }
                            queue.addLast(Pos(downNextY, curX, DOWN))
                        }
                    }

                    UP -> {
                        if (isPossible(map, curY, rightNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, rightNextX)) {
                                unwatchQueue.addLast(Pos(curY, rightNextX, RIGHT))
                                map[curY][rightNextX] = mask
                            }
                            queue.addLast(Pos(curY, rightNextX, RIGHT))
                        }
                        if (isPossible(map, curY, leftNextX, mask)) {
                            if (hasToUnWatchLater(map, curY, leftNextX)) {
                                unwatchQueue.addLast(Pos(curY, leftNextX, LEFT))
                                map[curY][leftNextX] = mask
                            }
                            queue.addLast(Pos(curY, leftNextX, LEFT))
                        }
                        if (isPossible(map, upNextY, curX, mask)) {
                            if (hasToUnWatchLater(map, upNextY, curX)) {
                                unwatchQueue.addLast(Pos(upNextY, curX, UP))
                                map[upNextY][curX] = mask
                            }
                            queue.addLast(Pos(upNextY, curX, UP))
                        }
                    }
                }
            }

            5 -> {
                if (isPossible(map, curY, rightNextX, mask)) {
                    if (hasToUnWatchLater(map, curY, rightNextX)) {
                        unwatchQueue.addLast(Pos(curY, rightNextX, RIGHT))
                        map[curY][rightNextX] = mask
                    }
                    queue.addLast(Pos(curY, rightNextX, RIGHT))
                }
                if (isPossible(map, curY, leftNextX, mask)) {
                    if (hasToUnWatchLater(map, curY, leftNextX)) {
                        unwatchQueue.addLast(Pos(curY, leftNextX, LEFT))
                        map[curY][leftNextX] = mask
                    }
                    queue.addLast(Pos(curY, leftNextX, LEFT))
                }
                if (isPossible(map, upNextY, curX, mask)) {
                    if (hasToUnWatchLater(map, upNextY, curX)) {
                        unwatchQueue.addLast(Pos(upNextY, curX, UP))
                        map[upNextY][curX] = mask
                    }
                    queue.addLast(Pos(upNextY, curX, UP))
                }
                if (isPossible(map, downNextY, curX, mask)) {
                    if (hasToUnWatchLater(map, downNextY, curX)) {
                        unwatchQueue.addLast(Pos(downNextY, curX, DOWN))
                        map[downNextY][curX] = mask
                    }
                    queue.addLast(Pos(downNextY, curX, DOWN))
                }
            }
        }

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            when (cur.dir) {
                RIGHT -> {
                    val nextY = cur.y
                    val nextX = cur.x + 1
                    if (!isPossible(map, nextY, nextX, mask)) continue

                    if (hasToUnWatchLater(map, nextY, nextX)) {
                        unwatchQueue.addLast(Pos(nextY, nextX, RIGHT))
                        map[nextY][nextX] = mask
                    }

                    queue.addLast(Pos(nextY, nextX, cur.dir))
                }

                DOWN -> {
                    val nextY = cur.y + 1
                    val nextX = cur.x
                    if (!isPossible(map, nextY, nextX, mask)) continue

                    if (hasToUnWatchLater(map, nextY, nextX)) {
                        unwatchQueue.addLast(Pos(nextY, nextX, DOWN))
                        map[nextY][nextX] = mask
                    }

                    queue.addLast(Pos(nextY, nextX, cur.dir))
                }

                LEFT -> {
                    val nextY = cur.y
                    val nextX = cur.x - 1
                    if (!isPossible(map, nextY, nextX, mask)) continue

                    if (hasToUnWatchLater(map, nextY, nextX)) {
                        unwatchQueue.addLast(Pos(nextY, nextX, LEFT))
                        map[nextY][nextX] = mask
                    }

                    queue.addLast(Pos(nextY, nextX, cur.dir))
                }

                UP -> {
                    val nextY = cur.y - 1
                    val nextX = cur.x
                    if (!isPossible(map, nextY, nextX, mask)) continue

                    if (hasToUnWatchLater(map, nextY, nextX)) {
                        unwatchQueue.addLast(Pos(nextY, nextX, UP))
                        map[nextY][nextX] = mask
                    }

                    queue.addLast(Pos(nextY, nextX, cur.dir))
                }
            }
        }
    }

    private fun isPossible(map: Array<IntArray>, y: Int, x: Int, mask: Int): Boolean {
        if (y < 0 || y >= N || x < 0 || x >= M) return false
        if (map[y][x] == 6) return false
        return true
    }

    private fun hasToUnWatchLater(map: Array<IntArray>, nextY: Int, nextX: Int): Boolean {
        if (map[nextY][nextX] in (1..5)) return false
        if (map[nextY][nextX] == watching) return false
        return true
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    val map = Array(N) { IntArray(M) { 0 } }

    repeat(N) { row ->
        map[row] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    println(Main4.solution(N, M, map))
}