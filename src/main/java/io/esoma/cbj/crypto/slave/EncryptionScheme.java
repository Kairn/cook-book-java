package io.esoma.cbj.crypto.slave;

/**
 * A function interface that describes a particular encryption scheme which will be able to encrypt
 * data buffers without requiring any additional input.
 *
 * @author Eddy Soma
 */
@FunctionalInterface
public interface EncryptionScheme {

    byte[] encrypt(byte[] buffer);
}
