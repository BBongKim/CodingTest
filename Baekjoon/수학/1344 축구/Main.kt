// 축구
// https://www.acmicpc.net/problem/1344

// 수학

// 한 팀의 득점 확률 = (골 넣을 확률) * (골 안넣을 확률) * (간격의 경우의 수)
// 한 경기의 득점 확률 = (A 팀 득점 확률) * (B 팀 득점 확률)

// 1. A 소수, B 소수x
// 2. A 소수, B 소수
// 3. A 소수x, B 소수

// 위 3가지 경우의 확률을 모두 더하면된다.
// 주의할 점은, 소수가 아닌 점수에서 0 점도 포함된다는 것이다.

package Study06

import kotlin.math.pow

val primes = listOf(2, 3, 5, 7, 11, 13, 17)
val noPrimes = listOf(0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18)

object Main2 {
    fun solution(a: Double, b: Double): Double {
        var sum = 0.0

        // p1만 소수
        for (p1 in primes) {
            for (p2 in noPrimes) {
                val chance1 = a.pow(p1) * (1 - a).pow(18 - p1) * comb(18, p1)
                val chance2 = b.pow(p2) * (1 - b).pow(18 - p2) * comb(18, p2)
                sum += chance1 * chance2
            }
        }

        // p1 p2 모두 소수
        for (p1 in primes) {
            for (p2 in primes) {
                val chance1 = a.pow(p1) * (1 - a).pow(18 - p1) * comb(18, p1)
                val chance2 = b.pow(p2) * (1 - b).pow(18 - p2) * comb(18, p2)
                sum += chance1 * chance2
            }
        }

        // p2만 소수
        for (p2 in primes) {
            for (p1 in noPrimes) {
                val chance1 = a.pow(p1) * (1 - a).pow(18 - p1) * comb(18, p1)
                val chance2 = b.pow(p2) * (1 - b).pow(18 - p2) * comb(18, p2)
                sum += chance1 * chance2
            }
        }

        return sum
    }

    private fun comb(n: Int, r: Int): Int {
        return (fact(n) / (fact(n - r) * fact(r))).toInt()
    }

    private fun fact(n: Int): Long {
        var acc = 1L
        for (i in 1..n) {
            acc *= i
        }
        return acc
    }
}

fun main() {
    val a = readln().toInt()
    val b = readln().toInt()

    println(Main2.solution(a * 0.01, b * 0.01))
}