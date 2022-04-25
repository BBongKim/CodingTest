// 백트래킹 문제

// 2차원 배열을 사용하지 않고, 1차원 배열을 통해 표현하는 것이 중요하다.
// 대각선 구분은, (x, y) (a, b) 가 대각선 상에 있을 경우, abs(x-a) == abs(y-b)임을 이용한다.

import java.io.*;
import kotlin.math.abs

var N = 0
var answer = 0
lateinit var col: Array<Int>

fun isPossible(r: Int, depth: Int): Boolean {
    for (i in 0 until depth) {
        if (col[i] == r || abs(i - depth) == abs(col[i] - r)) return false
    }
    return true
}

fun queen(depth: Int) {
    if (depth == N) answer++

    for (r in 0 until N) {
        if (isPossible(r, depth)) {
            col[depth] = r
            queen(depth + 1)
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    N = br.readLine().toInt()
    col = Array(N) { 0 }
    queen(0)
    print(answer)
}