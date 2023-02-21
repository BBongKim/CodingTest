// 과자 나눠주기
// https://www.acmicpc.net/problem/16401

// 문제에 정렬되어 있다는 보장이 없다.
// 정렬 안하면 틀린당..

// 질문하기에 있는
// 3 3
// 1 1 1 케이스는 어떻게 된거지 ㅋㅋ

// 23.2.21 업데이트
// 위 케이스를 통과하지 못하기 때문에
// start의 범위를 가능한 길이인 1부터 시작해주어야한다.

fun solution(m: Int, n: Int, list: List<Int>): Int {
    var start = 1
    var end = list.max()
    var mid: Int
    var max = 0

    while (start <= end) {
        mid = (start + end) / 2

        val cnt = countStick(mid, list)

        if (cnt >= m) {
            max = mid
            start = mid + 1
        } else {
            end = mid - 1
        }
    }

    return max
}

fun countStick(target: Int, list: List<Int>): Int {
    var cnt = 0

    for (stick in list) {
        if (target != 0) {
            cnt += (stick / target)
        }
    }
    return cnt
}

fun main() {
    val (M, N) = readln().split(" ").map { it.toInt() }
    val list = readln().split(" ").map { it.toInt() }

    val ans = solution(M, N, list)

    println(ans)
}
