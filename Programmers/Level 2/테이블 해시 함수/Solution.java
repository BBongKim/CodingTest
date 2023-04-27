// 람다를 이용해서 자바로도 짜봄.

import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        Arrays.sort(data, (int[] d1, int[] d2) -> {
            if (d1[col - 1] != d2[col - 1]) return d1[col - 1] - d2[col - 1];
            else return d2[0] - d1[0];
        });
        
        for (int i = row_begin; i <= row_end; i++) {
            int sum = 0;
           for (int d : data[i - 1]) {
                sum += d % i;
           }
            answer ^= sum;
        }
        
        return answer;
    }
}