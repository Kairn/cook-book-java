package io.esoma.cbj.crypto.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class RepeatingXorTest {

  @Test
  void testEncrypt() {
    final String message =
        "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal";
    final String key = "ICE";
    final String expected =
        "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f";
    String actual = RepeatingXor.create(message, key, true);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testDecrypt() {
    final String message =
        "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal";
    final String key = "ICE";
    final String encrypted = RepeatingXor.create(message, key, false);
    String actual = RepeatingXor.create(encrypted, key, false);
    Logger.debug(actual);
    assertEquals(message, actual);
  }
}
