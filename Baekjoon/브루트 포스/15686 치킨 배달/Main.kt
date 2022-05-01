// 브루트포스 문제
// 백트래킹을 이용하여 가능한 가게의 조합을 구한 후, 도시의 치킨 거리를 구한다.
// 조심해야 할 점은, 앞서 구한 치킨 거리의 최솟값보다 현재 구하고 있는 치킨 거리가 커지면 그냥 다 구하지 않고 넘어간다.

import java.util.*
import kotlin.math.*

lateinit var map: Array<Array<Int>>
lateinit var visited: Array<Boolean>
lateinit var TmpStores: ArrayList<Pos>
var houses = ArrayList<Pos>()
var stores = ArrayList<Pos>()
var min = Int.MAX_VALUE

data class Pos(var y: Int, var x: Int)

fun solution() {
    var sum = 0
    for (house in houses) {
        var tmp = Int.MAX_VALUE
        for (store in TmpStores) {
            val dist = abs(house.x - store.x) + abs(house.y - store.y)
            tmp = minOf(tmp, dist)
        }
        sum += tmp

        // 최적화
        if (sum > min) break
    }
    min = minOf(sum, min)
}

fun combination(N: Int, M: Int, start: Int) {
    if (M == 0) {
        TmpStores = ArrayList<Pos>()
        for (i in 0 until N) {
            if (visited[i]) {
                TmpStores.add(stores[i])
            }
        }
        solution()
        return
    }

    for (i in start until N) {
        if (!visited[i]) {
            visited[i] = true
            combination(N, M - 1, i + 1)
            visited[i] = false
        }
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    map = Array(N) { Array(N) { 0 } }
    for (i in 0 until N) {
        val st = StringTokenizer(readln(), " ")
        for (k in 0 until N) {
            map[i][k] = st.nextToken().toInt()

            if (map[i][k] == 1) houses.add(Pos(i, k))
            else if (map[i][k] == 2) stores.add(Pos(i, k))
        }
    }

    visited = Array(stores.size) { false }

    combination(stores.size, M, 0)

    println(min)
}