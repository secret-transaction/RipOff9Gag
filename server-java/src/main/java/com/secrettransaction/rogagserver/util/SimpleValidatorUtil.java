package com.secrettransaction.rogagserver.util;

public class SimpleValidatorUtil {

	public static boolean validEmail(String email) {
		return email.matches("[A-Za-z0-9._%+-][A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{3}");
	}
}
