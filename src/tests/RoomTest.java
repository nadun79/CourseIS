package tests;

import db.RoomTable;
import beans.Room;

public class RoomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(!RoomTable.create()) {
			System.out.println("Error creating room table;");
		}

		int capacity = 5;
		Room room = new Room(capacity);

		Room r1 = RoomTable.insert(room);
		if(r1 == null) {
			System.out.println("Error inserting room");
		}
		
		Room r2 = RoomTable.selectByNum(r1.getNum());
		if(r2 == null) {
			System.out.println("Couldn't find room with num " + r1.getNum());
		} else {
			System.out.println("Found: " + r2.toString());
		}
		
		r2.setCapacity(100);
		r2.setAvailability(0);
		
		Room r3 = RoomTable.update(r2);
		if(r3 == null) {
			System.out.println("Couldn't find room with num " + r2.getNum());
		} else {
			System.out.println("Updated to: " + r3.toString());
		}
		
		if(!RoomTable.delete(r3)) {
			System.out.println("Couldn't delete room with num " + r3.getNum());
		}
		
		if(!RoomTable.drop()) {
			System.out.println("Error dropping room table;");
		}

	}

}
