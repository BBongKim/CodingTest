// DFS, DP 문제
// 나는 DFS로 풀었는데, DP로 풀어도 된다고 한다.
// 하다가 집중이 안되서 코드가 뭔가 지져분해짐 ㅋㅋㅋ

import java.util.*

lateinit var map: Array<IntArray>
lateinit var visited: Array<BooleanArray>
var answer = 0
var N = 0

// 현재 상태
val GARO = 0
val DEAGAK = 1
val SERO = 2

// 이동 방향
val MOVE_RIGHT = 0
val MOVE_DEAGAK = 1
val MOVE_DOWN = 2

// 오른쪽, 대각선, 아래
var x_move = intArrayOf(1, 1, 0)
var y_move = intArrayOf(0, 1, 1)

fun dfs(y: Int, x: Int, direction: Int) {

    if (y == N - 1 && x == N - 1) {
        answer++
        return
    }

    // 오른쪽 이동, 대각선 이동, 아래 이동
    for (i in 0..2) {
        val nextY = y + y_move[i]
        val nextX = x + x_move[i]

        if (nextY >= N || 0 > nextY || nextX >= N || 0 > nextX || visited[nextY][nextX]) continue

        if (isPossible(nextY, nextX, direction, i)) {
            setVisited(y, x, i)
            dfs(nextY, nextX, i)
            eraseVisited(y, x, i)
        }
    }
}

fun isPossible(y: Int, x: Int, curDirect: Int, moveDirect: Int): Boolean {
    when (curDirect) {
        GARO -> {
            return if (moveDirect == MOVE_RIGHT && map[y][x] != 1) true
            else moveDirect == MOVE_DEAGAK && map[y][x] != 1 && map[y][x - 1] != 1 && map[y - 1][x] != 1
        }
        DEAGAK -> {
            return if (moveDirect == MOVE_RIGHT && map[y][x] != 1) true
            else if (moveDirect == MOVE_DEAGAK && map[y][x] != 1 && map[y][x - 1] != 1 && map[y - 1][x] != 1) true
            else moveDirect == MOVE_DOWN && map[y][x] != 1
        }
        SERO -> {
            return if (moveDirect == MOVE_DOWN && map[y][x] != 1) true
            else moveDirect == MOVE_DEAGAK && map[y][x] != 1 && map[y][x - 1] != 1 && map[y - 1][x] != 1
        }
        else -> return false
    }
}

fun setVisited(y: Int, x: Int, direct: Int) {
    when (direct) {
        MOVE_RIGHT -> {
            visited[y + y_move[0]][x + x_move[0]] = true
        }
        MOVE_DEAGAK -> {
            visited[y + y_move[0]][x + x_move[0]] = true
            visited[y + y_move[1]][x + x_move[1]] = true
            visited[y + y_move[2]][x + x_move[2]] = true
        }
        MOVE_DOWN -> {
            visited[y + y_move[2]][x + x_move[2]] = true
        }
    }
}

fun eraseVisited(y: Int, x: Int, direct: Int) {
    when (direct) {
        MOVE_RIGHT -> {
            visited[y + y_move[0]][x + x_move[0]] = false
        }
        MOVE_DEAGAK -> {
            visited[y + y_move[0]][x + x_move[0]] = false
            visited[y + y_move[1]][x + x_move[1]] = false
            visited[y + y_move[2]][x + x_move[2]] = false
        }
        MOVE_DOWN -> {
            visited[y + y_move[2]][x + x_move[2]] = false
        }
    }
}

fun main() {
    N = readln().toInt()
    map = Array(N) { IntArray(N) { 0 } }
    visited = Array(N) { BooleanArray(N) { false } }

    repeat(N) { idx ->
        map[idx] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    visited[0][0] = true
    visited[0][1] = true
    dfs(0, 1, GARO)

    println(answer)
}