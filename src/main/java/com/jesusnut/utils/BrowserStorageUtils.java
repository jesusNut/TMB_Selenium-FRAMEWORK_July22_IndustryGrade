package com.jesusnut.utils;

import java.util.Set;

import org.openqa.selenium.Cookie;

import com.jesusnut.driver.TLDriverManager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility Class on Cookies.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BrowserStorageUtils {

	// ********** Utility methods for handling Cookies ************

	public static void deleteAllCookies() {
		TLDriverManager.getDriver().manage().deleteAllCookies();
	}

	public static void deleteCookieNamed(String cookieName) {
		TLDriverManager.getDriver().manage().deleteCookieNamed(cookieName);
	}

	public static void deleteCookie(Cookie cookie) {
		TLDriverManager.getDriver().manage().deleteCookie(cookie);
	}

	public static void addCookie(Cookie cookie) {
		TLDriverManager.getDriver().manage().addCookie(cookie);
	}

	public static Set<Cookie> getAllCookies() {

		return TLDriverManager.getDriver().manage().getCookies();

	}

	public static Cookie getCookieNamed(String name) {

		return TLDriverManager.getDriver().manage().getCookieNamed(name);

	}

}
