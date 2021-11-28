package io.esoma.cbj.crypto.stream;

/**
 * Interface that provides the abilities to generate consecutive bytes to be used for AES encryption
 * or decryption in CTR mode.
 *
 * @author Eddy Soma
 */
public interface CounterGenerator {

  void reset();

  byte[] getNext();

  byte[] peekNext();
}
