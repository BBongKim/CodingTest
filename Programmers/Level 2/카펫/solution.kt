// 완전탐색 문제
// 테두리가 1줄이기 때문에, 노란칸이 1줄일 경우, 2줄일 경우, 3줄일 경우...에 가로 길이를 구할 수 있다.
// 한줄에 전체 노란칸이 1줄일 때는 (yellow / 1), 2줄일 때는 (yellow / 2)개씩... 있다.
// 가로는 (yellowNumInLine+2)이므로, 세로는 (전체 셀 갯수 / 가로)로 구할 수 있다. 
// 문제의 조건은 가로가 세로보다 길거나 같아야 하므로, 이를 만족하는 가로 세로를 구할 때 까지 계속 반복하면 된다.
// 다만, (yellow / 몇 줄)과 (전체 셀 갯수 / 가로)가 소수점이 나올 수 있기 때문에 소수점이 나오는 경우는 배제시킨다.
// Ex.) [brown = 16, yellow = 9] , [brown = 24, yellow = 24]

class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = intArrayOf(0, 0)
        val totalCell = brown + yellow
        
        var yellowNumInLine = yellow
        var divider = 2
        
        while (yellowNumInLine > 0) {
            if (yellowNumInLine * (divider - 1) == yellow) {
                val col = yellowLine + 2
                val row = totalCell / col

                if (col >= row && (col * row) == totalCell) {
                    answer[0] = col
                    answer[1] = row
                    break
                }
            }
                
            yellowNumInLine = yellow
            yellowNumInLine /= divider++            
        }
        
        return answer
    }
}