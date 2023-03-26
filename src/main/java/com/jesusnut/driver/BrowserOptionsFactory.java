package com.jesusnut.driver;

import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.AbstractDriverOptions;

import com.jesusnut.config.ConfigFactory;
import com.jesusnut.enums.BrowserType;
import com.jesusnut.exception.FrameworkException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * BrowserOptionsFactory class is responsible for providing browser options
 * (capabilities) to WebDriver instance for execution in both local or remote.
 * </p>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see LocalDriverFactory
 * @see RemoteDriverFactory
 */

//TODO Write custom function to set proxy as per requirement. All constants related to proxy should be placed in FrameworkConstants class.
//TODO Write custom function to add extensions/add-ons as per requirement. Put extension/add-ons file in src/main/resources.
//TODO Write custom functions to add capability to fetch HAR files from browsers as per requirement.

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BrowserOptionsFactory {

	private static final String ARG_DISABLE_GPU = "--disable-gpu";
	private static final String ARG_DISABLE_SANDBOX = "--no-sandbox";
	private static final String ARG_SET_WINDOW_SIZE_1920X1080 = "--window-size=1920,1080";
	private static final String BROWSERNAME = "browserName";
	private static final String CUSTOM_EXCEPTION_STRING = "JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] supports only 'firefox'/'edge'/'chrome' at the moment. Please enter valid Browser value";

	/**
	 * Provides browser options (capabilities) to WebDriver instance in
	 * {@link LocalDriverFactory} and {@link RemoteDriverFactory} <br>
	 * 
	 * @param browserName - name of browser on which test methods need to run
	 * @return driverOptions - browser options (capabilities)
	 * @throws FrameworkException if browserName is different from ones supported by
	 *                            framework
	 */
	static AbstractDriverOptions<?> getBrowserOptions(String browserName) {

		AbstractDriverOptions<?> driverOptions;

		if (browserName.trim().equalsIgnoreCase(BrowserType.FIREFOX.getBrowserName())) {

			driverOptions = new FirefoxOptions();
			((FirefoxOptions) driverOptions).setProfile(setFirefoxBasedBrowserPermissions());
			driverOptions.setCapability(BROWSERNAME, browserName);
			driverOptions.setAcceptInsecureCerts(true);
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
			checkAndGetHeadlessCapabilities(BrowserType.FIREFOX, driverOptions);
			checkAndGetIncognitoCapabilities(BrowserType.FIREFOX, driverOptions);
			((FirefoxOptions) driverOptions).setProfile(setFirefoxBasedBrowserPermissions());
		}

		else if (browserName.trim().equalsIgnoreCase(BrowserType.EDGE.getBrowserName())) {

			HashMap<String, Object> prefs = setChromiumBasedBrowserPermissions();

			driverOptions = new EdgeOptions();
			driverOptions.setCapability(BROWSERNAME, "MicrosoftEdge");
			((EdgeOptions) driverOptions).setExperimentalOption("excludeSwitches",
					Collections.singletonList("enable-automation"));
			((EdgeOptions) driverOptions).setExperimentalOption("prefs", prefs);

			checkAndGetHeadlessCapabilities(BrowserType.EDGE, driverOptions);
			checkAndGetIncognitoCapabilities(BrowserType.EDGE, driverOptions);

		}

		else if (browserName.trim().equalsIgnoreCase(BrowserType.CHROME.getBrowserName())) {

			HashMap<String, Object> prefs = setChromiumBasedBrowserPermissions();

			driverOptions = new ChromeOptions();

			driverOptions.setCapability(BROWSERNAME, browserName);
			driverOptions.setCapability("se:recordVideo", true);
			((ChromeOptions) driverOptions).addArguments("--remote-allow-origins=*");
			((ChromeOptions) driverOptions).setExperimentalOption("excludeSwitches",
					Collections.singletonList("enable-automation"));
			((ChromeOptions) driverOptions).setAcceptInsecureCerts(true);
			((ChromeOptions) driverOptions).setExperimentalOption("prefs", prefs);
			checkAndGetHeadlessCapabilities(BrowserType.CHROME, driverOptions);
			checkAndGetIncognitoCapabilities(BrowserType.CHROME, driverOptions);
		}

		else {

			throw new FrameworkException(CUSTOM_EXCEPTION_STRING);
		}

		return driverOptions;

	}

	/**
	 * Sets Chromium Browser based permissions for 'Notifications','Location','Mic
	 * Access','Camera Access' to 'always ask'(0),'allow'(1),'block'(2) <br>
	 * and returns this capability to
	 * {@link BrowserOptionsFactory#getBrowserOptions(String)}.
	 * 
	 * @return HashMap of set preferences.
	 */

	private static HashMap<String, Object> setChromiumBasedBrowserPermissions() {
		HashMap<String, Integer> contentSettings = new HashMap<>();
		HashMap<String, Object> profile = new HashMap<>();
		HashMap<String, Object> prefs = new HashMap<>();
		contentSettings.put("notifications", 1);
		contentSettings.put("geolocation", 1);
		contentSettings.put("media_stream", 1);
		profile.put("managed_default_content_settings", contentSettings);
		prefs.put("profile", profile);
		return prefs;
	}

	/**
	 * Sets Firefox Browser based permissions for 'Notifications','Location','Mic
	 * Access','Camera Access' to 'always ask'(0),'allow'(1),'block'(2) <br>
	 * and returns this capability to
	 * {@link BrowserOptionsFactory#getBrowserOptions(String)} via a new profile.
	 * 
	 * @return HashMap of set preferences.
	 */

	private static FirefoxProfile setFirefoxBasedBrowserPermissions() {

		FirefoxProfile prof = new FirefoxProfile();
		prof.setPreference("permissions.default.microphone", 1);
		prof.setPreference("permissions.default.camera", 1);
		prof.setPreference("permissions.default.geo", 1);

		return prof;

	}

	/**
	 * Checks and provides capabilities required to run browsers in headless mode to
	 * {@link BrowserOptionsFactory#getBrowserOptions(String)}
	 * 
	 * @param browserType   - name of browser on which test methods need to run
	 * @param driverOptions - capabilities of the browser
	 * @throws FrameworkException if browserName is different from ones supported by
	 *                            framework
	 * @see com.jesusnut.config.ConfigFactory#hasHeadlessRunModeStatus()
	 */

	private static void checkAndGetHeadlessCapabilities(BrowserType browserType,
			AbstractDriverOptions<?> driverOptions) {

		if (ConfigFactory.hasHeadlessRunModeStatus()) {

			if (("firefox").equalsIgnoreCase(browserType.getBrowserName())) {

				((FirefoxOptions) driverOptions).setHeadless(true);
				((FirefoxOptions) driverOptions).addArguments(ARG_SET_WINDOW_SIZE_1920X1080);
				((FirefoxOptions) driverOptions).addArguments(ARG_DISABLE_SANDBOX);
				((FirefoxOptions) driverOptions).addArguments(ARG_DISABLE_GPU);

			} else if (("edge").equalsIgnoreCase(browserType.getBrowserName())) {

				((EdgeOptions) driverOptions).setHeadless(true);
				((EdgeOptions) driverOptions).addArguments(ARG_SET_WINDOW_SIZE_1920X1080);
				((EdgeOptions) driverOptions).addArguments(ARG_DISABLE_SANDBOX);
				((EdgeOptions) driverOptions).addArguments(ARG_DISABLE_GPU);

			} else if (("chrome").equalsIgnoreCase(browserType.getBrowserName())) {

				((ChromeOptions) driverOptions).setHeadless(true);
				((ChromeOptions) driverOptions).addArguments(ARG_SET_WINDOW_SIZE_1920X1080);
				((ChromeOptions) driverOptions).addArguments(ARG_DISABLE_SANDBOX);
				((ChromeOptions) driverOptions).addArguments(ARG_DISABLE_GPU);

			} else

			{
				throw new FrameworkException(CUSTOM_EXCEPTION_STRING);

			}
		}

	}

	/**
	 * Checks and provides capabilities required to run browsers in incognito mode
	 * to {@link BrowserOptionsFactory#getBrowserOptions(String)}.
	 * 
	 * @param browserType   - name of browser on which test methods need to run
	 * @param driverOptions - capabilities of the browser
	 * @throws FrameworkException if browserName is different from ones supported by
	 *                            framework
	 * @see com.jesusnut.config.ConfigFactory#hasIncognitoRunModeStatus()
	 */

	private static void checkAndGetIncognitoCapabilities(BrowserType browserType,
			AbstractDriverOptions<?> driverOptions) {

		if (ConfigFactory.hasIncognitoRunModeStatus()) {

			if (("firefox").equalsIgnoreCase(browserType.getBrowserName())) {
				((FirefoxOptions) driverOptions).addArguments("-private");
			} else if (("edge").equalsIgnoreCase(browserType.getBrowserName())) {
				((EdgeOptions) driverOptions).addArguments("-inprivate");
			} else if (("chrome").equalsIgnoreCase(browserType.getBrowserName())) {
				((ChromeOptions) driverOptions).addArguments("--incognito");
			} else {
				throw new FrameworkException(CUSTOM_EXCEPTION_STRING);
			}
		}

	}

}
