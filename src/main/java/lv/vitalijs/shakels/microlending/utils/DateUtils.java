package lv.vitalijs.shakels.microlending.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final int DAYS_IN_A_WEEK = 7;
	
	public static Date shiftDateByDays(Date date, int daysToShift) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + daysToShift);
		return calendar.getTime();
	}

}
