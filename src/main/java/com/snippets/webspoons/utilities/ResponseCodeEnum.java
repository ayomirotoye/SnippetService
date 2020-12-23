package com.snippets.webspoons.utilities;

import lombok.Getter;
import lombok.Setter;

public enum ResponseCodeEnum {

	FAILED("01", "Failed", ""), BAD_REQUEST("400", "Something is fishy about your request! Please check !", ""),
	UNAUTHORISED("401", "You do not have enough authorization to perform this action", ""),
	UNSUPPORTED_MEDIA_TYPE("415", "Unsupported media type", ""),
	SYSTEM_MALFUNCTION("96", "Something Happened ... Please contact Admin ...", ""),
	INTERNAL_SERVER_ERROR("503", "Internal Server Error", ""), METHOD_NOT_ALLOWED("405", "Method not allowed", ""),
	CLIENT_ERROR("400", "Something is fishy about your request! Please check !", ""),
	GENERAL_OR_FATAL_ERROR("06", "General or Fatal Error occurred", "");

	@Getter
	@Setter
	private String responseCode;
	@Getter
	@Setter

	private String description;
	@Getter
	@Setter
	private String message;

	private ResponseCodeEnum(String responseCode, String description, String message) {
		this.responseCode = responseCode;
		this.description = description;
		this.message = message;
	}

}
