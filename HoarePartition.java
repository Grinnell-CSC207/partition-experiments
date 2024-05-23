/**
 * Partition using a variant of Hoare's partitioning method.
 */
public class HoarePartition implements PartitionAlgorithm {

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * It's a singleton class, right?
   */
  public static final PartitionAlgorithm ALGORITHM = new HoarePartition();

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the name of the algorithm.
   */
  public String name() {
    return "Hoare";
  } // name()

  /**
   * Partition the elements at positions [lb .. ub) of iarr. 
   *
   * @param     iarr    the array to partition
   * @param     lb      the lower bound of the subarray (inclusive)
   * @param     ub      the upper bound of the subarray (exclusive)
   * @return            the index of the pivot
   */
  public int partition(IntArray iarr, int lb, int ub) {
    // Sanity check
    if (lb >= ub) return lb;

    // Pick a pivot and swap to the beginning.
    int pivotLoc = lb + (int) Math.floor(Math.random() * (ub - lb));
    iarr.swap(lb, pivotLoc);
    int pivot = iarr.get(lb);

    // Iterate through, moving things as appropriate
    int small = lb+1;
    int large = ub;
    int left = 0;
    int right = 0;
    while (small < large) {
      while ((small < large) && (left = iarr.get(small)) <= pivot) {
        ++small;
      } 
      while ((small < large) && (right = iarr.get(large - 1)) > pivot) {
        --large;
      } 
      if (small < (large - 1)) {
        iarr.set(small++, right);
        iarr.set(--large, left);
      } 
    } // while

    // Swap the pivot back to the middle
    iarr.swap(lb, small-1);

    // And we're done.
    return small-1;
  } // partition
} // class HoarePartition
