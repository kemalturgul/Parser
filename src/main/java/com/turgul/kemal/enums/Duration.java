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

	public static Duration getDuration(String durationString) {
		for (Duration value : Duration.values()) {
			if (value.getDurationTxt().equals(durationString)) {
				return value;
			}
		}
		return null;
	}
}
