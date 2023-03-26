package com.jesusnut.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.jesusnut.config.ConfigFactory;
import com.jesusnut.exception.FrameworkException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * RemoteDriverFactory class is responsible for returning WebDriver
 * (RemoteWebDriver) instance for execution in remote.
 * </p>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see BrowserOptionsFactory
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RemoteDriverFactory {

	/**
	 * Returns WebDriver (RemoteWebDriver) instance loaded with browser options
	 * (from {@link BrowserOptionsFactory}) based on browserName; and remote URL for
	 * execution in remote. <br>
	 * 
	 * 
	 * 
	 * @param browserName - name of browser on which test methods need to run
	 * @return WebDriver (RemoteWebDriver) instance for execution in remote loaded
	 *         with Browser Options.
	 * @throws FrameworkException denotes issue while returning instance of
	 *                            RemoteWebDriver
	 */

	static WebDriver getRemoteDriver(String browserName) {

		try {

			return new RemoteWebDriver(new URL(ConfigFactory.getRemoteURL()),
					BrowserOptionsFactory.getBrowserOptions(browserName));

		} catch (MalformedURLException | SessionNotCreatedException e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>>  Probable cause >>> \n 1. Remote URL{[remoteURL] key through System properties/System environment/Config.properties files} is a malformed URL/invalid address of remote server. \n 2. Issue with capabilities",
					e);

		}

	}

}
