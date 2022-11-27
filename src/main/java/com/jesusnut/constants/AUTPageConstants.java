package com.jesusnut.constants;

import java.util.Arrays;
import java.util.List;

import lombok.experimental.UtilityClass;

/**
 * AUTPageConstants class contains constants regarding to the AUT pages.<br>
 * Constants in AUTPageConstants class <b>serves as expected values for
 * assertions while writing test methods for respective AUT pages</b>.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@UtilityClass
public final class AUTPageConstants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL = "https://naveenautomationlabs.com/opencart/index.php?route=account/login";

	public static final String MYACCOUNTS_PAGE_TITLE = "My Account";
	public static final String MYACCOUNTS_PAGE_LEFTMENU_FIRSTHEADER = "My Account";
	public static final String MYACCOUNTS_PAGE_LOGO_TITLE = "naveenopencart";
	public static final String MYACCOUNTS_PAGE_COMPANYNAME_POWEREDBYLINK_FOOTER = "OpenCart";
	public static final List<String> MYACCOUNTS_PAGE_CATEGORYNAMES_TOPMENU_NAVBAR = Arrays.asList("Desktops",
			"Laptops & Notebooks", "Components", "Tablets", "Software", "Phones & PDAs", "Cameras", "MP3 Players");

	public static final int MYACCOUNTS_PAGE_CATEGORYNAMES_TOPMENU_NAVBAR_COUNT = MYACCOUNTS_PAGE_CATEGORYNAMES_TOPMENU_NAVBAR
			.size();

	public static final String ACCOUNTSUCCESS_PAGE_TITLE = "Your Account Has Been Created!";
	public static final String ACCOUNTSUCCESS_PAGE_CONFIRMATIONTEXT = "Your Account Has Been Created!";
}
