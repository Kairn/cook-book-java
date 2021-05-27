package io.esoma.cbj.crypto.stream;

import java.util.Arrays;

/**
 * Class that implements the {@link CounterGenerator} by enumerating integers
 * with an incrementing step of 1. Each integer is returned as an 8-byte array
 * in little-endian mode. An exception will be thrown if the counter >=
 * {@link Long#MAX_VALUE}.
 * 
 * @author Eddy Soma
 *
 */
public class LeIntegerCounterGenerator implements CounterGenerator {

	private static final byte BYTE_CAP = (byte) 0xFF;

	private long counter = 0L;
	private byte[] counterBytes = new byte[8];

	@Override
	public void reset() {
		counter = 0L;
		counterBytes = new byte[8];
	}

	@Override
	public byte[] getNext() {
		if (counter >= Long.MAX_VALUE) {
			throw new IllegalStateException("Maximum counter value exceeded");
		}
		try {
			return Arrays.copyOf(counterBytes, 8);
		} finally {
			counter++;
			incrementBytes(0);
		}
	}

	@Override
	public byte[] peekNext() {
		return counterBytes;
	}

	private void incrementBytes(int position) {
		if (counterBytes[position] == BYTE_CAP) {
			counterBytes[position] = 0x00;
			incrementBytes(position + 1);
		} else {
			counterBytes[position]++;
		}
	}

}
