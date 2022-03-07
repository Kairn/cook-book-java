package io.esoma.cbj.algo;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestCommonSubseqTest {

  private static final String START = "LCS Test Start";
  private static final String END = "LCS Test End";

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
  public void testFindLcs1() {
    String s1 = "bcv";
    String s2 = "accid";
    int expected = 1;
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFindLcs2() {
    String s1 = "rtg5";
    String s2 = "";
    int expected = 0;
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFindLcs3() {
    String s1 = "AGCDFGDA";
    String s2 = "GFAADGDA";
    int expected = 5;
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFindLcs4() {
    String s1 = "398397970";
    String s2 = "3399917206";
    int expected = 6;
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFindLcs5() {
    String s1 = "12341";
    String s2 = "341213";
    int expected = 3;
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFindLcs6() {
    String s1 = "44321751322856067355245610665431643040435861973548";
    String s2 = "5217202868630547660313515968975138408327244335729710";
    int expected = 24;
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFindLcs7() {
    String s1 =
        "vrqwpvtbfekzcdokahhyoilammjswyqbafctbvrjvdphaqahuqjsjxxslkywdlqudvchnbtiphksqiqwfimigumwmigbepmbeat";
    String s2 =
        "wtoexkciduowkjhtdhymjwyfonqbavfybrjvagtmqchuqxrslrwkxwfdunlquvennfiieihqzkosgqinrkqqimgmwdicyacmeahk";
    int expected = 55;
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFindLcs8() {
    String s1 = "tiroqeitqitr";
    String s2 = "zxmnjsdmfncvjg";
    int expected = 0;
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }
}
