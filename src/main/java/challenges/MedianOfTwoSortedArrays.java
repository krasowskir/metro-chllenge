package challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Example 3:
Input: nums1 = [0,0], nums2 = [0,0]
Output: 0.00000

Example 4:
Input: nums1 = [], nums2 = [1]
Output: 1.00000

Example 5:
Input: nums1 = [2], nums2 = []
Output: 2.00000


Constraints:

    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -106 <= nums1[i], nums2[i] <= 106


 */
public class MedianOfTwoSortedArrays {


    public void findMedian(int[] arr1, int[] arr2){

        int arr1Length = arr1.length;
        int arr2Length = arr2.length;

        int[] newArr = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).toArray();

        Stream.of(arr1, arr2)
                .flatMap(Stream::of)
                .reduce(new int[arr1Length + arr2Length], (a,b) -> b);
        System.out.println("result: " + Arrays.toString(newArr));
    }
}
