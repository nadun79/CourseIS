package beans;

public class Room {
	private int num;			// The room's number, auto-generated primary key
	private int capacity;		// The room's max capacity
	private Boolean availability;	// Whether the room is available
	
	/* Fully Populated Course */
	public Room(int capacity) {
		this.capacity = capacity;
		this.availability = true;
	}
	
	/* Tabla Rasa */
	public Room() {
		// Intentionally blank
	}
	
	/* New Copy */
	public Room(Room c) {
		this.num = c.getNum();
		this.capacity = c.getCapacity();
		this.availability = c.getAvailability() == 0 ? false : true;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getAvailability() {
		return availability ? 1 : 0;
	}
	
	public void setAvailability(int available) {
		this.availability = available == 0 ? false : true;
	}


	@Override
	public String toString() {
		return "Room [available=" + availability + ", capacity=" + capacity
				+ ", num=" + num + "]";
	}


	/* Useful static attributes */
	public static final String TABLE_ID = "rooms";
	
	public static final class Attribute {
		public static final String NUM = "num";
		public static final String CAPACITY = "capacity";
		public static final String AVAILABLE = "available";
	}
	
}
