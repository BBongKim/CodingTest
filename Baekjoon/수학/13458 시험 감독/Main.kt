// 시험감독
// https://www.acmicpc.net/problem/13458

// 수학

// 문제는 그냥 나누기 문제다.
// 하지만, 정답이 Long 범위임을 조심.

// 정답의 범위가 정해져 있지 않다면, 항상 범위를 생각하자!

package Study12

object Main {
    fun solution(N: Int, A: List<Int>, B: Int, C: Int): Long {
        var answer = 0L

        for (room in A) {
            var temp = room

            temp -= B
            answer++

            if (temp <= 0) continue
            if (temp <= C) answer++
            else answer += if (temp % C == 0) temp / C else temp / C + 1
        }

        return answer
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val A = readLine().split(" ").map { it.toInt() }
    val (B, C) = readLine().split(" ").map { it.toInt() }

    println(Main.solution(N, A, B, C))
}