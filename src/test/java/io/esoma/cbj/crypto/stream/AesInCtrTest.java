package io.esoma.cbj.crypto.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.esoma.cbj.crypto.core.Base64Util;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class AesInCtrTest {

    @Test
    void testEncrypt() {
        final String key = "YELLOW SUBMARINE";
        final byte[] nonce = new byte[8];
        final CounterGenerator counterGenerator = new LeIntegerCounterGenerator();
        final String plainText = "Yo, VIP Let's kick it Ice, Ice, baby Ice, Ice, baby ";
        final String expected = "L77na/nrFsKvynd6HzOoG7GHTLXsTVu9qvY/2syLXzhPweyyMTJULu/6/kXX0KSvoOLSFQ==";
        String actual = Base64Util.encodeFromBytes(
                AesInCtr.decrypt(plainText.getBytes(StandardCharsets.US_ASCII), key, nonce, counterGenerator));
        Logger.debug(actual);
        assertEquals(expected, actual);
    }
}
