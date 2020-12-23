package com.snippets.webspoons.utilities;

import javax.servlet.http.HttpServletRequest;

public class UrlUtils {
	public static String createURL(HttpServletRequest request, String resourcePath) {

		int port = request.getServerPort();
		StringBuilder result = new StringBuilder();
		result.append(request.getScheme()).append("://").append(request.getServerName());

		result = definePort(request, port, result);

		result.append(request.getContextPath());

		result = defineResourcePath(request, resourcePath, result);

		return result.toString();

	}

	private static StringBuilder definePort(HttpServletRequest request, int port, StringBuilder result) {
		if ((request.getScheme().equals("http") && port != 80)
				|| (request.getScheme().equals("https") && port != 443)) {
			result.append(':').append(port);
		}
		return result;
	}

	private static StringBuilder defineResourcePath(HttpServletRequest request, String resourcePath,
			StringBuilder result) {
		if (resourcePath != null && resourcePath.length() > 0) {
			if (!resourcePath.startsWith("/")) {
				result.append("/");
			}
			result.append(resourcePath);
		}

		return result;
	}
}
