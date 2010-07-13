package util;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Period {
	public static final Period ONE = new Period(0);
	public static final Period TWO = new Period(1);
	public static final Period THREE = new Period(2);
	public static final Period FOUR = new Period(3);
//	
//	public static final Period[] periods = {ONE, TWO, THREE, FOUR};
	public static final Period[] periods = {ONE, TWO};
	
	private int n;	// Number of the Period
	public Period(int n) {
		this.n = n;
	}
	
	public int getN() {
		return n;
	}
	
	@Override
	public String toString() {
		switch (n) {
			case 0: return "08:00 - 10:00";
			case 1: return "10:00 - 12:00";
			case 2: return "12:00 - 14:00";
			case 3: return "14:00 - 16:00";
		}
		return "Not a valid Period.";
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Period)) {
			return false;
		}
		Period rhs = (Period) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.n, rhs.n).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(225887801, 841634223).appendSuper(
				super.hashCode()).append(this.n).toHashCode();
	}
	
	
}
