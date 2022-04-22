// DP 문제
// 코틀린 연습겸 품

import java.io.*;
import java.util.*;

var N = 0
lateinit var map: Array<Array<Int>>
lateinit var sum: Array<Array<Int>>

fun solution(): Int {
    var max = map[0][0]
    sum[0][0] = map[0][0]

    for (i in 0 until N - 1) {
        for (k in 0 until i + 1) {
            for (q in 0..1) {
                var tmp = sum[i][k] + map[i + 1][k + q]
                sum[i + 1][k + q] = tmp.coerceAtLeast(sum[i + 1][k + q])
                max = max.coerceAtLeast(sum[i + 1][k + q])
            }
        }
    }

   return max
}

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()

    map = Array(N) { i -> Array(i + 1) { 0 } }
    sum = Array(N) { i -> Array(i + 1) { 0 } }

    for (i in 0 until N) {
        val st = StringTokenizer(br.readLine(), " ")

        for (k in 0 until i + 1) {
            map[i][k] = st.nextToken().toInt()
        }
    }

    print(solution())
}