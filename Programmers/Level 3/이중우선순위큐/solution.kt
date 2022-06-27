// Heap 문제
// 단순하게 maxHeap과 minHeap을 동시에 운용하면 된다.
// 난이도가 레벨3은 아닌 것 같다.

import java.util.*

class Solution {
    fun solution(operations: Array<String>): IntArray {
        var answer = intArrayOf(0 ,0)
        val maxHeap = PriorityQueue<Int>(reverseOrder())
        val minHeap = PriorityQueue<Int>()
        
        for (operation in operations) {
            val(cmd, numString) = operation.split(" ")
            val num = numString.toInt()
            when(cmd) {
                "I" -> {
                    maxHeap.offer(num)
                    minHeap.offer(num)
                }
                
                "D" -> {
                    if (maxHeap.isEmpty() || minHeap.isEmpty()) continue
                    if (num == 1) {
                        val max = maxHeap.poll()
                        minHeap.remove(max)
                    } else {
                        val min = minHeap.poll()
                        maxHeap.remove(min)
                    }
                }
            }
        }
        
        if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            answer[0] = maxHeap.poll()
            answer[1] = minHeap.poll()
        }
        
        return answer
    }
}