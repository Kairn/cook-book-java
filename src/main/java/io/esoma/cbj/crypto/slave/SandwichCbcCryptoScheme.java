package io.esoma.cbj.crypto.slave;

import io.esoma.cbj.crypto.block.AesInCbc;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Class that implements a cryptographic scheme using AES-128 in CBC mode. It always puts the user
 * input in between some prefix and suffix before encrypting, hence the name sandwich.
 *
 * @author Eddy Soma
 */
public class SandwichCbcCryptoScheme implements CryptoScheme {

  private static final String SECRET_KEY = "PURPLE SUBMARINE";
  private static final byte[] IV_BLOCK = new byte[16];

  private final byte[] prefix;
  private final byte[] suffix;

  public SandwichCbcCryptoScheme(byte[] prefix, byte[] suffix) {
    this.prefix = prefix == null ? new byte[0] : prefix;
    this.suffix = suffix == null ? new byte[0] : suffix;
  }

  @Override
  public byte[] encrypt(byte[] buffer) {
    if (buffer == null || buffer.length == 0) {
      throw new IllegalArgumentException("Buffer is empty");
    }

    byte[] text = ArrayUtils.addAll(prefix, buffer);
    text = ArrayUtils.addAll(text, suffix);

    return AesInCbc.encrypt(text, SECRET_KEY, IV_BLOCK, true);
  }

  @Override
  public byte[] decrypt(byte[] buffer) {
    if (buffer == null || buffer.length == 0) {
      throw new IllegalArgumentException("Buffer is empty");
    }

    return AesInCbc.decrypt(buffer, SECRET_KEY, IV_BLOCK);
  }
}
