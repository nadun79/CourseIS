package beans;

import util.*;

public class Schedule {
	private String c_code;	// A course code
	private int r_num;		// A room number
	private Day day;		// Day room is being used
	private Period period; 	// Time period room is being used

	/* Construct with existing course and room an known day and period*/
	public Schedule(Course c, Room r, Day day, Period period) {
		this.c_code = c.getCode();
		this.r_num = r.getNum();
		this.day = day;
		this.period = period;
	}
	
	/* Construct with known course and room number*/
	public Schedule(String c_code, int r_num, Day day, Period period) {
		this.c_code = c_code;
		this.r_num = r_num;
		this.day = day;
		this.period = period;
	}

	public String getC_code() {
		return c_code;
	}

	public int getR_num() {
		return r_num;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}
	
	@Override
	public String toString() {
		return "Schedule [c_code=" + c_code + ", day=" + day + ", period="
				+ period + ", r_num=" + r_num + "]";
	}

	/* Useful static attributes */
	public static final String TABLE_ID = "schedule";
	
	public static final class Attribute {
		public static final String C_CODE = "c_code";
		public static final String R_NUM = "r_num";
		public static final String DAY = "day";
		public static final String PERIOD = "period";
	}
	
	
}
