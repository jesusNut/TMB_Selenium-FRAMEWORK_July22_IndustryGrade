package com.jesusnut.utils;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Ulility Class having methods to help create unique Alphabetic strings,
 * Numeric strings and AlphaNumeric strings.<br>
 * This class can also be used in conjunction with Java-Faker library.
 * 
 * @see <a href=
 *      "https://github.com/anhtester/AutomationFrameworkSelenium/blob/main/src/main/java/anhtester/com/utils/DataGenerateUtils.java">AnhTester's
 *      fakeData_Class</a>
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FakeDataUtils {

	private static final String LOCALE = "en-IND";

	public static String get32CharUniqueAlphaNumericString() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String get16CharUniqueAlphaNumericString() {

		FakeValuesService fake = new FakeValuesService(new Locale(LOCALE),
				new RandomService(new Random(System.nanoTime())));

		return fake.expression(
				"#{letterify '????','true'}#{letterify '????','false'}#{numerify '####'}#{numerify '####'}",
				new Faker());

	}

	public static String get8CharUniqueAlphaNumericString() {

		FakeValuesService fake = new FakeValuesService(new Locale(LOCALE),
				new RandomService(new Random(System.nanoTime())));

		return fake.expression("#{letterify '??','false'}#{numerify '##'}#{letterify '??','true'}#{numerify '##'}",
				new Faker());

	}

	public static String getUniqueAlphaNumericStringWithSplChar() {

		FakeValuesService fake = new FakeValuesService(new Locale(LOCALE),
				new RandomService(new Random(System.nanoTime())));

		return fake.expression(
				"#{letterify '??','false'}#{regexify '[^A-Za-z0-9]'}#{numerify '##'}#{regexify '[^A-Za-z0-9]'}#{letterify '??','true'}#{numerify '##'}#{regexify '[^A-Za-z0-9]'}",
				new Faker());

	}

	public static String buildUniqueText(String prefix, String suffix) {

		FakeValuesService fake = new FakeValuesService(new Locale(LOCALE),
				new RandomService(new Random(System.nanoTime())));

		String randomData = fake.expression("#{letterify '?????'}", new Faker());

		return prefix.trim() + randomData + suffix.trim();
	}

	public static String buildUniqueTextWithSpecialChar(String prefix, String suffix) {

		FakeValuesService fake = new FakeValuesService(new Locale(LOCALE),
				new RandomService(new Random(System.nanoTime())));

		String randomData = fake.expression(
				"#{letterify '???','false'}#{regexify '[^A-Za-z0-9]'}#{letterify '???','true'}#{regexify '[^A-Za-z0-9]'}",
				new Faker());

		return prefix.trim() + randomData + suffix.trim();
	}

	public static String buildUniqueNumericText(String prefix, String suffix) {

		FakeValuesService fake = new FakeValuesService(new Locale(LOCALE),
				new RandomService(new Random(System.nanoTime())));

		String randomData = fake.expression("#{numerify '#####'}", new Faker());

		return prefix.trim() + randomData + suffix.trim();

	}

	public static String buildUniqueAlphaNumericText(String prefix, String suffix) {

		FakeValuesService fake = new FakeValuesService(new Locale(LOCALE),
				new RandomService(new Random(System.nanoTime())));

		String randomData = fake.expression(
				"#{letterify '??','false'}#{numerify '#'}#{letterify '??','true'}#{numerify '###'}", new Faker());

		return prefix.trim() + randomData + suffix.trim();

	}

	public static String buildUniqueAlphaNumericTextWithSplChar(String prefix, String suffix) {

		FakeValuesService fake = new FakeValuesService(new Locale(LOCALE),
				new RandomService(new Random(System.nanoTime())));

		String randomData = fake.expression(
				"#{letterify '??','true'}#{regexify '[^A-Za-z0-9]'}#{numerify '#'}#{letterify '??','false'}#{regexify '[^A-Za-z0-9]'}",
				new Faker());

		return prefix.trim() + randomData + suffix.trim();

	}

}
