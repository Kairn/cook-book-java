package io.esoma.cbj.crypto.stream;

import static io.esoma.cbj.crypto.stream.CtrFixedNonceAttack.TEST_KEY;
import static io.esoma.cbj.crypto.stream.CtrFixedNonceAttack.TEST_NONCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.esoma.cbj.crypto.basic.FixedXor;
import io.esoma.cbj.crypto.core.Base64Util;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class CtrFixedNonceAttackTest {

  @Test
  void testDecryptWithGuess() {
    final String encodedPlainText = "aWxvdmVsYXVyZW5zY290dA==";
    final byte[] plainTextBytes = Base64Util.decodeToByteArray(encodedPlainText);
    final byte[] encrypted =
        AesInCtr.encrypt(plainTextBytes, TEST_KEY, TEST_NONCE, new LeIntegerCounterGenerator());
    byte[] guessedKeyStream = CtrFixedNonceAttack.loadAndGuess();
    String actual =
        new String(FixedXor.combine(encrypted, guessedKeyStream), StandardCharsets.US_ASCII);
    Logger.debug(actual);
    assertEquals(encodedPlainText, Base64Util.encodeFromBytes(actual.toLowerCase().getBytes()));
  }
}
