class Solution {
    public boolean isPalindrome(String s) {
        char[] charArr = s.toCharArray();
        // 5 -> 2
        StringBuilder sb = new StringBuilder(); 
        for ( int i = 0; i < charArr.length; i++){
            if ( Character.isLetterOrDigit(charArr[i])){
                sb.append(Character.toLowerCase(charArr[i]));
            }
        }

        char[] modifyArr = sb.toString().toCharArray();
        for ( int i = 0; i < modifyArr.length/2; i++){
            if ( modifyArr[i] != modifyArr[modifyArr.length - i - 1]){
                return false;
            }
        }

        return true;
    }
}
