// 전기 요금
// https://www.acmicpc.net/problem/5710

// 이분 탐색

// 문제 힌트가 조금 명확하게 줬으면 좋았을 것 같다.

// 상근이가 이웃보다 항사 전기를 적게 쓰기 때문에, 상근이는 0 ~ (총 와트 / 2) 사이의 와트를 사용한다.

// 그래서, 이 범위 내에서 상근이가 사용한 사용량을 이분 탐색하면 된다.

// 탐색 종료 조건은, 요금 차이 = |상근이의 전기 요금 - 이웃의 전기 요금| == B일 때,
// 요금 차이 < B 일 때는, 사용량 간의 범위를 늘려줘야 하기 때문에, left = mid + 1
// 요금 차이 > B 일 때는, 사용량 간의 범위를 줄여줘야 하기 때문에, right = mid - 1 이다.

package Study19

import kotlin.math.abs

object Main2 {

    private fun convertPriceToWatt(price: Int): Int {
        return if (price <= 200) price / 2
        else if (price <= 29900) 100 + (price - 200) / 3
        else if (price <= 4979900) 100 + 9900 + (price - 29900) / 5
        else 100 + 9900 + 990000 + (price - 4979900) / 7
    }

    private fun convertWattToPrice(watt: Int): Int {
        return if (watt <= 100) watt * 2
        else if (watt <= 10000) 2 * 100 + 3 * (watt - 100)
        else if (watt <= 1000000) 2 * 100 + 3 * 9900 + 5 * (watt - 10000)
        else 2 * 100 + 3 * 9900 + 5 * 990000 + 7 * (watt - 1000000)
    }

    private fun searchWatt(watt: Int, B: Int) {
        var left = 0
        var right = watt / 2

        while (left <= right) {
            val mid = (left + right) / 2

            val sangPrice = convertWattToPrice(mid)
            val neighPrice = convertWattToPrice(watt - mid)
            val diff = abs(neighPrice - sangPrice)

            if (diff == B) {
                println(sangPrice)
                break
            } else if (diff > B) {
                left = mid + 1
            } else if (diff < B) {
                right = mid - 1
            }
        }
    }

    fun solution(A: Int, B: Int) {
        val watt = convertPriceToWatt(A)

        searchWatt(watt, B)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    while (true) {
        val (A, B) = readLine().split(" ").map { it.toInt() }

        if (A == 0 && B == 0) break

        Main2.solution(A, B)
    }
}