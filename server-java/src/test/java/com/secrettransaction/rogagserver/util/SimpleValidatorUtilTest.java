package com.secrettransaction.rogagserver.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimpleValidatorUtilTest {

	@Test
	public void validEmailShouldReturnTrueWhenEmailIsValid() {
		//TODO: improve validator, this one is just temporary
		assertTrue(SimpleValidatorUtil.validEmail("test@test.com"));
		assertTrue(SimpleValidatorUtil.validEmail("test@test.edu"));
	}
	
	@Test
	public void validEmailShouldReturnFalseWhenEmailIsInvalid() {
		//TODO: improve validator, this one is just temporary
		assertFalse(SimpleValidatorUtil.validEmail("waaaaaaaa"));
		assertFalse(SimpleValidatorUtil.validEmail("lol.com"));
	}
}
