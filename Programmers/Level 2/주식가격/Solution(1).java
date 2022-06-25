// 완전 탐색 풀이, N이 1만이라 가능하다.

import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] prices) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        
        for (int i = 0; i < prices.length; i++) {
            int cnt = 1;
            boolean declined = false;
            
            for (int k = i + 1; k < prices.length; k++) {
                if (prices[k] < prices[i]) {
                    answer.add(cnt);
                    declined = true;
                    break;
                }
                cnt++;
            }
            
            if (!declined) answer.add(cnt - 1);
        }
             
        return answer;
    }
}