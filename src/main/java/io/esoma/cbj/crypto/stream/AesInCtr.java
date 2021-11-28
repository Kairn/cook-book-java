package io.esoma.cbj.crypto.stream;

import io.esoma.cbj.crypto.basic.AesInEcb;
import io.esoma.cbj.crypto.core.Base64Util;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.charset.StandardCharsets;

/**
 * Class for implementing AES in CTR (counter) mode. The standard AES-128 scheme is used to generate
 * the key stream by encrypting the concatenation of a nonce and a running counter with a specific
 * key, and streams are continuously generated to XOR with the plain text to produce the cipher
 * text.
 *
 * @author Eddy Soma
 */
public class AesInCtr {

  public static void main(String[] args) {
    String base64Cipher =
        "L77na/nrFsKvynd6HzOoG7GHTLXsTVu9qvY/2syLXzhPweyyMTJULu/6/kXX0KSvoOLSFQ==";
    String key = "YELLOW SUBMARINE";
    byte[] nonce = new byte[8];
    byte[] cipherBytes = Base64Util.decodeToByteArray(base64Cipher);

    System.out.println(
        new String(
            decrypt(cipherBytes, key, nonce, new LeIntegerCounterGenerator()),
            StandardCharsets.US_ASCII));
  }

  /**
   * Decrypts the cipher with CTR mode for the given key, nonce, and a counter generator. This is a
   * symmetrical method, meaning it can be used for encryption too.
   *
   * @param cipherBytes the cipher in bytes
   * @param key the encryption key
   * @param nonce the nonce
   * @param counterGenerator an object that generates counter streams
   * @return
   */
  public static byte[] decrypt(
      byte[] cipherBytes, String key, byte[] nonce, CounterGenerator counterGenerator) {
    int len = cipherBytes.length;
    byte[] decrypted = new byte[len];
    byte[] currentStream =
        AesInEcb.encrypt(ArrayUtils.addAll(nonce, counterGenerator.getNext()), key, false);
    int streamPosition = 0;

    for (int i = 0; i < len; ++i) {
      decrypted[i] = (byte) (cipherBytes[i] ^ currentStream[streamPosition++]);
      if (streamPosition == 16) {
        currentStream =
            AesInEcb.encrypt(ArrayUtils.addAll(nonce, counterGenerator.getNext()), key, false);
        streamPosition = 0;
      }
    }

    return decrypted;
  }

  public static byte[] encrypt(
      byte[] plainBytes, String key, byte[] nonce, CounterGenerator counterGenerator) {
    return decrypt(plainBytes, key, nonce, counterGenerator);
  }
}
