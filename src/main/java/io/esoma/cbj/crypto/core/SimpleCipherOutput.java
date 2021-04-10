package io.esoma.cbj.crypto.core;

/**
 * A data structure for storing relevant information of a single cipher case.
 * 
 * @author Eddy Soma
 *
 */
public class SimpleCipherOutput<T> {

	private String encrypted;
	private String decrypted;
	private T key;

	public SimpleCipherOutput() {
	}

	public SimpleCipherOutput(String encrypted, String decrypted, T key) {
		this.encrypted = encrypted;
		this.decrypted = decrypted;
		this.key = key;
	}

	public String getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}

	public String getDecrypted() {
		return decrypted;
	}

	public void setDecrypted(String decrypted) {
		this.decrypted = decrypted;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "SimpleCipherOutput [encrypted=" + encrypted + ", decrypted=" + decrypted + ", key=" + key + "]";
	}
}
