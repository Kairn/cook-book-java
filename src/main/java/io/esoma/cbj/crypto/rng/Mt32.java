package io.esoma.cbj.crypto.rng;

/**
 * Class for implementing the famous Mersenne Twister pseudo random number generator. Parameters are
 * specified by MT19937 (32-bit).
 *
 * @author Eddy Soma
 * @see <a href="https://en.wikipedia.org/wiki/Mersenne_Twister">...</a>
 */
public class Mt32 {

  // Algorithm parameters.
  private static final int W = 32;
  private static final int N = 624;
  private static final int M = 397;
  private static final int A = 0x9908B0DF;
  private static final int U = 11;
  private static final int D = 0xFFFFFFFF;
  private static final int S = 7;
  private static final int B = 0x9D2C5680;
  private static final int T = 15;
  private static final int C = 0xEFC60000;
  private static final int L = 18;
  private static final int UPPER_MASK = 0x80000000;
  private static final int LOWER_MASK = 0x7FFFFFFF;
  private static final long F = 1812433253L;
  private static final long INT_MASK = 0xFFFFFFFFL;

  private final int[] mt = new int[N];
  private int mti;

  public Mt32() {
    this(5489);
  }

  public Mt32(int seed) {
    setSeed(seed);
  }

  public Mt32(int[] seed) {
    setSeed(seed);
  }

  public void setSeed(int seed) {
    long longMt = seed;
    mt[0] = (int) longMt;
    for (mti = 1; mti < N; ++mti) {
      longMt = F * (longMt ^ (longMt >> (W - 2))) + mti;
      mt[mti] = applyUpperMask(longMt);
    }
  }

  public void setSeed(int[] seed) {
    if (seed == null || seed.length < 1) {
      setSeed(5489);
      return;
    }

    setSeed(19650218);

    int i = 1;
    int j = 0;
    int k = Math.max(N, seed.length);

    for (; k > 0; --k) {
      long m0 = toUnsignedInt(mt[i]);
      long m1 = toUnsignedInt(mt[i - 1]);
      long longM = (m0 ^ ((m1 ^ (m1 >> 30)) * 1664525L)) + seed[j] + j;
      mt[i] = applyUpperMask(longM);
      i++;
      j++;
      if (i >= N) {
        mt[0] = mt[N - 1];
        i = 1;
      }
      if (j >= seed.length) {
        j = 0;
      }
    }

    for (k = N - 1; k > 0; k--) {
      long m0 = toUnsignedInt(mt[i]);
      long m1 = toUnsignedInt(mt[i - 1]);
      long longM = (m0 ^ ((m1 ^ (m1 >> 30)) * 1566083941L)) - i;
      mt[i] = applyUpperMask(longM);
      i++;
      if (i >= N) {
        mt[0] = mt[N - 1];
        i = 1;
      }
    }

    mt[0] = 0x80000000;
  }

  public int next() {
    int y;

    if (mti >= N) {
      twist();
    }

    y = mt[mti++];
    y ^= (y >>> U) & D;
    y ^= (y << S) & B;
    y ^= (y << T) & C;
    y ^= y >>> L;

    return y;
  }

  private void twist() {
    int y;
    int yA;

    for (int k = 0; k < N - 1; ++k) {
      int mtCur = mt[k];
      int mtNext = mt[(k + 1) % N];
      y = (mtCur & UPPER_MASK) | (mtNext & LOWER_MASK);
      yA = y >>> 1;
      if (y % 2 != 0) {
        yA ^= A;
      }
      mt[k] = mt[(k + M) % N] ^ yA;
    }

    mti = 0;
  }

  private int applyUpperMask(long value) {
    return (int) (value & INT_MASK);
  }

  private long toUnsignedInt(int value) {
    return (value & 0x7FFFFFFFL) | ((value < 0) ? 0x80000000L : 0x0L);
  }
}
