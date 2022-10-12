// 계란으로 계란치기
// https://www.acmicpc.net/problem/16987
// 완탐, 백트래킹

// 오랜만에 하니까 재귀 종료 조건에서 머리가 안돌아감...ㅠㅠ

// 기본 아이디어는 한대 치고, 다음 index로 넘어 갔다가 다시 돌아오는 백트랙킹이다.

data class Egg(var durability: Int, val weight: Int)

var N = 0
var max = 0
var cnt = 0

fun hit(idx: Int, list: ArrayList<Egg>) {
    if (idx == list.size) {
        max = maxOf(max, cnt)
        return
    }

    val egg1 = list[idx]

    // 현재 달걀이 깨진 경우 OR 1개 빼고 다 깨진 경우
    if (isBroken(egg1) || cnt == N - 1) {
        hit(idx + 1, list)
        return
    }

    for (egg2 in list) {
        if (isBroken(egg2)) continue
        if (egg1 == egg2) continue

        egg1.durability = egg1.durability - egg2.weight
        egg2.durability = egg2.durability - egg1.weight
        if (isBroken(egg1)) cnt++
        if (isBroken(egg2)) cnt++

        hit(idx + 1, list)

        if (isBroken(egg1)) cnt--
        if (isBroken(egg2)) cnt--
        egg1.durability = egg1.durability + egg2.weight
        egg2.durability = egg2.durability + egg1.weight
    }
}

fun isBroken(egg: Egg): Boolean {
    return egg.durability <= 0
}

fun main() {
    N = readln().toInt()
    val list = ArrayList<Egg>()

    repeat(N) {
        val (d, w) = readln().split(" ").map { it.toInt() }
        list.add(Egg(d, w))
    }

    hit(0, list)
    println(max)
}