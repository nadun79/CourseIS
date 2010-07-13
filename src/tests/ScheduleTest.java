package tests;

import db.ScheduleTable;
import beans.Course;
import beans.Room;
import beans.Schedule;

import java.util.List;

import util.Day;
import util.Period;

public class ScheduleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(!ScheduleTable.drop()) {
			System.out.println("Error dropping " + Schedule.TABLE_ID + " table;");
		}
		
		if(!ScheduleTable.create()) {
			System.out.println("Error creating " + Schedule.TABLE_ID + " table;");
		}

		/* Make some students and courses */
		Room r1 = new Room(5);
		Room r2 = new Room(10);
		Room r3 = new Room(15);
		Course c1 = new Course("c1", "c1", "desc", 2);
		Course c2 = new Course("c2", "c2", "desc", 2);
		Course c3 = new Course("c3", "c3", "desc", 2);
		Course c4 = new Course("c4", "c4", "desc", 2);
		Course c5 = new Course("c5", "c5", "desc", 2);
		
		/* Since just mocking, add nums to rooms */
		r1.setNum(1);
		r2.setNum(2);
		r3.setNum(3);
		
		/* Populate some row objects for Course_Student */
		Schedule s1 = new Schedule(c1, r1, Day.MONDAY, Period.ONE);
		Schedule s2 = new Schedule(c2, r2, Day.TUESDAY, Period.THREE);
		Schedule s3 = new Schedule(c3, r3, Day.MONDAY, Period.ONE);
		Schedule s4 = new Schedule(c4, r2, Day.MONDAY, Period.TWO);
		Schedule s5 = new Schedule(c5, r1, Day.TUESDAY, Period.THREE);
		
		if(!ScheduleTable.insert(s1)) {
			System.out.println("Error inserting s1");
		}
		if(!ScheduleTable.insert(s2)) {
			System.out.println("Error inserting s2");
		}
		if(!ScheduleTable.insert(s3)) {
			System.out.println("Error inserting s3");
		}
		if(!ScheduleTable.insert(s4)) {
			System.out.println("Error inserting s3");
		}
		if(!ScheduleTable.insert(s5)) {
			System.out.println("Error inserting s3");
		}
		
		Schedule s = ScheduleTable.selectAllByCourse(c1);
		if(s == null) {
			System.out.println("No Rooms booked for course " + c1.getCode());
		} else {
			System.out.println("Found: " + s.toString());
		}

		List<Schedule> cList = ScheduleTable.selectAllByRoom(r1);
		if(cList == null) {
			System.out.println("Room " + r1.getNum() + 
					" not booked for any courses.");
		} else {
			System.out.println("Found: " + cList.toString());
		}
		
		
		if(!ScheduleTable.delete(s1)) {
			System.out.println("Couldn't delete row from " + 
					Schedule.TABLE_ID + " identified by " + 
					s1.getC_code() + "," + s1.getR_num());
		}

	}

}
