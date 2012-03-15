package edu.spsu.rgoodwin.maxsubseqsum

class MaxSubSum {
  // Class variables
  var maxLeftSum = 0
  var maxRightSum = 0
  var middleSum = 0
  // Maximum Subsequence
  def maxSubSum(A: Array [Int], left: Int, right: Int): Int = {
    if (left == right) /* Base case */
      if (A(left) > 0)
        return A(left)
      else
        return 0

    /* Calculate the center */
    val center: Int = (left + right) / 2

    /* Make recursive calls */
    maxLeftSum = maxSubSum(A, left, center)
    maxRightSum = maxSubSum(A, center + 1, right)

    /* Find the max subsequence sum in the left half where the */
    /* subsequence spans the last element of the left half */
    var maxLeftBorderSum, leftBorderSum = 0
    var i = center
    while(i >= left) {
      leftBorderSum += A(i)
      if (leftBorderSum > maxLeftBorderSum)
        maxLeftBorderSum = leftBorderSum
      i -= 1
    }

    var maxRightBorderSum, rightBorderSum = 0
    var j = center + 1
    while(j <= right) {
      rightBorderSum += A(j)
      if (rightBorderSum > maxRightBorderSum)
        maxRightBorderSum = rightBorderSum
      j += 1
    }

    /* The function max3 returns the largest of */
    /* its three arguments */
    middleSum = maxLeftBorderSum + maxRightBorderSum
    max3(maxLeftSum, maxRightSum, middleSum)
  }

  // Return maximum of three integers.
  def max3(a: Int, b: Int, c: Int): Int = {
    if (a > b && b > c) a
    else if (b > c) b
    else c
  }
}