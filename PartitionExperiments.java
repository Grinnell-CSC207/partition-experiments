import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Fun with partitioning and Quicksort.
 */
public class PartitionExperiments {

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The smallest array size.
   */
  static final int SMALL = 1000000;

  /**
   * The largest array size.
   */
  static final int LARGE =  20000000;

  /**
   * The number of tests.
   */
  static final int TESTS = 5;

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The total number of reads.
   */
  static int totalReads;

  /**
   * The total number of writes.
   */
  static int totalWrites;

  /**
   * The number of reads in the current call.
   */
  static int currentReads;

  /**
   * The number of writes in the current call.
   */
  static int currentWrites;

  // +-----------+---------------------------------------------------
  // | Quicksort |
  // +-----------+

  /**
   * Sort an array using a partition algorithm.
   */
  public static void quicksort(IntArray iarr, PartitionAlgorithm alg) {
    quicksort(iarr, alg, 0, iarr.length());
  } // quicksort

  /**
   * Sort the subarray at positions [lb..ub) using a partition
   * algorithm.
   */
  public static void quicksort(IntArray iarr, PartitionAlgorithm alg, int lb, int ub) {
    // A subarray of zero or one elements is sorted.
    if (ub - lb <= 1) {
      return;
    }
    // Divide and conquer
    int pivotLoc = alg.partition(iarr, lb, ub);
    quicksort(iarr, alg, lb, pivotLoc);
    quicksort(iarr, alg, pivotLoc+1, ub);
  }

  // +-------------+-------------------------------------------------
  // | Experiments |
  // +-------------+

  /**
   * Run partition on a full array.
   */
  public static void partitionExperiment(PrintWriter pen, int size) {
    int[] vals = new int[size];
    for (int i = 0; i < size; i++) {
      vals[i] = i;
    } // for
    IntArray iarr = new IntArray(vals);
    iarr.permute();
    LomutoPartition.ALGORITHM.partition(iarr.clone(), 0, size);
  } // partitionExperiment(PrintWriter, int)

  /**
   * Run Quicksort on an array of a given size.
   */
  public static void qsExperiment(PrintWriter pen, int size) {
    // Build an array of the appropriate size.
    int[] vals = new int[size];
    for (int i = 0; i < size; i++) {
      vals[i] = i;
    }
    IntArray iarr = new IntArray(vals);
    iarr.permute();
    for (int i = 0; i < 5; i++) {
      qsExperiment(pen, iarr.clone(), HoarePartition.ALGORITHM);
    }
    for (int i = 0; i < 5; i++) {
      qsExperiment(pen, iarr.clone(), HoarePartitionAlt.ALGORITHM);
    }
    for (int i = 0; i < 5; i++) {
      qsExperiment(pen, iarr.clone(), LomutoPartition.ALGORITHM);
    }
  } // qsExperiment(PrintWriter, size)

  /**
   * Run Quicksort on a particular array using a given partition
   * algorithm.
   */
  public static void qsExperiment(PrintWriter pen, IntArray iarr, PartitionAlgorithm alg) {
    // Run the algorithm
    long startTime = System.currentTimeMillis();
    quicksort(iarr, alg);
    long elapsed = System.currentTimeMillis() - startTime;

    // Gather some data
    int gets = iarr.gets();
    int sets = iarr.sets();
    int len = iarr.length();

    // Check the result
    String result = "OK";
    for (int i = 0; i < len; i++) {
      if (iarr.get(i) != i) {
        result = "FAILED";
        break;
      } //if
    } // for

    // Print some message
    pen.printf("%s\tQuicksort\t%d\t%d\t%d\t%d\t%s\n",
               alg.name(), iarr.length(), gets, sets, elapsed, result);
  } // qsExperiment(PrintWriter, IntArray, PartitionAlgorithm

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Build some arrays, sort them, display statistics.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("Algorithm\tContext\tSize\tGets\tSets\n");
    for (int i = 0; i < 5; i++) {
      qsExperiment(pen, 10000000);
    }
    pen.close();
  } // main

} // class PartitionExperiments
