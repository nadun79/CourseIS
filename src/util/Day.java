package util;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Day {
	public static final Day MONDAY = new Day(0);
	public static final Day TUESDAY = new Day(1);
	public static final Day WEDNESDAY = new Day(2);
	public static final Day THURSDAY = new Day(3);
	public static final Day FRIDAY = new Day(4);
	public static final Day SATURDAY = new Day(5);
	public static final Day SUNDAY = new Day(6);
//	
//	public static final Day[] days = {MONDAY, TUESDAY,
//		WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};
	
	public static final Day[] days = {MONDAY, TUESDAY};

	
	private int n;	// Number of the day
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Day))
			return false;
		Day castOther = (Day) other;
		return new EqualsBuilder().append(n, castOther.n).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(n).toHashCode();
	}

	public Day(int n) {
		this.n = n;
	}
	
	public int getN() {
		return n;
	}
	
	@Override
	public String toString() {
		switch (n) {
			case 0: return "Monday";
			case 1: return "Tuesday";
			case 2: return "Wednesday";
			case 3: return "Thursday";
			case 4: return "Friday";
			case 5: return "Saturday";
			case 6: return "Sunday";
		}
		return "Not a valid day.";
	}
	
	
}
