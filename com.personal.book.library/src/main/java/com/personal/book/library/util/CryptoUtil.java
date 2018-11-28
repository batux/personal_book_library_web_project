package com.personal.book.library.util;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base32;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptoUtil {

	public static String cryptPassword(String password) {
		
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean checkPassword(String passwordFromDb, String passwordFromClient) {
		return BCrypt.checkpw(passwordFromDb, passwordFromClient);
	}
	
	public static String generateOtpSecret() {
		
        byte [] buffer = new byte[10];
        new SecureRandom().nextBytes(buffer);
        return new String(new Base32().encode(buffer));
    }
}
