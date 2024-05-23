/**
 * Partition using a variant of Lomuto's partitioning method.
 */
public class LomutoPartition implements PartitionAlgorithm {

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * It's a singleton class, right?
   */
  public static final PartitionAlgorithm ALGORITHM = new LomutoPartition();

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the name of the algorithm.
   */
  public String name() {
    return "Lomuto";
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

    // Pick a pivot and swap to the end.
    int pivotLoc = lb + (int) Math.floor(Math.random() * (ub - lb));
    iarr.swap(pivotLoc, ub - 1);
    int pivot = iarr.get(ub - 1);

    // Iterate through, moving things as appropriate
    int small = lb;
    int val;
    for (int i = lb; i < ub-1; i++) {
      if ((val = iarr.get(i)) < pivot) {
        if (i != small) {
          iarr.set(i, iarr.get(small));
          iarr.set(small, val);
        } // if indices are different
        small++;
      } // if the value is small
    } // for

    // Swap the pivot back to the middle
    iarr.swap(small, ub-1);

    // And we're done.
    return small;
  } // partition
} // class LomutoPartition
