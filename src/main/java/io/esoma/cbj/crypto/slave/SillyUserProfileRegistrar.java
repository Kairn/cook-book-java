package io.esoma.cbj.crypto.slave;

import io.esoma.cbj.crypto.basic.AesInEcb;
import org.apache.commons.lang3.StringUtils;

/**
 * Class that simulates an insecure user profile registration/login system that encrypts information
 * using AES-128 in ECB mode under a fixed key.
 *
 * @author Eddy Soma
 */
public class SillyUserProfileRegistrar {

    private static final String AES_KEY = "SALMON SUBMARINE";

    /**
     * Creates a standard user profile with a "USER" role for the given email address. "&" and "="
     * characters are not allowed.
     *
     * @param email the registration email
     * @return an array of bytes representing the credentials
     */
    public byte[] register(String email) {
        if (StringUtils.isBlank(email) || email.indexOf('&') > -1 || email.indexOf('=') > -1) {
            throw new IllegalArgumentException("Invalid email");
        }

        SimpleUserProfile profile = new SimpleUserProfile(email);
        return AesInEcb.encrypt(profile.toString().getBytes(), AES_KEY, true);
    }

    /**
     * Decrypts and decodes the credentials and then retrieves the user profile behind it.
     *
     * @param credentials the credentials bytes
     * @return the decoded user profile
     */
    public SimpleUserProfile login(byte[] credentials) {
        if (credentials == null || credentials.length < 1) {
            throw new IllegalArgumentException("No credentials provided");
        }

        try {
            byte[] decrypted = AesInEcb.decrypt(credentials, AES_KEY, true);
            String profileData = new String(decrypted);
            return SimpleUserProfile.parse(profileData);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid credentials");
        }
    }
}
