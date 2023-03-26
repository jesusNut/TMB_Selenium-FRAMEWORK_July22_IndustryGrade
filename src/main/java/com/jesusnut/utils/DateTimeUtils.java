package com.jesusnut.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.jesusnut.exception.FrameworkException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility Class for Date and Time Operations using Joda Date Time API.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeUtils {

	private static final String LOCALDATE_PATTERN = "MM-dd-yyyy";
	private static final String LOCALTIME_PATTERN = "HH:mm:ss";
	private static final String LOCALDATETIME_PATTERN = "yyyy-MM-dd-HH-mm-ss-SS";
	private static final String ZONEDDATE_PATTERN = "MM-dd-yyyy";
	private static final String ZONEDTIME_PATTERN = "HH:mm:ss";
	private static final String ZONEDDATETIME_PATTERN = "MMM-dd-yyyy HH.mm.ss";

	public static String getLocalDate() {

		LocalDate localDate = LocalDate.now();

		DateTimeFormatter dt = DateTimeFormatter.ofPattern(LOCALDATE_PATTERN);

		return dt.format(localDate);

	}

	public static String getLocalTime() {

		LocalTime localTime = LocalTime.now();

		DateTimeFormatter dt = DateTimeFormatter.ofPattern(LOCALTIME_PATTERN);

		return dt.format(localTime);

	}

	public static String getLocalDateTime() {

		LocalDateTime ldt = LocalDateTime.now();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(LOCALDATETIME_PATTERN);

		return dtf.format(ldt);

	}

	public static String getZonedDate(String zoneId) {

		ZonedDateTime unformattedZonedDateTime = verifyZoneID(zoneId);

		DateTimeFormatter dt = DateTimeFormatter.ofPattern(ZONEDDATE_PATTERN);

		return dt.format(unformattedZonedDateTime);

	}

	public static String getZonedTime(String zoneId) {

		ZonedDateTime unformattedZonedDateTime = verifyZoneID(zoneId);

		DateTimeFormatter dt = DateTimeFormatter.ofPattern(ZONEDTIME_PATTERN);

		return dt.format(unformattedZonedDateTime);

	}

	public static String getZonedDateTime(String zoneId) {

		ZonedDateTime unformattedZonedDateTime = verifyZoneID(zoneId);

		DateTimeFormatter dt = DateTimeFormatter.ofPattern(ZONEDDATETIME_PATTERN);

		return dt.format(unformattedZonedDateTime);

	}

	private static ZonedDateTime verifyZoneID(String zoneId) {

		try {

			ZoneId zid = ZoneId.of(zoneId);

			return ZonedDateTime.now(zid);

		}

		catch (DateTimeException e) {

			throw new FrameworkException("JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Incorrect ZoneID ["
					+ zoneId + "] provided. Please refer http://joda-time.sourceforge.net/timezones.html.", e);

		}

	}

}
