package com.ygsoft.kpiviewer.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getCentainDateByNum(Date startDate, Integer num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DATE,num);
		Date endDate = calendar.getTime();
		return endDate;
	}
}
