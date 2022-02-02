// 생각보다 Comparator 구현이 까다로웠다.
// 0, long 고려 필요
// 다른분 접근법 참고해서 풀었음

import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public static String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();

        ArrayList<String> list = new ArrayList<>();

        for (int i : numbers) {
            list.add(Integer.toString(i));
        }

        if (Collections.frequency(list, "0") == list.size()) return "0";

        list.sort((o1, o2) -> {
            long o1o2 = Long.parseLong(o1 + o2);
            long o2o1 = Long.parseLong(o2 + o1);

            return Long.compare(o2o1, o1o2);
        });

        for(String s : list) {
            answer.append(s);
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        int[] numbers = {0, 0};

        System.out.println(solution(numbers));
    }
}