import java.util.Arrays;

/**
 * A simple array with logging.
 */
public class IntArray {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The values in the array.
   */
  int[] values;

  /**
   * The number of calls to get.
   */
  int gets;

  /**
   * The number of calls to set.
   */
  int sets;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new array.
   */
  public IntArray(int[] values) {
    this.values = values;
    this.gets = 0;
    this.sets = 0;
  } // IntArray(int[])

  // +-----------------+---------------------------------------------
  // | Primary methods |
  // +-----------------+

  /**
   * Read a value from the array.
   */
  public int get(int i) {
    ++this.gets;
    if ((i < 0) || (i >= values.length)) {
      String message = "Invalid get at position " + i + " in " + Arrays.toString(values);
      throw new ArrayIndexOutOfBoundsException(message);
    }
    return this.values[i];
  } // get(int)

  /**
   * Write a value to the array.
   */
  public void set(int i, int val) {
    if ((i < 0) || (i >= values.length)) {
      String message = "Invalid set at position " + i + " in " + Arrays.toString(values);
      throw new ArrayIndexOutOfBoundsException(message);
    }
    ++this.sets;
    this.values[i] = val;
  } // set(int, int)

  /**
   * Determine how many values are in the array.
   */
  int length() {
    return this.values.length;
  } // length()

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Swap the values at positions i and j.
   */
  public void swap(int i, int j) {
    int tmp = this.get(i);
    this.set(i, this.get(j));
    this.set(j, tmp);
  } // swap(int, int)

  /**
   * Make a copy of this array.
   */
  public IntArray clone() {
    return new IntArray(Arrays.copyOf(this.values, this.values.length));
  } // clone

  /**
   * Permute the array, without counting the sets and gets.
   */
  public void permute() {
    int sets = this.sets;
    int gets = this.gets;
    for (int i = 0; i < this.values.length; i++) {
      this.swap(i, (int) Math.floor(Math.random() * this.values.length));
    } // for
    this.sets = sets;
    this.gets = gets;
  } // permute

  /**
   * Convert to a string.
   */
  public String toString() {
    return Arrays.toString(this.values);
  } // toString()

  // +--------------+------------------------------------------------
  // | Logging info |
  // +--------------+

  /**
   * Determine the number of calls to get.
   */
  public int gets() {
    return this.gets;
  } // gets()

  /**
   * Determine the number of calls to set.
   */
  public int sets() {
    return this.sets;
  } // sets()

} // class IntArray
