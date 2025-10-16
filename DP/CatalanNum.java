// catalan num -  1 1 2 5 14 42

import java.util.*;

public class CatalanNum {
    public static void main(String args[]){
        System.out.println(catalanNo(100));
         System.out.println(CatalanRecursive(10));
    }

    //simple recursion o(2^n)
    public static int CatalanRecursive(int n){
        if(n<=1)
            return 1;
        int temp=0;
        for(int i=0;i<n;i++){
            temp+= CatalanRecursive(i)*CatalanRecursive(n-i-1);
        }
        return temp;
    }

    // dp o(n^2);
    public static int catalanNo(int n){
        int [] num = new int[n+1];
        num[0]=1;
        num[1]=1;

        for(int i=2;i<=n;i++){
            int j=0;
           
            int temp=0;
            while(j<i){
                temp+= num[j]*num[i-j-1];
                // temp+= num[j]*num[k] k=(i-1)--
                j++;
            }
            num[i]=temp;
        }
        return num[n];
    }
}
