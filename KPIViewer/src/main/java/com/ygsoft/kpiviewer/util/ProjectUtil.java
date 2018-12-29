package com.ygsoft.kpiviewer.util;

import java.util.UUID;

public class ProjectUtil {
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

}
