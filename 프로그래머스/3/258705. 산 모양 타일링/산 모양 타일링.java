class Solution {
    public int solution(int n, int[] tops) {
        int edgeRectangle = 1;
        int edgeTriangle;
        if (tops[0] == 1) {
            edgeTriangle = 3;
        } else {
            edgeTriangle = 2;
        }
        for (int i = 1; i<tops.length; i++) {
            int beforeRectangle = edgeRectangle;
            int beforeTriangle = edgeTriangle;
            if (tops[i] == 1) {    
                edgeTriangle = (beforeTriangle * 3)%10007 + (beforeRectangle * 2)%10007;
                edgeRectangle = (beforeTriangle)%10007 + (beforeRectangle)%10007;
            } else {
                edgeTriangle = (beforeTriangle * 2)%10007 + (beforeRectangle)%10007;
                edgeRectangle = (beforeTriangle)%10007 + (beforeRectangle)%10007;
            }
        }
        
        return (edgeTriangle + edgeRectangle)%10007;
    }
}