package com.andigeeky.finnhub.data.temp;

import java.util.ArrayList;
import java.util.List;

class SortAlgorithm {
    public static List<Integer> sortUsingBubbleSortRecursiveAlgorithm(List<Integer> unsortedList) {
        for (int i = 0; i < unsortedList.size() - 1; i++) {
            if (unsortedList.get(i) > unsortedList.get(i + 1)) {
                int temp = unsortedList.get(i);
                unsortedList.set(i, unsortedList.get(i + 1));
                unsortedList.set(i + 1, temp);
            }
        }
        System.out.println("List : " + unsortedList);
        if (unsortedList.size() <= 1) return unsortedList;
        else
            sortUsingBubbleSortRecursiveAlgorithm(unsortedList.subList(0, unsortedList.size() - 1));
        return unsortedList;
    }

    public static List<Integer> sortUsingBubbleSortAlgorithm(List<Integer> unsortedList) {
        for (int i = 0; i < unsortedList.size() - 1; i++) {
            for (int j = 0; j < unsortedList.size() - i - 1; j++) {
                System.out.println("i: " + i + " arr[" + j + "]=" + unsortedList.get(j) + " arr[" + (j + 1) + "]:" + unsortedList.get(j + 1));
                if (unsortedList.get(j) > unsortedList.get(j + 1)) {
                    int temp = unsortedList.get(j);
                    unsortedList.set(j, unsortedList.get(j + 1));
                    unsortedList.set(j + 1, temp);
                }
                System.out.println(unsortedList);
            }
        }
        return unsortedList;
    }

    public static List<Integer> sortDescending(List<Integer> unsortedList) {
        for (int i = 0; i < unsortedList.size() - 1; i++) {
            if (unsortedList.get(i) < unsortedList.get(i + 1)) {
                int temp = unsortedList.get(i);
                unsortedList.set(i, unsortedList.get(i + 1));
                unsortedList.set(i + 1, temp);
            }
        }
        System.out.println("List : " + unsortedList);
        if (unsortedList.size() <= 1) return unsortedList;
        else sortDescending(unsortedList.subList(0, unsortedList.size() - 1));
        return unsortedList;
    }

    public static List<Integer> sortUsingInsertionAlgorithm(List<Integer> unsortedList) {
        for (int i = 0; i < unsortedList.size() - 1; i++) {
            int j = i;
            while (j > 0 && unsortedList.get(j) < unsortedList.get(j - 1)) {
                int temp = unsortedList.get(j);
                unsortedList.set(j, unsortedList.get(j - 1));
                unsortedList.set(j - 1, temp);
                j--;
            }
        }
        return unsortedList;
    }

    public static List<Integer> sortUsingSelectionAlgorithm(List<Integer> unsortedList) {
        System.out.println(unsortedList);
        if (unsortedList.size() <= 1) return unsortedList;
        int minIndex = -1;
        for (int i = 0; i < unsortedList.size(); i++) {
            if (minIndex == -1) {
                if (unsortedList.get(i) < unsortedList.get(i + 1)) {
                    minIndex = i;
                } else {
                    minIndex = i + 1;
                }
            } else if (unsortedList.get(i) < unsortedList.get(minIndex)) {
                minIndex = i;
            }
        }
        int temp = unsortedList.get(0);
        unsortedList.set(0, unsortedList.get(minIndex));
        unsortedList.set(minIndex, temp);
        sortUsingSelectionAlgorithm(unsortedList.subList(1, unsortedList.size()));
        return unsortedList;
    }

    @SuppressWarnings("NewApi")
    public static List<Integer> sortUsingMergeSortAlgorithm(List<Integer> unsortedList) {
        System.out.println(unsortedList);
        if (unsortedList.size() <= 1) return unsortedList;
        int center = (int) Math.ceil(unsortedList.size() / 2);
        List<Integer> left = sortUsingMergeSortAlgorithm(unsortedList.subList(0, center));
        List<Integer> right = sortUsingMergeSortAlgorithm(unsortedList.subList(center, unsortedList.size()));
        return merge(left, right);
    }

    public static List<Integer> merge(List<Integer> left, List<Integer> right) {
        System.out.println("Merge left: " + left + " right: " + right);
        List<Integer> merge = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < left.size() || j < right.size()) {
            if (i == left.size()) {
                merge.add(right.get(j));
                j++;
            } else if (j == right.size()) {
                merge.add(left.get(i));
                i++;
            } else if (left.get(i) < right.get(j)) {
                merge.add(left.get(i));
                i++;
            } else {
                merge.add(right.get(j));
                j++;
            }
        }
        System.out.println("Merged: " + merge);
        return merge;
    }

    @SuppressWarnings("NewApi")
    public static List<Integer> sortUsingQuickSortAlgorithm(List<Integer> unsortedList) {
        System.out.println(unsortedList);
        int end = unsortedList.size() - 1;
        if (end < 1) return unsortedList;

        if (unsortedList.size() == 2) {
            if (unsortedList.get(0) >= unsortedList.get(1)) {
                int temp1 = unsortedList.get(0);
                unsortedList.set(0, unsortedList.get(1));
                unsortedList.set(1, temp1);
            }
            return unsortedList;
        }

        //Decide pivot
        int pivotIndex = end;

        //Sort it
        int startIndex = 0;
        int endIndex = pivotIndex - 1;
        boolean swapped = false;
        while (startIndex < endIndex) {
            if (unsortedList.get(startIndex) >= unsortedList.get(pivotIndex)) {
                swapped = true;
                if (unsortedList.get(endIndex) < unsortedList.get(pivotIndex)) {
                    int temp1 = unsortedList.get(startIndex);
                    unsortedList.set(startIndex, unsortedList.get(endIndex));
                    unsortedList.set(endIndex, temp1);
                    startIndex++;
                } else {
                    endIndex--;
                }
            } else {
                startIndex++;
            }
            System.out.println("Steps : " + unsortedList);
        }

        //Move pivot at correct position
        if (swapped) {
            int temp2 = unsortedList.get(startIndex);
            unsortedList.set(startIndex, unsortedList.get(pivotIndex));
            unsortedList.set(pivotIndex, temp2);
        }

        System.out.println("Moved Pivot to correct position : " + unsortedList);

        pivotIndex = startIndex;
        if (pivotIndex + 1 < unsortedList.size())
            sortUsingQuickSortAlgorithm(unsortedList.subList(0, pivotIndex + 1));
        if (pivotIndex + 1 < unsortedList.size())
            sortUsingQuickSortAlgorithm(unsortedList.subList(pivotIndex + 1, unsortedList.size()));
        return unsortedList;
    }
}