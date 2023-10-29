package io.esoma.cbj.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class GreatestCommonDivisorTest {

    @Test
    void testFindGcd1() {
        int[] array = new int[] {5, 10, 15};
        int expected = 5;
        int actual = GreatestCommonDivisor.findGcd(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testFindGcd2() {
        int[] array = new int[] {612, 806, 240, 412};
        int expected = 2;
        int actual = GreatestCommonDivisor.findGcd(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testFindGcd3() {
        int[] array = new int[] {9800, 500, 400, 100};
        int expected = 100;
        int actual = GreatestCommonDivisor.findGcd(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testFindGcd4() {
        int[] array = new int[] {79, 35, 10, 6, 120};
        int expected = 1;
        int actual = GreatestCommonDivisor.findGcd(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testFindGcd5() {
        int[] array = new int[] {226, 113};
        int expected = 113;
        int actual = GreatestCommonDivisor.findGcd(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testFindGcd6() {
        int[] array = new int[] {525, 70, 35};
        int expected = 35;
        int actual = GreatestCommonDivisor.findGcd(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd1() {
        int[] array = new int[] {4, 6, 5, 10, 15, 12};
        int expected = 5;
        int actual = GreatestCommonDivisor.bestGcd(array, 3);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd2() {
        int[] array = new int[] {88, 33, 111, 10, 9, 1, 22, 44, 66, 109, 780};
        int expected = 22;
        int actual = GreatestCommonDivisor.bestGcd(array, 4);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd3() {
        int[] array = new int[] {7, 3, 10, 22, 15, 6, 190, 6, 8, 19};
        int expected = 1;
        int actual = GreatestCommonDivisor.bestGcd(array, 10);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd4() {
        int[] array = new int[] {40, 100, 100, 100, 600, 50, 45, 150, 250};
        int expected = 100;
        int actual = GreatestCommonDivisor.bestGcd(array, 3);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd5() {
        int[] array = new int[] {24, 36, 12, 6, 4, 30};
        int expected = 12;
        int actual = GreatestCommonDivisor.bestGcd(array, 2);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd6() {
        int[] array = new int[] {
            71, 43, 183, 955, 96, 477, 620, 142, 929, 575, 645, 152, 883, 608, 513, 47, 902, 923, 915, 98, 782, 47, 173,
            533, 611, 760, 285, 502, 677, 453, 453, 128, 580, 453, 527, 54, 344, 121, 31, 451, 790, 53, 14, 600, 203,
            852, 218, 25, 331, 359, 977, 602, 625, 496, 817, 139, 3, 529, 531, 177, 137, 89, 66, 979, 48, 762, 641, 610,
            675, 968, 171, 904, 882, 446, 989, 789, 460, 550, 958, 44, 688, 685, 714, 903, 235, 682, 843, 374, 502, 451,
            708, 572, 91, 926, 729, 848, 758, 599, 125, 58, 419, 535, 560, 713, 934, 542, 541, 678, 281, 702, 185, 10,
            395, 127, 511, 932, 845, 467, 75, 359, 157, 302, 249, 527, 482, 929, 624, 883, 807, 188, 235, 848, 661, 607,
            10, 706, 461, 606, 739, 398, 147, 159, 476, 456, 306, 549, 959, 367, 771, 599, 806, 575, 348, 678, 774, 387,
            367, 756, 684, 396, 830, 39, 130, 687, 522, 511, 918, 99, 531, 43, 867, 524, 166, 639, 420, 841, 850, 914,
            762, 361, 659, 491, 54, 857, 68, 136, 83, 307, 156, 293, 936, 152, 282, 716, 642, 757, 202, 53, 754, 847,
            166, 604, 498, 662, 936, 453, 302, 578, 621, 198, 640, 929, 711, 150, 776, 955, 586, 174, 483, 196, 928, 2,
            365, 125, 515, 613, 734, 246, 680, 356, 277, 972, 57, 166, 841, 224, 813, 200, 616, 96, 453, 501, 626, 640,
            426, 290, 917, 483, 805, 952, 687, 401, 801, 178, 191, 889, 584, 39, 827, 393, 371, 609, 573, 173, 216, 138,
            949, 799, 484, 307, 978, 293, 882, 669, 117, 943, 937, 370, 889, 93, 687, 351, 358, 262, 123, 625, 240, 668,
            13, 65, 110, 781, 710, 203, 934, 745, 32, 619, 67, 637, 615, 87, 423, 872, 147, 606, 982, 709, 439, 446,
            636, 415, 944, 577, 141, 192, 846, 790, 411, 358, 813, 792, 48, 427, 386, 744, 524, 209, 222, 881, 212, 32,
            136, 270, 290, 560, 258, 274, 953, 593, 69, 897, 315, 676, 63, 951, 790, 547, 427, 439, 296, 928, 973, 571,
            163, 648, 524, 882, 476, 734, 846, 707, 675, 758, 500, 527, 449, 542, 538, 338, 112, 890, 435, 316, 516,
            846, 592, 889, 79, 915, 219, 154, 735, 857, 178, 61, 679, 538, 845, 225, 817, 76, 449, 884, 62, 76, 277,
            678, 96, 14, 280, 939, 764, 704, 118, 957, 688, 199, 969, 507, 691, 331, 784, 521, 357, 997, 507, 901, 388,
            282, 326, 533, 135, 708, 234, 696, 7, 860, 704, 500, 225, 784, 242, 855, 474, 156, 575, 541, 623, 80, 474,
            307, 525, 547, 937, 87, 221, 284, 909, 846, 893, 137, 415, 775, 538, 268, 445, 162, 631, 255, 65, 795, 2,
            367, 105, 992, 404, 789, 298, 463, 440, 219, 393, 70, 268, 300, 828, 84, 965, 758, 217, 684, 790, 745, 888,
            412, 144, 486, 527, 60, 979, 653, 583, 844, 526, 920, 766, 375, 819, 428
        };
        int expected = 997;
        int actual = GreatestCommonDivisor.bestGcd(array, 1);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd7() {
        int[] array = new int[] {70, 25, 15, 155, 165, 995, 450, 1005, 225, 85, 40, 55, 23, 10, 80, 105, 990, 2, 50};
        int expected = 15;
        int actual = GreatestCommonDivisor.bestGcd(array, 5);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd8() {
        int[] array = new int[] {111, 7, 13, 29, 2377, 1913, 99, 73, 193, 9967, 7993, 4447, 1087};
        int expected = 3;
        int actual = GreatestCommonDivisor.bestGcd(array, 2);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd9() {
        int[] array = new int[] {94, 47, 30, 150, 92, 46, 23, 138, 470, 230};
        int expected = 47;
        int actual = GreatestCommonDivisor.bestGcd(array, 3);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBestGcd10() {
        int[] array = new int[] {6, 4, 120, 728, 100, 46, 90, 130, 7, 22, 14};
        int expected = 2;
        int actual = GreatestCommonDivisor.bestGcd(array, 8);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }
}
