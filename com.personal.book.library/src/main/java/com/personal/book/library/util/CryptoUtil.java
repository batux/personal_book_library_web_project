package com.personal.book.library.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptoUtil {

	public static String cryptPassword(String password) {
		
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean checkPassword(String passwordFromDb, String passwordFromClient) {
		return BCrypt.checkpw(passwordFromDb, passwordFromClient);
	}
}
