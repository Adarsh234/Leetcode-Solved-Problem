class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for(char c : s.toCharArray()){
            freq[c-'a']++;
        }
        StringBuilder first = new StringBuilder();
        String middle = "";
        for(int i = 0; i < 26; i++){
            if(freq[i] % 2 != 0){
                middle = String.valueOf((char)(i + 'a'));
            }
            int half = freq[i] / 2;
            char c = (char)(i+'a');
            for(int j = 0; j < half; j++){
                first.append(c);
            }
        }
        StringBuilder result = new StringBuilder(first);
        result.append(middle);
        result.append(first.reverse());
        return result.toString();
    }
}