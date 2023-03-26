package com.jesusnut.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.jesusnut.enums.BrowserType;
import com.jesusnut.exception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * LocalDriverFactory class is responsible for returning WebDriver instance for
 * execution in local.
 * </p>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see BrowserOptionsFactory
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocalDriverFactory {

	/**
	 * Returns WebDriver instance loaded with browser options (from
	 * {@link BrowserOptionsFactory}) based on browserName; for execution in local.
	 * <br>
	 * Sets browser driver executable using {@link WebDriverManager}.<br>
	 * 
	 * 
	 * @param browserName - name of browser on which test methods need to run
	 * @return WebDriver instance for execution in local loaded with Browser
	 *         Options.
	 * @throws FrameworkException if browserName is different from ones supported by
	 *                            framework
	 */

	static WebDriver getLocalDriver(String browserName) {

		WebDriver driver;

		if (browserName.trim().equalsIgnoreCase(BrowserType.FIREFOX.getBrowserName())) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver((FirefoxOptions) BrowserOptionsFactory.getBrowserOptions(browserName));

		}

		else if (browserName.trim().equalsIgnoreCase(BrowserType.EDGE.getBrowserName())) {

			WebDriverManager.edgedriver().setup();

			driver = new EdgeDriver((EdgeOptions) BrowserOptionsFactory.getBrowserOptions(browserName));
		}

		else if (browserName.trim().equalsIgnoreCase(BrowserType.CHROME.getBrowserName())) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver((ChromeOptions) BrowserOptionsFactory.getBrowserOptions(browserName));

		}

		else {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] supports only 'firefox'/'edge'/'chrome' at the moment. Please enter valid Browser value");
		}

		return driver;

	}

}
