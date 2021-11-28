package io.esoma.cbj.crypto.slave;

/**
 * Interface that describes a cryptographic scheme which supports symmetric encryption and
 * decryption of byte streams.
 *
 * @author Eddy Soma
 */
public interface CryptoScheme extends EncryptionScheme {

  byte[] decrypt(byte[] buffer);
}
