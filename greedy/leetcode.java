
import java.util.*;

class leetcode {

//     1200. Minimum Absolute Difference
// Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
// Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
// a, b are from arr
// a < b
// b - a equals to the minimum absolute difference of any two elements in arr
// Example 1:
// Input: arr = [4,2,1,3]
// Output: [[1,2],[2,3],[3,4]]
// Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        Arrays.sort(arr);

        List<List<Integer>> finalList = new ArrayList<>();

        int diff = Math.abs(arr[1] - arr[0]);

        finalList.add(Arrays.asList(arr[0], arr[1]));

        for (int i = 2; i < arr.length; i++) {
            int newdiff = Math.abs(arr[i] - arr[i - 1]);

            if (newdiff == diff) {
                finalList.add(Arrays.asList(arr[i - 1], arr[i]));
            } else if (newdiff < diff) {
                diff = newdiff;
                finalList.clear();
                finalList.add(Arrays.asList(arr[i - 1], arr[i]));
            }

        }
        return finalList;
    }
}
