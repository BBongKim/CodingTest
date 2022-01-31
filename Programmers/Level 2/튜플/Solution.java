// 다른분 풀이를 보니 이렇게 한줄로 가능하다.
// String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");

import java.util.ArrayList;

class Solution {
    public static int[] solution(String s) {
        int[] answer = {};
        ArrayList<Integer> set = new ArrayList<>();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for(int i = 0; i < 501; i++) {
            list.add(new ArrayList<>());
        }

        int idx = 0;
        boolean start = false;
        String tmp = "";

        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == '{') {
                start = true;
            }
            else if (s.charAt(i) == '}') {
                list.get(idx).add(Integer.parseInt(tmp));
                start = false;
                idx++;
                tmp = "";
            }
            else if (start && s.charAt(i) != ',') {
                tmp += s.charAt(i);
            } else if (start && s.charAt(i) == ','){
                list.get(idx).add(Integer.parseInt(tmp));
                tmp = "";
            }
        }

        list.sort((o1, o2) -> o1.size() < o2.size() ? -1 : 1);

        for(ArrayList<Integer> l : list) {
            if(l.size() == 0) continue;

            for(int i : l) {
                if(set.contains(i)) continue;
                set.add(i);
            }
        }

        answer = new int[set.size()];
        int index = 0;
        for(int i : set) {
            answer[index++] = i;
        }
        return answer;
    }

    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";

        for(int i : solution(s)) {
            System.out.println(i);
        }
    }
}