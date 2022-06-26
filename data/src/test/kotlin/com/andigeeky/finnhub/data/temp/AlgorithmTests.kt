package com.andigeeky.finnhub.data.temp

import com.andigeeky.finnhub.data.temp.Problems.buildTree
import org.junit.Assert
import org.junit.Test

/*
Part I
Design a stack that supports push, pop, top, and retrieving the minimum element.

push(x) -- Push element x onto stack.

pop() -- Removes the element on top of the stack.

top -- Get the top element.

min -- Retrieve the minimum element in the stack.

Your MinStack implementation should be generic over it's elements.

Example:
val minStack = MinStack<Int>()
minStack.push(-2)
minStack.push(0)
minStack.push(-3)
minStack.min        // --> Returns -3.
minStack.pop()
minStack.top        // --> Returns 0.
minStack.min        // --> Returns -2.
*/

class AlgorithmTests {
    @Test
    fun `Given an element is provided When push in stack THEN should be available`() {
        val minStack = StackImpl<Int>()
        minStack.push(-2)
        minStack.push(0)
        minStack.push(-3)
        Assert.assertEquals(-3, minStack.min)    // --> Returns -3.
        minStack.pop()
        Assert.assertEquals(0, minStack.top)        // --> Returns 0.
        Assert.assertEquals(-2, minStack.min)        // --> Returns -2.
    }

    @Test
    fun `Given sort array`() {
        val sorted = SortAlgorithm.sortUsingBubbleSortAlgorithm(listOf(6, 4, 8, 3, 9, 7, 9, 3, 1))
        Assert.assertEquals(listOf(1, 3, 3, 4, 6, 7, 8, 9, 9), sorted)
    }

    @Test
    fun `Given search an element`() {
        val index = SearchingAlgorithms.binarySearchImperative(listOf(1, 3, 4, 6, 7, 8, 9, 9), 9)
        Assert.assertEquals(6, index)
    }

    @Test
    fun `Given find boundary`() {
        val index = SearchingAlgorithms.findBoundaryOfRightSectionV2(listOf(true))
        Assert.assertEquals(0, index)
    }

    @Test
    fun `find the index of the first element in the array that is larger than or equal to the target`() {
        val index =
            SearchingAlgorithms.findElementNotSmallerThanTargetV2(listOf(1, 3, 3, 5, 8, 8, 10), 2)
        Assert.assertEquals(1, index)
    }

    @Test
    fun `find the first occurrence of the target and return its index`() {
        val index =
            SearchingAlgorithms.findFirstOccurrenceWithDuplicates(
                listOf(1, 3, 3, 3, 3, 6, 10, 10, 10, 100), 3
            )
        Assert.assertEquals(1, index)
    }

    @Test
    fun `find square of number`() {
        val result = Problems.findSquareOfNumber(6)
        Assert.assertEquals(36, result)
    }

    @Test
    fun `find square root of number`() {
        val result = Problems.findSquareRootOfNumber(8)
        Assert.assertEquals(2, result)
    }

    @Test
    fun `find min in rotated array`() {
        val result = Problems.findMinInRotatedArrayV2(listOf(30))
        Assert.assertEquals(0, result)
    }

    @Test
    fun `find peak of mountain`() {
        val result = Exercises.findPeakOfMountain(listOf(0,1,2,3,2,1))
        Assert.assertEquals(3, result)
    }

    @Test
    fun `find intersection`() {
        val result = Exercises.intersect(
            intArrayOf(1,2,2,1),
            intArrayOf(2,2)
        )
        Assert.assertArrayEquals(intArrayOf(2,2), result)
    }

    @Test
    fun `plus 1`() {
        val result = Exercises.plusOne(
            intArrayOf(1,2,3)
        )
        Assert.assertEquals(intArrayOf(1,2,4).toList(), result.toList())
    }

    @Test
    fun `move zeros`() {
        val result = Exercises.moveZeroes(
            intArrayOf(0,1,0)
        )
        Assert.assertEquals(intArrayOf(1,0,0).toList(), result.toList())
    }

    @Test
    fun `two sum`() {
        val result = Exercises.twoSum(
            intArrayOf(3,2,4),
            6
        )
        Assert.assertEquals(intArrayOf(2,1).toList(), result.toList())
    }

    @Test
    fun `find max depth of tree`() {
        val tree = listOf("1", "2", "3", "x", "x", "4", "x", "x", "5","6","x","x","7", "8","x", "x","9","10", "x","x","11", "x","x")
        val result = Problems.treeMaxDepth(
            buildTree(tree.iterator()) {
                if (it == "x") null
                else Integer.parseInt(it)
            }
        )
        Assert.assertEquals(result, 5)
    }

    @Test
    fun `find number of visible nodes of tree`() {
        val tree = listOf("5", "4", "3", "x", "x", "8", "x", "x", "6","x","x")
        val result = Problems.countVisibleNodes(
            buildTree(tree.iterator()) {
                if (it == "x") null
                else Integer.parseInt(it)
            },
            Int.MIN_VALUE
        )
        Assert.assertEquals(3, result)
    }

    @Test
    fun `find if tree is balanced`() {
//        val tree = listOf("1", "2", "4", "x", "7", "x", "x","5", "x", "x", "3", "x", "6","8","x", "x", "x")
        val tree = listOf("1", "2", "4", "x", "7", "x", "x","5", "x", "x", "3", "x", "6", "x", "x")
        val result = Problems.isBalanced(
            buildTree(tree.iterator()) {
                if (it == "x") null
                else Integer.parseInt(it)
            },
        )
        Assert.assertEquals(true, result !=-1)
    }
}