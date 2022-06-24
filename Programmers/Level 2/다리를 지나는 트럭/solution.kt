// 큐 문제
// 처음에 트럭을 더 이상 못 넣을때, 꼭 앞에 있는 트럭들이 모두 나가야된다고 착각해버려서 틀림.
// 당연히 1초 후, 앞에 있는 트럭이 나갔을 때 넣을 수 있는 경우가 있음.
// 그래서, 풀이를 보니 그냥 1초마다 큐를 빼는 식으로 구현하는게 편한듯.
// 트럭을 못넣는 경우는 weight 0 값을 큐에 넣어 기다리는 것처럼 만들고, 트럭을 집어 넣을 수 있을 때 까지 계속 poll을 반복함.

import java.util.*

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        var weightSum = 0
        val queue: Queue<Int> = LinkedList<Int>()
        
        repeat(bridge_length) {
            queue.offer(0)
        }
    
        for (truckW in truck_weights) {
            while(true) {
                answer++
                weightSum -= queue.poll()
                
                if (queue.size + 1 <= bridge_length && weightSum + truckW <= weight) {
                    queue.offer(truckW)
                    weightSum += truckW                 
                    break
                }
                
                if (queue.size + 1 > bridge_length || weightSum + truckW > weight) {
                    queue.offer(0)                    
                }                         
            }
        }
        
        answer += bridge_length
        
        return answer
    }
}