package lv.vitalijs.shakels.microlending.utils;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void shiftDateByDaysTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2013, 3, 19, 12, 12);
		
		Date date1 = DateUtils.shiftDateByDays(calendar.getTime(), 10);
		Date date2 = DateUtils.shiftDateByDays(calendar.getTime(), 20);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Assert.assertEquals(cal1.get(Calendar.DATE), 29);
		Assert.assertEquals(cal1.get(Calendar.MONTH), 3);
		Assert.assertEquals(cal1.get(Calendar.YEAR), 2013);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		Assert.assertEquals(cal2.get(Calendar.DATE), 9);
		Assert.assertEquals(cal2.get(Calendar.MONTH), 4);
		Assert.assertEquals(cal2.get(Calendar.YEAR), 2013);
	}

}
