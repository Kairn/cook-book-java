package io.esoma.cbj.crypto.stream;

import io.esoma.cbj.crypto.core.Base64Util;
import org.junit.Test;
import org.tinylog.Logger;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class AesInCtrTest {

  @Test
  public void testEncrypt() {
    final String key = "YELLOW SUBMARINE";
    final byte[] nonce = new byte[8];
    final CounterGenerator counterGenerator = new LeIntegerCounterGenerator();
    final String plainText = "Yo, VIP Let's kick it Ice, Ice, baby Ice, Ice, baby ";
    final String expected =
        "L77na/nrFsKvynd6HzOoG7GHTLXsTVu9qvY/2syLXzhPweyyMTJULu/6/kXX0KSvoOLSFQ==";
    String actual =
        Base64Util.encodeFromBytes(
            AesInCtr.decrypt(
                plainText.getBytes(StandardCharsets.US_ASCII), key, nonce, counterGenerator));
    Logger.debug(actual);
    assertEquals(expected, actual);
  }
}
