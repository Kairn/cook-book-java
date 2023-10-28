package io.esoma.cbj.prob;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.tinylog.Logger;

class InterleavingStringTest {

  @ParameterizedTest
  @CsvSource({
    "aabcc, dbbca, aadbbcbcac",
    "ajajajajajajajajaja, vhweoithweohoiweht, ajajvhajweoithajajajajajweohoiwajeaht",
    "vvvvvvvvvvvvvvvvv, ccccccccccccccccc, cvcvcvcvcvcvcvcvcvcvcvcvcvcvcvvvcc",
    "'', '', ''",
    "fgrtewtqewtdg, '', fgrtewtqewtdg",
    "muklopighdrrt, aszxcfgserqsgfrtwttyhddgrtwfgcghdre, asmuklzxcfgopigserqsghdfrrrtwtttyhddgrtwfgcghdre"
  })
  void testJudgeWhenTrue(String src1, String src2, String dest) {
    boolean actual = InterleavingString.judge(src1, src2, dest);
    Logger.debug(actual);
    assertTrue(actual);
  }

  @ParameterizedTest
  @CsvSource({
    "aabcc, dbbca, aadbbbaccc",
    "ghghg, rtrtr, rtrtghghggr",
    "qwqqasdsasdweqwe, fffff, fqwqaqsfdsafsdweqfwef",
    "hhhhhhhhhhhhhhhhhh, bbbbbbbbbbbbbbbbb, hhhhhhhhhhhhhhhhhbbbbbbbbbbbbbbbbb",
    "mlkjdk, jdkmlk, mljdkkdjmlkk"
  })
  void testJudgeWhenFalse(String src1, String src2, String dest) {
    boolean actual = InterleavingString.judge(src1, src2, dest);
    Logger.debug(actual);
    assertFalse(actual);
  }
}
