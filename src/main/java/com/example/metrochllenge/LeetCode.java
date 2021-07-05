package com.example.metrochllenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeetCode {

//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        List<List<Integer>> superSet = new ArrayList<>();
//        superSet.add(new ArrayList<>());
//
//        for (int i = 0; i < nums.length; i++){
//            List<List<Integer>> restSubSets = restSubsetValues(nums, i);
//            restSubSets.stream()
//                    .filter(e -> !superSet.contains(e))
//                    .map(e -> superSet.add(e))
//                    .collect(Collectors.toList());
//
//
//        }
//        return superSet;
//    }
//
//    private List<List<Integer>> restSubsetValues(int[] nums, int index, int offset) {
//        assert (nums.length > 1);
//        List<List<Integer>> subSets = new ArrayList<>();
//        for (int a = index; a < offset; a++){
//            for (int i = index + offset; i < nums.length; i++) {
//                subSets.add(
//                        Arrays.stream(
//                                Arrays.copyOfRange(nums, index, i+1))
//                                .mapToObj(e -> (Integer) e)
//                                .collect(Collectors.toList()));
//            }
//        }
//        return subSets;
//    }

    /*
    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.
    You can return the answer in any order.

    Example 1:

    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Output: Because nums[0] + nums[1] == 9, we return [0, 1].

    Example 2:

    Input: nums = [3,2,4], target = 6
    Output: [1,2]

     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < nums.length; j++){
                if (nums[i] + nums[j] == target && i != j){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }



}
