package com.andigeeky.finnhub.data.temp;

import java.util.List;

public class SearchingAlgorithms {
    public static int binarySearchRecursive(List<Integer> arr, int target, int startIndex) {
        System.out.println("List: " + arr + " Target: " + target + " start index: " + startIndex);
        if (arr.size() < 1) return -1;
        int centerIndex = (int) Math.ceil(arr.size() / 2);
        int element = arr.get(centerIndex);
        System.out.println("Element: " + element);
        if (target == element) return startIndex + centerIndex;
        else if (target < element) return binarySearchRecursive(arr.subList(0, centerIndex), target, 0);
        else
            return binarySearchRecursive(arr.subList(centerIndex + 1, arr.size()), target, startIndex + centerIndex + 1);
    }

    public static int binarySearchImperative(List<Integer> arr, int target) {
        System.out.println("List: " + arr + " Target: " + target);
        if (arr.size() < 1) return -1;
        int start = 0;
        int end = arr.size()-1;
        while (start<=end){
            int center = start + (end-start)/2;
            int element = arr.get(center);
            if (target == element) return center;
            else if (target < element){
                end = center -1;
            }else {
                start = center + 1;
            }
        }
        return -1;
    }

    /**
     * An array of boolean values is divided into two sections;
     * the left section consists of all false and the right section consists of all true.
     * Find the boundary of the right section, i.e. the index of the first true element. If there is no true element, return -1.
     *
     * Input: arr = [false, false, true, true, true]
     *
     * Output: 2
     */
    public static int findBoundaryOfRightSection(List<Boolean> arr) {
        int end = arr.size()-1;
        int start = 0;
        if (end <1) return -1;
        for (start = 0; start<end; start++){
            if (arr.get(start)) return start;
        }
        return -1;
    }

    public static int findBoundaryOfRightSectionV2(List<Boolean> arr) {
        int end = arr.size() - 1;
        int start = 0;
        if (arr.size() < 1) return -1;
        if (end == 0){
            if (arr.get(end)) return end;
            else return -1;
        }
        while (start <= end) {
            if (start == end){
                if (arr.get(start)) return start;
                else return -1;
            }
            int center = start + (end - start) / 2;
            if (!arr.get(center)) {
                start = center + 1;
            } else {
                end = center;
            }
        }
        return -1;
    }

    /**
     * Given an array of integers sorted in increasing order and a target,
     * find the index of the first element in the array that is larger than or equal to the target.
     * Assume that it is guaranteed to find a satisfying number.
     *
     * Input:
     *
     * arr = [1, 3, 3, 5, 8, 8, 10]
     * target = 2
     * Output: 1
     *
     */
    public static int findElementNotSmallerThanTarget(List<Integer> arr, int target) {
        int start = 0;
        int end = arr.size() -1;
        if (end <=1) return end;
        while (start<=end){
            if (start==end) return start;
            int center = start + (end-start)/2;
            if (arr.get(center) >= target){
                end = center;
            } else {
              start = center + 1;
            }
        }
        return -1;
    }

    public static int findElementNotSmallerThanTargetV2(List<Integer> arr, int target) {
        int start = 0;
        int end = arr.size() -1;
        if (end <=1) return end;
        int boundaryIndex = -1;
        while (start<=end){
            int center = start + (end-start)/2;
            if (arr.get(center) >= target){
                end = center -1;
                boundaryIndex = center;
            } else {
                start = center + 1;
            }
        }
        return boundaryIndex;
    }

    /**
     * Given a sorted array of integers and a target integer,
     * find the first occurrence of the target and return its index. Return -1 if the target is not in the array.
     *
     * Input:
     *
     * arr = [1, 3, 3, 3, 3, 6, 10, 10, 10, 100]
     * target = 3
     * Output: 1
     *
     */
    public static int findFirstOccurrenceWithDuplicates(List<Integer> arr, int target) {
        int start = 0;
        int end = arr.size() -1;
        int boundaryIndex = -1;
        if (arr.size() <1) return boundaryIndex;
        if (start == end && arr.get(start) == target ) return start;
        while (start<end){
            int center = start + (end-start)/2;
            if (arr.get(center) >= target){
                end = center -1;
                boundaryIndex = center;
            } else {
                start = center + 1;
            }
        }
        return boundaryIndex;
    }
}
