package com.jesusnut.helper;

import com.jesusnut.config.ConfigFactory;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.enums.BrowserType;
import com.jesusnut.exception.FrameworkException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * IconHelper class helps to fetch the Browser Icons and Operating System Icons.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.constants.FrameworkConstants
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IconHelper {
	/**
	 * Returns Font Awesome Icons for the browser specified in @link
	 * {@link com.jesusnut.config.ConfigFactory#getBrowserFromConfig()}
	 * 
	 * @return Font Awesome Icons for the browser in String format.
	 * @throws FrameworkException if the browser value is not supported by
	 *                            Framework.
	 */

	public static String getBrowserIcon() {
		if (ConfigFactory.getBrowserFromConfig().equalsIgnoreCase(BrowserType.CHROME.getBrowserName())) {
			return FrameworkConstants.ICON_BROWSER_CHROME;
		} else if (ConfigFactory.getBrowserFromConfig().equalsIgnoreCase(BrowserType.EDGE.getBrowserName())) {
			return FrameworkConstants.ICON_BROWSER_EDGE;
		} else if (ConfigFactory.getBrowserFromConfig().equalsIgnoreCase(BrowserType.FIREFOX.getBrowserName())) {
			return FrameworkConstants.ICON_BROWSER_FIREFOX;
		} else {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] supports only 'firefox'/'edge'/'chrome' at the moment. Please enter valid Browser value");
		}
	}

	/**
	 * Returns Font Awesome Icons for the Operating System.
	 * 
	 * @return Font Awesome Icons for the Operating System in String format.
	 */

	public static String getOSIcon() {

		String operationSystemIcon;
		String operationSystem = System.getProperty("os.name");

		if (operationSystem.toLowerCase().contains("win")) {
			operationSystemIcon = FrameworkConstants.ICON_OS_WINDOWS;
		} else if (operationSystem.toLowerCase().contains("nix") || operationSystem.contains("nux")
				|| operationSystem.contains("aix")) {
			operationSystemIcon = FrameworkConstants.ICON_OS_LINUX;
		} else if (operationSystem.toLowerCase().contains("mac")) {
			operationSystemIcon = FrameworkConstants.ICON_OS_MAC;
		}

		else {
			operationSystemIcon = FrameworkConstants.ICON_OS_UNIDENTIFIED;

		}

		return operationSystemIcon;
	}

}
