package io.esoma.cbj.crypto.block;

import io.esoma.cbj.crypto.core.ByteMemory;
import io.esoma.cbj.crypto.slave.EncryptionScheme;
import io.esoma.cbj.crypto.slave.SillyEcbEncryptionScheme;
import org.apache.commons.lang3.ArrayUtils;
import org.tinylog.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for implementing an attack function that evaluates the outputs of a particular ECB
 * encryption scheme and uncovers the secret unknown text appended by the scheme.
 *
 * @author Eddy Soma
 */
public class EcbOracleAttack {

  private static final int BLOCK_SIZE = 16;

  public static void main(String[] args) {
    String unknownText = attackAndExtractV2(new SillyEcbEncryptionScheme());
    Logger.info("Uncovered unknown text:\n" + unknownText);
  }

  public static String attackAndExtractV2(EncryptionScheme scheme) {
    if (scheme == null) {
      throw new IllegalArgumentException("Scheme is required");
    }

    int len = scheme.encrypt(new byte[0]).length;
    byte[] knowledge = new byte[len];
    int cursor = 0;
    int attackPos = BLOCK_SIZE - 1;

    while (cursor < len) {
      int start = Math.max(0, cursor - (BLOCK_SIZE - 1));
      byte[] attackBlock = Arrays.copyOfRange(knowledge, start, cursor + 1);
      if (attackBlock.length < BLOCK_SIZE) {
        int missing = BLOCK_SIZE - attackBlock.length;
        attackBlock = ArrayUtils.addAll(new byte[missing], attackBlock);
      }

      Map<ByteMemory, Byte> table = new HashMap<>();
      // Iterate bytes and map out the results.
      for (int b = 0; b <= Byte.MAX_VALUE; ++b) {
        attackBlock[attackPos] = (byte) b;
        byte[] output = scheme.encrypt(attackBlock);
        // Remember only the last three bytes to save memory.
        table.put(
            new ByteMemory(output[attackPos], output[attackPos - 1], output[attackPos - 2]),
            (byte) b);
      }

      int pushOffset = (BLOCK_SIZE - 1) - (cursor % BLOCK_SIZE);
      byte[] pushBlock = new byte[pushOffset];

      int hackPos = cursor + pushOffset;
      byte[] oracleBlock = scheme.encrypt(pushBlock);
      ByteMemory footPrint =
          new ByteMemory(oracleBlock[hackPos], oracleBlock[hackPos - 1], oracleBlock[hackPos - 2]);
      byte gist = table.get(footPrint);
      if (gist == 1) {
        // Reached padding bits.
        break;
      } else {
        knowledge[cursor] = gist;
      }

      ++cursor;
    }

    return new String(Arrays.copyOfRange(knowledge, 0, cursor));
  }
}
