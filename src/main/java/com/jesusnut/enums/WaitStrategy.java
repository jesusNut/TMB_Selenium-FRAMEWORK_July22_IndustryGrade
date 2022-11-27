package com.jesusnut.enums;

import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Enums to restrict the Wait Strategies supported by this framework.<br>
 * 
 * <pre>
 * Wait Strategies define conditions for which WebDriver waits to be met before
 * operating on any WebElements/Non-WebElements.{@link WebDriverWait}
 * </pre>
 * 
 * <pre>
 * These conditions are generally related to WebElements/Non-WebElements on
 * which WebDriver operates.
 * </pre>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.utils.Ignitor
 */

public enum WaitStrategy {

	PRESENCE, VISIBLE

}
