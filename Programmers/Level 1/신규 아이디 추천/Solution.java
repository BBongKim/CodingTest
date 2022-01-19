class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        answer = getReco(new_id);
        
        return answer;
    }
    
    public String getReco(String new_id) {
        // 1
        new_id = new_id.toLowerCase();
        
        // 2
        new_id = new_id.replaceAll("[^a-z0-9_.-]", "");
        
        // 3
        new_id = new_id.replaceAll("[.]{2,}", ".");
        
        // 4
        new_id = new_id.replaceAll("^[.]", "");
        new_id = new_id.replaceAll("[.]$", "");
        
        // 5
        if (new_id.length() < 1) new_id = "a" + new_id;
        
        // 6
        if (new_id.length() > 15) {
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("[.]$", "");
        }
        
        // 7
        while (new_id.length() < 3) {
            new_id += new_id.substring(new_id.length()-1);
        }
        
        return new_id;
    }
}