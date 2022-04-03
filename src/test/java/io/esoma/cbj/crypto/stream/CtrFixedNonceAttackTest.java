package io.esoma.cbj.crypto.stream;

import io.esoma.cbj.crypto.basic.FixedXor;
import io.esoma.cbj.crypto.core.Base64Util;
import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static io.esoma.cbj.crypto.stream.CtrFixedNonceAttack.TEST_KEY;
import static io.esoma.cbj.crypto.stream.CtrFixedNonceAttack.TEST_NONCE;
import static org.junit.Assert.assertEquals;

public class CtrFixedNonceAttackTest {

  private static final String START = "CTR Attack Test Start";
  private static final String END = "CTR Attack Test End";

  @BeforeClass
  public static void setUpBeforeClass() {
    System.out.println(Banner.getTitleBanner(START, 3));
  }

  @AfterClass
  public static void tearDownAfterClass() {
    System.out.println(Banner.getTitleBanner(END, 3));
    System.out.println();
  }

  @Test
  public void testDecryptWithGuess() {
    final String encodedPlainText = "aWxvdmVsYXVyZW5zY290dA==";
    final byte[] plainTextBytes = Base64Util.decodeToByteArray(encodedPlainText);
    final byte[] encrypted =
        AesInCtr.encrypt(plainTextBytes, TEST_KEY, TEST_NONCE, new LeIntegerCounterGenerator());
    byte[] guessedKeyStream = CtrFixedNonceAttack.loadAndGuess();
    String actual =
        new String(FixedXor.combine(encrypted, guessedKeyStream), StandardCharsets.US_ASCII);
    System.out.println(actual);
    assertEquals(encodedPlainText, Base64Util.encodeFromBytes(actual.toLowerCase().getBytes()));
  }
}
