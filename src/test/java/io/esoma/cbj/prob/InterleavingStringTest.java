package io.esoma.cbj.prob;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.tinylog.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class InterleavingStringTest {

  @Test
  @Parameters({
    "aabcc, dbbca, aadbbcbcac",
    "ajajajajajajajajaja, vhweoithweohoiweht, ajajvhajweoithajajajajajweohoiwajeaht",
    "vvvvvvvvvvvvvvvvv, ccccccccccccccccc, cvcvcvcvcvcvcvcvcvcvcvcvcvcvcvvvcc",
    ", , ",
    "fgrtewtqewtdg, , fgrtewtqewtdg",
    "muklopighdrrt, aszxcfgserqsgfrtwttyhddgrtwfgcghdre, asmuklzxcfgopigserqsghdfrrrtwtttyhddgrtwfgcghdre"
  })
  public void testJudgeWhenTrue(String src1, String src2, String dest) {
    boolean actual = InterleavingString.judge(src1, src2, dest);
    Logger.debug(actual);
    assertTrue(actual);
  }

  @Test
  @Parameters({
    "aabcc, dbbca, aadbbbaccc",
    "ghghg, rtrtr, rtrtghghggr",
    "qwqqasdsasdweqwe, fffff, fqwqaqsfdsafsdweqfwef",
    "hhhhhhhhhhhhhhhhhh, bbbbbbbbbbbbbbbbb, hhhhhhhhhhhhhhhhhbbbbbbbbbbbbbbbbb",
    "mlkjdk, jdkmlk, mljdkkdjmlkk"
  })
  public void testJudgeWhenFalse(String src1, String src2, String dest) {
    boolean actual = InterleavingString.judge(src1, src2, dest);
    Logger.debug(actual);
    assertFalse(actual);
  }
}
