// maximum rectangle area in the histogram
 
import java.util.Stack;

public class maximumRectangle{

// stack O(n)
    public static int maxRect_Opti(int[] heights){

        // find left , right boundary (index) exclusive in width 
        //width = right-left -1;

        // left = next left smaller value index
        // right = next right smaller val index
        
        int[] right = new int[heights.length];
        int[] left = new int[heights.length];

        nextSmallerLeft(heights , left);   // gives left boundary
        nextSmallerRight(heights , right); // gives right boundaries

        int leftB, rightB, height , width , currArea , maxArea=0;

        for(int i =0 ; i<heights.length ;i++){
            leftB = left[i];
            rightB = right[i];
            height = heights[i];
            width = rightB-leftB-1;
            currArea = width*height ;
            maxArea  = Math.max(currArea , maxArea);
        }

        return maxArea;
    }

// helper functions - left smaller next
    public static void nextSmallerLeft(int[] heights , int[] left){
        Stack<Integer> s = new Stack<>();
        int idx = 0;
        left[idx] =-1;
        s.push(idx);

        for(idx =1 ; idx < heights.length ; idx++){
            int currHeight = heights[idx];
            while(!s.isEmpty() && currHeight <= heights[s.peek()]){
                s.pop();
            }
            if(s.isEmpty())    // stack is empty-no smaller val
            left[idx] =-1;
            else       // got smaller left val index
            left[idx] = s.peek();

            s.push(idx);
        }
    }

// helper functions - right smaller next
    public static void nextSmallerRight(int[] heights , int[] right){
        Stack<Integer> s = new Stack<>();
        int idx = heights.length-1;

        right[idx] = heights.length;  // small change
        s.push(idx);

        while(idx >= 0){

            int currHeight = heights[idx];

            while(!s.isEmpty() && currHeight <= heights[s.peek()] ){
                s.pop();
            }

            if(s.isEmpty())  // no smaller in right 
                // right[idx] = -1;
                right[idx] = heights.length;  // change
            else
                right[idx] = s.peek();

            s.push(idx);

            idx--;
        }

    }


// brute force O(n^2);
    public static int maxRect_Brute(int[] heights){

        int width , rec_height ;
        int area , maxArea=0;

        for (int i =0 ; i<heights.length ;i++){
            
            int left =i, right = i ; // boundaries 

            while( left >=0 && heights[left] >=heights[i] ){
                left--;
            }
            while(right < heights.length && heights[right] >= heights[i]){
                right++;
            }

            width = right -left -1;
            rec_height = heights[i];
            area = width* rec_height;
            maxArea = Math.max(area , maxArea);

        }

        return maxArea;
    }


    public static void main(String args[]){

        int[]  heights = {2 , 1 , 5 , 6 , 2 , 3};

        System.out.println("maxArea by brute = "+maxRect_Brute(heights));
        System.out.println("maxArea by optimized = "+maxRect_Opti(heights));
    }
}