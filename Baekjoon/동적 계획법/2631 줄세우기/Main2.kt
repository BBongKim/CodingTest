// 줄세우기
// https://www.acmicpc.net/problem/2631

// DP

// Longest Increasing Subsequence 구하는 문제이다.

// O(Nlog(N)) 이분탐색을 이용한 풀이

package Study09

object Main6 {
    fun solution(N: Int, arr: IntArray): Int {
        val lis = IntArray(N) { 0 }

        var lisIdx = 0
        lis[0] = arr[0]

        for (arrIdx in 1 until N) {
            if (lis[lisIdx] < arr[arrIdx]) {
                lis[++lisIdx] = arr[arrIdx]
                continue
            }

            val insertedIdx = search(lis, lisIdx, arr[arrIdx])
            lis[insertedIdx] = arr[arrIdx]
        }

        return N - (lisIdx + 1)
    }

    private fun search(lis: IntArray, end: Int, target: Int): Int {
        var left = 0
        var right = end

        while (left < right) {
            val mid = (left + right) / 2

            if (lis[mid] < target) left = mid + 1
            else right = mid
        }

        return right
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val list = IntArray(N) { readLine().toInt() }

    println(Main6.solution(N, list))
}