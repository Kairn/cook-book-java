package io.esoma.cbj.crypto.block;

import io.esoma.cbj.crypto.slave.CryptoScheme;
import io.esoma.cbj.crypto.slave.SandwichCbcCryptoScheme;
import org.apache.commons.lang3.StringUtils;

/**
 * Class that studies the bit flipping attack used against CBC encryption mode. It takes advantage
 * of the fact that an one bit edit in any cipher block will cause an identical bit flip in the next
 * block.
 *
 * @author Eddy Soma
 */
public class CbcBitFlippingAttack {

  private static final int BLOCK_SIZE = 16;

  public static void main(String[] args) {
    final String prefix = "comment1=cooking%20MCs;userdata=";
    final String suffix = ";comment2=%20like%20a%20pound%20of%20bacon";
    CryptoScheme scheme = new SandwichCbcCryptoScheme(prefix.getBytes(), suffix.getBytes());

    byte[] hackedUserData = scheme.decrypt(attackAndFalsify(scheme));
    if (isAdmin(new String(hackedUserData))) {
      System.out.println("Successfully got admin access");
    } else {
      System.out.println("Hacking failed");
    }
  }

  public static byte[] attackAndFalsify(CryptoScheme scheme) {
    if (scheme == null) {
      throw new IllegalArgumentException("Scheme is required");
    }

    String attackString = "abcdefghijklmnop" + "?????:admin<true";
    int attackPos1 = 5 + BLOCK_SIZE * 2;
    int attackPos2 = 11 + BLOCK_SIZE * 2;

    byte[] origCipher = scheme.encrypt(attackString.getBytes());
    origCipher[attackPos1] ^= 1;
    origCipher[attackPos2] ^= 1;

    return origCipher;
  }

  private static boolean isAdmin(String userData) {
    return StringUtils.isNoneBlank(userData) || userData.contains(";admin=true;");
  }
}
