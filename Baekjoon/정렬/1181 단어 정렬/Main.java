// 정렬 연습

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<String> list = new ArrayList<>();
        TreeSet<String> set = new TreeSet<>();

        for (int i = 0; i < N; i++) set.add(br.readLine());

        list.addAll(set);

        Collections.sort(list, (s1, s2) -> {
            if (s1.length() > s2.length()) return 1;
            else if (s1.length() < s2.length()) return -1;
            else {
                int idx = 0;
                while (idx < s1.length() && s1.charAt(idx) == s2.charAt(idx)) idx++;

                if (idx >= s1.length()) return 0;

                if (s1.charAt(idx) > s2.charAt(idx)) return 1;
                else return -1;
            }
        });

        for (String s : list) {
            System.out.println(s);
        }
    }
}
