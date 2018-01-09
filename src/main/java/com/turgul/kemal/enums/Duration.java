package com.turgul.kemal.enums;

/**
 * @author kemalturgul
 * @date Jan 5, 2018
 */
public enum Duration {
	DAILY("daily"), HOURLY("hourly");

	private Duration(String durationTxt) {
		this.durationTxt = durationTxt;
	}

	private String durationTxt;

	public String getDurationTxt() {
		return durationTxt;
	}

	/**
	 * Search given string in defined enumeration
	 * 
	 * @param durationString
	 *            a string value to check in Duration enumeration
	 * @return Duration enum or null if not found
	 */
	public static Duration getDuration(String durationString) {
		for (Duration value : Duration.values()) {
			if (value.getDurationTxt().equals(durationString)) {
				return value;
			}
		}
		return null;
	}
}
