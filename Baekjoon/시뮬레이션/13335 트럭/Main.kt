// 13335 - 트럭

// 시뮬레이션

// 트럭을 다리위에 올릴 수 있을 때마다, 올리면 된다.
// Unit time이 지날 때마다, 큐의 맨 앞의 값을 제거한다.
// 그리고, 트럭을 올릴 수 있으면 큐에 트럭을 넣는다.
// 올릴 수 없다면, 0을 넣는다.

fun solution(N: Int, W: Int, L: Int, weights: List<Int>): Int {
    val bridge = ArrayDeque<Int>()
    val queue = ArrayDeque(weights)
    var time = 1

    var weightSum = 0

    val firstTruck = queue.removeFirst()
    bridge.addFirst(firstTruck)
    weightSum += firstTruck

    var out = 0

    while (out != N) {
        if (bridge.size == W) {
            val weight = bridge.removeFirst()

            if (weight > 0) {
                weightSum -= weight
                out++
            }
        }

        if (queue.isNotEmpty() && weightSum + queue.first() <= L) {
            val truck = queue.removeFirst()
            weightSum += truck
            bridge.addLast(truck)
        } else {
            bridge.addLast(0)
        }

        time++
    }

    return time
}

fun main() {
    val (n, w, l) = readln().split(" ").map { it.toInt() }
    val weights = readln().split(" ").map { it.toInt() }

    println(solution(n, w, l, weights))
}