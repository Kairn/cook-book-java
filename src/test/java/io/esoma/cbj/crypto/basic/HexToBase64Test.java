package io.esoma.cbj.crypto.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class HexToBase64Test {

    @Test
    void testPerform() {
        final String hexInput =
                "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        final String expected = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";
        String actual = HexToBase64.perform(hexInput);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }
}
