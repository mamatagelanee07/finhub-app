package com.andigeeky.finnhub.data.temp

object Exercises {
    fun findPeakOfMountain(elements: List<Int>): Int {

        val index = 0
        for (i in elements) {
            if (elements[i] > elements[i + 1]) return i
        }
        return index
    }

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val (small, large) = if (nums1.size <= nums2.size) Pair(nums1, nums2) else Pair(nums2,
            nums1)
        var i = 0
        var j = 0
        val result = mutableListOf<Int>()
        small.sort()
        large.sort()
        println("small : ${small.asList()} large: ${large.asList()}")
        while (i < small.size && j < large.size) {
            when {
                small[i] == large[j] -> {
                    result.add(small[i])
                    i++
                    j++
                }
                small[i] < large[j] -> i++
                else -> j++
            }

        }
        return result.toIntArray()
    }

    fun plusOne(digits: IntArray): IntArray {
        var i = digits.size - 1
        while (i >= 0) {
            if (digits[i] < 9) {
                digits[i]++
            } else {
                digits[i] = 0
            }
            i--
        }
        return if (digits[0] == 0) intArrayOf(1) + digits else digits
    }

    fun moveZeroes(nums: IntArray): IntArray {
        var left = 0
        var right = 0
        while (right < nums.size) {
            if (nums[right] != 0) {
                val temp = nums[left]
                nums[left] = nums[right]
                nums[right] = temp
                left++
            }
            right++
        }
        return nums
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = hashMapOf<Int, Int>()
        nums.forEachIndexed { index, element ->
            if (map.containsKey(element)) return intArrayOf(index, map[element] ?: -1)
            else map[target - element] = index
        }
        return intArrayOf(-1, -1)
    }

    fun prettyPrintFolderStructure() {

    }

    fun findMaxInBinaryTree() {

    }

    fun findMaxDepthOfBinaryTree() {

    }

    /**
     * Convert to list of digits
     * Double every single digit
     * if doubled digit is two digit, add those
     */
    fun luhnAlgorithm(cardNumber: String): Boolean {
        if (cardNumber.isBlank()) return false
        println(cardNumber)
        val pair = cardNumber.toCharArray().reversed()
            .asSequence()
            .map { it.digitToInt() }.withIndex().partition { i ->
                i.index % 2 == 0
            }
        val sum =
            pair.first.sumOf { it.value } + pair.second.sumOf { it.value / 5 + (2 * it.value) % 10 }
        return sum % 10 == 0
    }
}