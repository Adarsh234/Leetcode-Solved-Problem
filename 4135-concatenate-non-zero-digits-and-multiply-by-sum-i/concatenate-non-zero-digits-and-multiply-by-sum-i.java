class Solution {
    public long sumAndMultiply(int n) {
        int placeVal = 1; 
        int x = 0;
        int sum = 0;
        while(n > 0){
            int digit = n % 10;
            if(digit != 0){
                sum += digit;
                x += digit * placeVal;
                placeVal *= 10;
            }
            n /= 10;
        }
        return 1L * sum * x;
    }
}