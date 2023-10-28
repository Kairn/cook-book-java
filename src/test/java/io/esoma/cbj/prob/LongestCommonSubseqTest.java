package io.esoma.cbj.prob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.tinylog.Logger;

class LongestCommonSubseqTest {

  @ParameterizedTest
  @CsvSource({
    "bcv, accid, 1",
    "rtg5, '', 0",
    "AGCDFGDA, GFAADGDA, 5",
    "398397970, 3399917206, 6",
    "12341, 341213, 3",
    "44321751322856067355245610665431643040435861973548, 5217202868630547660313515968975138408327244335729710, 24",
    "vrqwpvtbfekzcdokahhyoilammjswyqbafctbvrjvdphaqahuqjsjxxslkywdlqudvchnbtiphksqiqwfimigumwmigbepmbeat, wtoexkciduowkjhtdhymjwyfonqbavfybrjvagtmqchuqxrslrwkxwfdunlquvennfiieihqzkosgqinrkqqimgmwdicyacmeahk, 55",
    "tiroqeitqitr, zxmnjsdmfncvjg, 0"
  })
  void testFindLcs(String s1, String s2, int lcs) {
    int actual = LongestCommonSubseq.findLcs(s1, s2);
    Logger.debug(actual);
    assertEquals(lcs, actual);
  }
}
