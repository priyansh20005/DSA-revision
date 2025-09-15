/// ordered is maintained int linkedHashSet
import java.util.*;

public class LinkedHashSetBasic{

    /// count distinct elements in an array
    public static int distinctElements(int nums[]){
        HashSet<Integer> set = new HashSet<>();
        for(int i=0 ;i<nums.length ;i++){
            set.add(nums[i]);
        }

        return set.size();
    }

    // union and intersection of 2 array
    public static ArrayList<Integer> getUnion(int arr1[] , int arr2[]){

        ArrayList<Integer> ans = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<arr1.length;i++){
            set.add(arr1[i]);
        }
        for(int i=0; i<arr2.length ;i++){
            set.add(arr2[i]);
        }

        Iterator i = set.iterator();
        while(i.hasNext()){
            ans.add((int)i.next());
        }

        return ans;
    }

    public static List<Integer> getInterSection(int[]arr1, int[]arr2){
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for( int num : arr1){
            set.add(num);
        }
        for(int num : arr2){
            if(set.contains(num)){ 
                ans.add(num);
                set.remove(num);// for avoiding dupicacy adding
            }
            
        }

        return ans;

        // Set<Integer> set1 = new HashSet<>();
        // for (int num : arr1) set1.add(num);

        // Set<Integer> set2 = new HashSet<>();
        // for (int num : arr2) set2.add(num);

        // set1.retainAll(set2); // keeps only common elements
        // return new ArrayList<>(set1);
    }

    public static void main(String args[]){
        int nums[]={1, 2, 3,4,6,2, 34,4, 2};
        System.out.println(distinctElements(nums)+" ");
    }
}