import java.util.Stack;


public class stockSpan {

// span means no of consectutive days in previous that prices is less than or equal to current price
    public static void giveSpan(int[] prices, int[] span) {
        Stack<Integer> s = new Stack<>();
        int day = 0;
        // it stores the index of day at which the prices is higher that today

        //actual function recurisve;
        calSpan(prices  , span ,s ,  day);

    }

    //helper function
    public static void calSpan(int[] prices , int[] span , Stack<Integer> s , int day){

        if(day == prices.length){
            return ;
        }

        if(day == 0){
            span[day] = 1;
            s.push(day);
            calSpan(prices, span , s, day+1);
            return;
        }

        while (!s.isEmpty() && prices[day] >= prices[s.peek()] ){
                s.pop();
        };

        if(s.isEmpty()){
            span[day] = day+1;

        }
        else{
            span[day] = day - s.peek();
        }
        s.push(day);
        calSpan(prices, span , s, day+1);

    }


    public static void printArr(int[] arr){
        
        for(int i=0 ;i<arr.length ;i++){
            System.out.print(arr[i]+" ->");
        };

    }

    public static void main(String agrs[]) {

        int[] prices = {100, 80, 60, 70, 60, 85, 100};
        int[] span = new int[prices.length];

        giveSpan(prices, span);
        printArr(span);
    }
}
