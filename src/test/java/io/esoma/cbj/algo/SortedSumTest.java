package io.esoma.cbj.algo;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortedSumTest {

  private static final String START = "Sorted Sum Test Start";
  private static final String END = "Sorted Sum Test End";

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    System.out.println(Banner.getTitleBanner(START, 3));
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println(Banner.getTitleBanner(END, 3));
    System.out.println();
  }

  @Test
  public void testNaiveInsertion1() throws Exception {
    int[] array = new int[] {4, 3, 2, 1};
    int expected = 65;
    int actual = SortedSum.naiveInsertion(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testNaiveInsertion2() throws Exception {
    int[] array = new int[] {9, 5, 8};
    int expected = 80;
    int actual = SortedSum.naiveInsertion(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testNaiveInsertion3() throws Exception {
    int[] array = new int[] {9, 5, 8, 7, 4, 12};
    int expected = 455;
    int actual = SortedSum.naiveInsertion(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testOptimalSum1() throws Exception {
    int[] array = new int[] {9, 5, 8, 7, 4, 12, 16};
    int expected = 751;
    int actual = SortedSum.optimalSum(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testOptimalSum2() throws Exception {
    int[] array =
        new int[] {
          776, 638, 509, 238, 326, 268, 66, 297, 156, 608, 485, 796, 711, 995, 543, 408, 481, 362,
          617, 444, 416, 885, 450, 754, 53, 398, 955, 125, 849, 757, 179, 761, 609, 98, 850, 18,
          269, 949, 960, 383, 474, 345, 558, 226, 333, 462, 206, 427, 862, 742, 228, 231, 596, 329,
          26, 307, 626, 677, 251, 492, 864, 718, 799, 694, 264, 200, 734, 394, 798, 352, 120, 445,
          222, 780, 10, 211, 580, 525, 416
        };
    int expected = 54551619;
    int actual = SortedSum.optimalSum(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testOptimalSum3() throws Exception {
    int[] array =
        new int[] {
          928026116, 954133790, 934849055, 975379763, 910330669, 924467517, 973461265, 977369382,
          902278800, 979663657
        };
    int expected = 257575486;
    int actual = SortedSum.optimalSum(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testOptimalSum4() throws Exception {
    int[] array = new int[] {1, 5, 7, 9, 11, 15};
    int expected = 448;
    int actual = SortedSum.optimalSum(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testOptimalSum5() throws Exception {
    int[] array =
        new int[] {
          44402, 41408, 24656, 44273, 56289, 47562, 77838, 71603, 11183, 77484, 54177, 18229
        };
    int expected = 20554621;
    int actual = SortedSum.optimalSum(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testOptimalSum6() throws Exception {
    int[] array =
        new int[] {
          392, 296, 156, 443, 409, 9, 9, 330, 199, 140, 102, 246, 341, 496, 78, 46, 193, 13, 111,
          106, 65, 129, 308, 307, 152, 256, 251, 84, 29, 178, 294, 360, 28, 236, 210, 306, 70, 130,
          148, 203, 245, 323, 249, 96, 109, 310, 89, 200, 10, 207, 495, 301, 465, 438, 489, 23, 88,
          401, 219, 125, 390, 319, 239, 45, 154, 222, 101, 352, 270, 123, 446, 404, 432, 174, 314,
          494, 26, 394, 419, 317, 146, 458, 310, 448, 215, 74, 97, 404, 423, 328, 466, 256, 220, 68,
          242, 115, 434, 207, 388, 310, 304, 331, 479, 262, 198, 223, 286, 469, 239, 75, 20, 181,
          302, 335, 193, 410, 455, 213, 284, 378, 90, 270, 434, 18, 381, 61, 252, 172, 50, 426, 358,
          42, 219, 114, 15, 49, 232, 38, 40, 225, 116, 393, 22, 270, 146, 465, 484, 155, 19, 255,
          132, 41, 250, 384, 152, 360, 131, 421, 315, 181, 153, 5, 329, 380, 170, 81, 59, 290, 257,
          82, 275, 217, 301, 246, 440, 114, 77, 19, 447, 403, 214, 108, 25, 178, 393, 482, 368, 176,
          192, 127, 360, 174, 103, 56, 245, 20, 498, 407, 267, 168, 105, 346, 75, 92, 473, 137, 494,
          77, 24, 87, 184, 16, 97, 38, 23, 169, 357, 202, 151, 497, 267, 465, 467, 479, 422, 120,
          403, 341, 255, 385, 156, 43, 432, 180, 216, 277, 472, 148, 479, 293, 33, 17, 126, 340, 77,
          312, 495, 123, 38, 324, 471, 104, 130, 219, 439, 358, 106, 17, 176, 432, 16, 270, 142,
          456, 118, 159, 362, 240, 226, 187, 322, 398, 296, 41, 65, 20, 217, 64, 204, 163, 341, 471,
          428, 278, 294, 138, 140, 436, 419, 38, 83, 415, 346, 146, 426, 465, 224, 19, 75, 242
        };
    int expected = 436562823;
    int actual = SortedSum.optimalSum(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }
}
