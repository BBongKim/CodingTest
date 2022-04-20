// LIS 문제
// lowerBound를 이용해서, dp 배열의 길이를 리턴하면 된다.

import java.io.*;
import java.util.*;

class Main {
}

var N = 0
var answer = 0

fun solution(list: Array<Int>) {
    var dp = Array(N) { 0 }

    var last = 0

    for (i in list.indices) {
        if (i == 0) {
            dp[last++] = list[0]
            continue
        }

        if (dp[last - 1] > list[i]) {
            dp[lowerBound(dp, 0, last - 1, list[i])] = list[i]
        } else if (dp[last - 1] < list[i]) {
            dp[last++] = list[i]
        }
    }
    answer = last
}

fun lowerBound(dp: Array<Int>, s: Int, e: Int, n: Int): Int {
    var start = s
    var end = e
    var mid = 0

    while (start < end) {
        mid = (start + end) / 2

        if (dp[mid] < n) {
            start = mid + 1
        } else {
            end = mid
        }
    }

    return end
}


fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))

    N = br.readLine().toInt()
    var list = Array(N) { 0 }

    var st = StringTokenizer(br.readLine(), " ")

    for (i in 0 until N) {
        list[i] = st.nextToken().toInt()
    }

    solution(list)

    print(answer)
}