package io.esoma.cbj.crypto.core;

import java.util.Arrays;

/**
 * A simple data structure for storing multiple bytes which can be used more extensively than native
 * arrays.
 *
 * @author Eddy Soma
 */
public class ByteMemory {

  private final byte[] mem;

  public ByteMemory(byte... bytes) {
    this.mem = bytes;
  }

  public int length() {
    return mem.length;
  }

  public byte[] getMem() {
    return mem;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(mem);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ByteMemory other = (ByteMemory) obj;
    return Arrays.equals(mem, other.mem);
  }
}
