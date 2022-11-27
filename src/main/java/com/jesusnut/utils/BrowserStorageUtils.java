package com.jesusnut.utils;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;

import com.jesusnut.driver.TLDriverManager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility Class for operations on HTML5 local storage, HTML5 Session Storage
 * and Cookies.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BrowserStorageUtils {

	// ********** Utility methods for HTML5 Local Storage************

	public static String getLocalStorageItem(String key) {
		LocalStorage localStorage = ((WebStorage) TLDriverManager.getDriver()).getLocalStorage();
		return localStorage.getItem(key);
	}

	public static void setLocalStorageItem(String key, String value) {
		LocalStorage localStorage = ((WebStorage) TLDriverManager.getDriver()).getLocalStorage();
		localStorage.setItem(key, value);
	}

	public static void removeLocalStorageItem(String key) {
		LocalStorage localStorage = ((WebStorage) TLDriverManager.getDriver()).getLocalStorage();
		localStorage.removeItem(key);
	}

	public static void clearLocalStorage() {
		LocalStorage localStorage = ((WebStorage) TLDriverManager.getDriver()).getLocalStorage();
		localStorage.clear();
	}

	public static int sizeLocalStorage() {
		LocalStorage localStorage = ((WebStorage) TLDriverManager.getDriver()).getLocalStorage();
		return localStorage.size();
	}

	// ********** Utility methods for HTML5 Session Storage************

	public static String getSessionStorageItem(String key) {
		SessionStorage sessionStorage = ((WebStorage) TLDriverManager.getDriver()).getSessionStorage();
		return sessionStorage.getItem(key);
	}

	public static void setSessionStorageItem(String key, String value) {
		SessionStorage sessionStorage = ((WebStorage) TLDriverManager.getDriver()).getSessionStorage();
		sessionStorage.setItem(key, value);
	}

	public static void removeSessionStorageItem(String key) {
		SessionStorage sessionStorage = ((WebStorage) TLDriverManager.getDriver()).getSessionStorage();
		sessionStorage.removeItem(key);
	}

	public static void clearSessionStorage() {
		SessionStorage sessionStorage = ((WebStorage) TLDriverManager.getDriver()).getSessionStorage();
		sessionStorage.clear();
	}

	public static int sizeSessionStorage() {
		SessionStorage sessionStorage = ((WebStorage) TLDriverManager.getDriver()).getSessionStorage();
		return sessionStorage.size();
	}

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
