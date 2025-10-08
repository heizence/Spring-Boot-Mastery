package com.example.urlShortener.util;

import org.apache.commons.codec.binary.Base64;
import java.nio.ByteBuffer;

public class Base62Util {

    // A custom alphabet for Base62 encoding (0-9, A-Z, a-z).
    private static final byte[] BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".getBytes();

    /**
     * Encodes a long value into a Base62 string.
     * @param value The long value to encode (e.g., a database ID).
     * @return The Base62 encoded string.
     */
    public static String encode(long value) {
        // Convert the long to a byte array.
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(value);
        byte[] longBytes = buffer.array();

        // Use Apache Commons Codec's Base64 with our custom alphabet.
        // This effectively makes it a Base62 encoder.
        Base64 base62Encoder = new Base64();
        String encoded = base62Encoder.encodeToString(longBytes);

        // Remove any leading '0's that are just padding.
        // For example, encode(1) might result in "00000001" -> "1".
        if (encoded.startsWith("0") && encoded.length() > 1) {
            return encoded.replaceAll("^0+", "");
        }

        return encoded;
    }
}