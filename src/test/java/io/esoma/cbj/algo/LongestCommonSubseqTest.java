package io.esoma.cbj.algo;

import io.esoma.cbj.util.Banner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
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
  @Parameters({
    "bcv, accid, 1",
    "rtg5, , 0",
    "AGCDFGDA, GFAADGDA, 5",
    "398397970, 3399917206, 6",
    "12341, 341213, 3",
    "44321751322856067355245610665431643040435861973548, 5217202868630547660313515968975138408327244335729710, 24",
    "vrqwpvtbfekzcdokahhyoilammjswyqbafctbvrjvdphaqahuqjsjxxslkywdlqudvchnbtiphksqiqwfimigumwmigbepmbeat, wtoexkciduowkjhtdhymjwyfonqbavfybrjvagtmqchuqxrslrwkxwfdunlquvennfiieihqzkosgqinrkqqimgmwdicyacmeahk, 55",
    "tiroqeitqitr, zxmnjsdmfncvjg, 0"
  })
  public void testFindLcs(String s1, String s2, int lcs) {
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    System.out.println(actual);
    assertEquals(lcs, actual);
  }
}
