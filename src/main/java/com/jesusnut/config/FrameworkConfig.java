package com.jesusnut.config;

import org.aeonbits.owner.Config;

/**
 * FrameworkConfig interface is used by org.aeonbits.owner Library.<br>
 * All the keys in the configuration files are mapped in this interface.<br>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see org.aeonbits.owner.Config.LoadPolicy
 * @see org.aeonbits.owner.Config.Sources
 * @see ConfigFactory
 */

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ "system:properties", "system:env", "file:${user.dir}/src/test/resources/config/config.properties",
		"file:${user.dir}/src/test/resources/config/qa-config.properties",
		"file:${user.dir}/src/test/resources/config/dev-config.properties",
		"file:${user.dir}/src/test/resources/config/uat-config.properties" })

public interface FrameworkConfig extends Config {

	@DefaultValue("qa")
	String environment();

	@Key("${environment}.webUrl")
	String url();

	@DefaultValue("chrome")
	@Key("browser")
	String browser();

	@DefaultValue("true")
	@Key("customizedReport")
	boolean customizedReport();

	@DefaultValue("false")
	@Key("passedStepsScreenshots")
	boolean passedStepsScreenshots();

	@DefaultValue("true")
	@Key("overrideReports")
	boolean overrideReports();

	@Key("${environment}.username")
	String username();

	@Key("${environment}.password")
	String password();

	@DefaultValue("false")
	@Key("highlightElements")
	boolean highlightedElements();

	@DefaultValue("false")
	@Key("remote")
	boolean remoteRunModeStatus();

	@Key("remoteURL")
	String remoteURL();

	@DefaultValue("false")
	@Key("headless")
	boolean headlessRunModeStatus();

	@DefaultValue("false")
	@Key("incognito")
	boolean incognitoRunModeStatus();

}
