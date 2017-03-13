package pl.com.redpike.bankred.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Redpike
 */
public class PasswordProvider {

    public static String hashPassword(String plainPassword) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(plainPassword.getBytes());

        byte byteData[] = messageDigest.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte data : byteData) {
            String hex = Integer.toHexString(0xff & data);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
