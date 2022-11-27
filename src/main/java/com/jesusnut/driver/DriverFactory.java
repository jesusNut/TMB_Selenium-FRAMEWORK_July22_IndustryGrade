package com.jesusnut.driver;

import org.openqa.selenium.WebDriver;

import com.jesusnut.config.ConfigFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * DriverFactory class is responsible for returning WebDriver instance for
 * execution in either local or remote.
 * </p>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see LocalDriverFactory
 * @see RemoteDriverFactory
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DriverFactory {

	/**
	 * Checks and returns WebDriver instance for execution in local or remote.
	 * 
	 * @param browsername - name of browser on which test methods need to run
	 * @return WebDriver instance for execution in either local or remote
	 * @see com.jesusnut.config.ConfigFactory#hasRemoteRunModeStatus()
	 */

	public static WebDriver getDriver(String browsername) {

		if (!ConfigFactory.hasRemoteRunModeStatus()) {

			return LocalDriverFactory.getLocalDriver(browsername);

		}

		else {

			return RemoteDriverFactory.getRemoteDriver(browsername);

		}

	}

}
