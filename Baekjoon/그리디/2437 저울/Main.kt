// Greedy 문제
// 문제는 간단한데 아이디어가 어려워서 풀이 봄

// 오름차순 정렬 후, 앞으로 올릴 추의 무게가 누적 값 + 1 보다 크면 불가능한 값이다.
// 간단히 생각해보면 된다.
// 1 1 2 3이라고 생각하면,
// sum = 0 에서 1은 가능 sum = 1이다.
// sum = 1, 1은 가능 sum = 2
// sum = 2 2는 가능 sum = 4
// sum = 4 3은 가능 sum = 7

// sum = 4일 때, 6 넣는다고 해보면 1 2 3 4 6만 만들 수 있고, 5를 못 만듦

import java.io.*;
import java.util.*;

var N = 0

fun solution(list: Array<Long>): Long {
    var sum = 0L

    list.sort()

    for (i in list) {
        if (sum + 1 < i) return sum + 1

        sum += i
    }

    return sum + 1
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    N = br.readLine().toInt()

    val list = Array(N) { 0L }

    val st = StringTokenizer(br.readLine(), " ")

    for (i in 0 until N) {
        list[i] = st.nextToken().toLong()
    }

    print(solution(list))
}