/**
 * Things that can partition arrays of integers.
 */
public interface PartitionAlgorithm {
  /**
   * Get the name of the algorithm.
   */
  public String name();

  /**
   * Partition the elements at positions [lb .. ub) of iarr. 
   *
   * @param     iarr    the array to partition
   * @param     lb      the lower bound of the subarray (inclusive)
   * @param     ub      the upper bound of the subarray (exclusive)
   * @return            the index of the pivot
   */
  public int partition(IntArray iarr, int lb, int ub);
} // interface PartitionAlgorithm
