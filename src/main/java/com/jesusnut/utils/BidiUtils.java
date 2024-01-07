package com.jesusnut.utils;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v119.emulation.Emulation;
import org.openqa.selenium.devtools.v119.network.Network;
import org.openqa.selenium.devtools.v119.network.model.ConnectionType;
import org.openqa.selenium.remote.Augmenter;

import com.jesusnut.config.ConfigFactory;
import com.jesusnut.driver.TLDriverManager;
import com.jesusnut.enums.BrowserType;
import com.jesusnut.exception.FrameworkException;
import com.jesusnut.reports.extentreports.ExtentLogger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility Class having methods using BiDi and CDP protocol of Selenium 4.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BidiUtils {

	public static void getURLAfterBrowserAuth(String domainName, String desiredURL, String userName, String password) {

		if (!ConfigFactory.hasRemoteRunModeStatus()) {
			checkLocalDriverType();
			WebDriver driver = new Augmenter().augment(TLDriverManager.getDriver());
			Predicate<URI> uriPredicate = uri -> uri.getHost().contains(domainName);
			((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of(userName, password));
			driver.get(desiredURL);
			ExtentLogger.info("URL [" + desiredURL + "] launched successfully after Browser Level Authentication.");

		}

		else {

			AtomicReference<DevTools> devToolsAtomicReference = new AtomicReference<>();

			WebDriver driver = new Augmenter().addDriverAugmentation(checkRemoteDriverType(), HasAuthentication.class,
					(caps, exec) -> (whenThisMatches, useTheseCredentials) -> {
						devToolsAtomicReference.get().createSessionIfThereIsNotOne();
						devToolsAtomicReference.get().getDomains().network().addAuthHandler(whenThisMatches,
								useTheseCredentials);
					}).augment(TLDriverManager.getDriver());

			DevTools devTools = ((HasDevTools) driver).getDevTools();
			devTools.createSession();
			devToolsAtomicReference.set(devTools);
			((HasAuthentication) driver).register(UsernameAndPassword.of(userName, password));
			driver.get(desiredURL);
			ExtentLogger.info("URL [" + desiredURL + "] launched successfully after Browser Level Authentication.");
		}

	}

	public static void emulateMobileScreenResolution(int width, int height, Number deviceScaleFactor, boolean mobile) {

		if (!ConfigFactory.hasRemoteRunModeStatus()) {
			checkLocalDriverType();
			DevTools devTools = ((HasDevTools) TLDriverManager.getDriver()).getDevTools();
			devTools.createSession();
			devTools.send(Emulation.setDeviceMetricsOverride(width, height, deviceScaleFactor, mobile, Optional.empty(),
					Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
					Optional.empty(), Optional.empty(), Optional.empty()));
			ExtentLogger.info("Emulated Mobile Device with dimension " + width + "X" + height);
		}

		else {

			checkRemoteDriverType();
			WebDriver driver = new Augmenter().augment(TLDriverManager.getDriver());
			DevTools devTools = ((HasDevTools) driver).getDevTools();
			devTools.createSession();
			devTools.send(Emulation.setDeviceMetricsOverride(width, height, deviceScaleFactor, mobile, Optional.empty(),
					Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
					Optional.empty(), Optional.empty(), Optional.empty()));
			ExtentLogger.info("Emulated Mobile Device with dimension " + width + "X" + height);

		}

	}

	public static void emulateGeoLocation(Number latitude, Number longitude) {

		if (!ConfigFactory.hasRemoteRunModeStatus()) {
			checkLocalDriverType();
			DevTools devTools = ((ChromiumDriver) TLDriverManager.getDriver()).getDevTools();
			devTools.createSession();
			devTools.send(
					Emulation.setGeolocationOverride(Optional.of(latitude), Optional.of(longitude), Optional.of(1)));
			ExtentLogger
					.info("Emulated Geo Location at [Latitude,Longitude] as : [" + latitude + "," + longitude + "]");
		}

		else {
			checkRemoteDriverType();
			WebDriver driver = new Augmenter().augment(TLDriverManager.getDriver());
			DevTools devTools = ((HasDevTools) driver).getDevTools();
			devTools.createSession();
			devTools.send(
					Emulation.setGeolocationOverride(Optional.of(latitude), Optional.of(longitude), Optional.of(1)));
			ExtentLogger
					.info("Emulated Geo Location at [Latitude,Longitude] as : [" + latitude + "," + longitude + "]");
		}
	}

	public static void emulateCellular2GNetwork() {

		if (!ConfigFactory.hasRemoteRunModeStatus()) {
			checkLocalDriverType();
			DevTools devTools = ((ChromiumDriver) TLDriverManager.getDriver()).getDevTools();
			devTools.createSession();
			devTools.send(
					Network.emulateNetworkConditions(false, 150, 2500, 2000, Optional.of(ConnectionType.CELLULAR2G)));
			ExtentLogger.info("Emulated Cellular 2G Network");
		}

		else {
			checkRemoteDriverType();
			WebDriver driver = new Augmenter().augment(TLDriverManager.getDriver());
			DevTools devTools = ((HasDevTools) driver).getDevTools();
			devTools.createSession();
			devTools.send(
					Network.emulateNetworkConditions(false, 150, 2500, 2000, Optional.of(ConnectionType.CELLULAR2G)));
			ExtentLogger.info("Emulated Cellular 2G Network");
		}
	}

	public static void emulateCellular3GNetwork() {

		if (!ConfigFactory.hasRemoteRunModeStatus()) {
			checkLocalDriverType();
			DevTools devTools = ((ChromiumDriver) TLDriverManager.getDriver()).getDevTools();
			devTools.createSession();
			devTools.send(
					Network.emulateNetworkConditions(false, 150, 2500, 2000, Optional.of(ConnectionType.CELLULAR3G)));
			ExtentLogger.info("Emulated Cellular 3G Network");
		}

		else {
			checkRemoteDriverType();
			WebDriver driver = new Augmenter().augment(TLDriverManager.getDriver());
			DevTools devTools = ((HasDevTools) driver).getDevTools();
			devTools.createSession();
			devTools.send(
					Network.emulateNetworkConditions(false, 150, 2500, 2000, Optional.of(ConnectionType.CELLULAR3G)));
			ExtentLogger.info("Emulated Cellular 3G Network");
		}
	}

	public static void emulateCellular4GNetwork() {

		if (!ConfigFactory.hasRemoteRunModeStatus()) {
			checkLocalDriverType();
			DevTools devTools = ((ChromiumDriver) TLDriverManager.getDriver()).getDevTools();
			devTools.createSession();
			devTools.send(
					Network.emulateNetworkConditions(false, 150, 2500, 2000, Optional.of(ConnectionType.CELLULAR4G)));
			ExtentLogger.info("Emulated Cellular 4G Network");
		}

		else {
			checkRemoteDriverType();
			WebDriver driver = new Augmenter().augment(TLDriverManager.getDriver());
			DevTools devTools = ((HasDevTools) driver).getDevTools();
			devTools.createSession();
			devTools.send(
					Network.emulateNetworkConditions(false, 150, 2500, 2000, Optional.of(ConnectionType.CELLULAR4G)));
			ExtentLogger.info("Emulated Cellular 4G Network");
		}
	}

	public static void emulateOfflineNetwork() {

		if (!ConfigFactory.hasRemoteRunModeStatus()) {
			checkLocalDriverType();
			DevTools devTools = ((ChromiumDriver) TLDriverManager.getDriver()).getDevTools();
			devTools.createSession();
			devTools.send(Network.emulateNetworkConditions(true, 150, 2500, 2000, Optional.of(ConnectionType.WIFI)));
			ExtentLogger.info("Emulated Offline Network");
		}

		else {
			checkRemoteDriverType();
			WebDriver driver = new Augmenter().augment(TLDriverManager.getDriver());
			DevTools devTools = ((HasDevTools) driver).getDevTools();
			devTools.createSession();
			devTools.send(Network.emulateNetworkConditions(false, 150, 2500, 2000, Optional.of(ConnectionType.WIFI)));
			ExtentLogger.info("Emulated Offline Network");
		}
	}

	private static void checkLocalDriverType() {

		if (!(TLDriverManager.getDriver() instanceof ChromiumDriver))
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Bidi protocol supports only ChromiumDriver at this moment. Please use Chrome or Edge browsers only.");

	}

	private static String checkRemoteDriverType() {

		if (ConfigFactory.getBrowserFromConfig().equals(BrowserType.CHROME.getBrowserName())) {
			return "chrome";
		} else if (ConfigFactory.getBrowserFromConfig().equals(BrowserType.EDGE.getBrowserName())) {
			return "msedge";
		} else {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Bidi protocol supports only ChromiumDriver at this moment. Please use Chrome or Edge browsers only.");
		}
	}

}
