package init;

import util.Day;
import util.Period;
import db.*;
import beans.*;


public class AllTables {

	/**
	 * Creates and populates all tables
	 */
	public static void main(String[] args) {
		/* Drop All Tables */
		if(StudentTable.drop()) {
			System.out.println("Dropped Student Table");
		}
		if(LecturerTable.drop()) {
			System.out.println("Dropped Lecturer Table");
		}
		if(CourseTable.drop()) {
			System.out.println("Dropped Course Table");
		}
		if(Course_StudentTable.drop()) {
			System.out.println("Dropped Course_Student Table");
		}
		if(Course_LecturerTable.drop()) {
			System.out.println("Dropped Course_Lecturer Table");
		}
		if(RoomTable.drop()) {
			System.out.println("Dropped Room Table");
		}
		if(ScheduleTable.drop()) {
			System.out.println("Dropped Schedule Table");
		}
		if(AdminTable.drop()) {
			System.out.println("Dropped Admin Table");
		}
		
		/* Create All Tables */
		if(StudentTable.create()) {
			System.out.println("Created Student Table");
		}
		if(LecturerTable.create()) {
			System.out.println("Created Lecturer Table");
		}
		if(CourseTable.create()) {
			System.out.println("Created Course Table");
		}
		if(Course_StudentTable.create()) {
			System.out.println("Created Course_Student Table");
		}
		if(Course_LecturerTable.create()) {
			System.out.println("Created Course_Lecturer Table");
		}
		if(RoomTable.create()) {
			System.out.println("Created Room Table");
		}
		if(ScheduleTable.create()) {
			System.out.println("Created Schedule Table");
		}
		if(AdminTable.create()) {
			System.out.println("Created Admin Table");
		}
		
		/* Populate All Tables */
		Student s = new Student(
				"Justin Mancinelli", "password", "01/28/1985", 
				"j@infs3202.com", "0123456789");
		Student s0 = StudentTable.insert(s);
		s.setName("Derek Abbott");
		Student s1 = StudentTable.insert(s);
		s.setName("Alexander Graham Bell");
		Student s2 = StudentTable.insert(s);
		s.setName("Nicola Cabibbo");
		Student s3 = StudentTable.insert(s);
		s.setName("John Dalton");
		Student s4 = StudentTable.insert(s);
		s.setName("William Eccles");
		Student s5 = StudentTable.insert(s);
		s.setName("Ludvig Faddeev");
		Student s6 = StudentTable.insert(s);
		s.setName("Dennis Gabor");
		Student s7 = StudentTable.insert(s);
		s.setName("Rudolf Haag");
		Student s8 = StudentTable.insert(s);
		s.setName("John Iliopoulos");
		Student s9 = StudentTable.insert(s);

		Course c = new Course("INFS3202", "title", "description", 2); 
		Course c0 = CourseTable.insert(c);
		c.setCode("INFS1200"); 
		Course c1 = CourseTable.insert(c);
		c.setCode("CSSE1000");
		Course c2 = CourseTable.insert(c);
		c.setCode("CSSE1001"); 
		Course c3 = CourseTable.insert(c);
		c.setCode("CSSE2002"); 
		Course c4 = CourseTable.insert(c);
		c.setCode("CSSE3004");
		Course c5 = CourseTable.insert(c);
		c.setCode("COMP2303"); 
		Course c6 = CourseTable.insert(c);
		c.setCode("MATH1061"); 
		Course c7 = CourseTable.insert(c);
		c.setCode("INFS2200"); 
		Course c8 = CourseTable.insert(c);
		c.setCode("COMP3301"); 
		Course c9 = CourseTable.insert(c);
		
		Course_Student cs;
		cs = new Course_Student(c3, s4);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c1, s6);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c4, s2);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c1, s5);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c5, s4);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c9, s3);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c2, s3);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c6, s8);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c5, s3);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c3, s2);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c5, s7);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c8, s9);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c9, s1);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c7, s0);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c9, s9);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c3, s8);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c2, s2);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c3, s0);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c8, s8);
		Course_StudentTable.insert(cs);
		cs = new Course_Student(c0, s8);
		Course_StudentTable.insert(cs);
		
		Lecturer l = new Lecturer("Hal Abelson", "password");
		Lecturer l0 = LecturerTable.insert(l);
		l.setName("Charles Babbage");
		Lecturer l1 = LecturerTable.insert(l);
		l.setName("Luca Cardelli");
		Lecturer l2 = LecturerTable.insert(l);
		l.setName("Peter Denning");
		Lecturer l3 = LecturerTable.insert(l);
		l.setName("Andrey Ershov");
		Lecturer l4 = LecturerTable.insert(l);
		l.setName("Robert Floyd");
		Lecturer l5 = LecturerTable.insert(l);
		
		Course_Lecturer cl;
		cl = new Course_Lecturer(c0, l0);
		Course_LecturerTable.insert(cl);
		cl = new Course_Lecturer(c1, l1);
		Course_LecturerTable.insert(cl);
		cl = new Course_Lecturer(c2, l4);
		Course_LecturerTable.insert(cl);
		cl = new Course_Lecturer(c3, l1);
		Course_LecturerTable.insert(cl);
		cl = new Course_Lecturer(c4, l5);
		Course_LecturerTable.insert(cl);
		cl = new Course_Lecturer(c5, l4);
		Course_LecturerTable.insert(cl);
		cl = new Course_Lecturer(c6, l2);
		Course_LecturerTable.insert(cl);
		cl = new Course_Lecturer(c7, l1);
		Course_LecturerTable.insert(cl);
		cl = new Course_Lecturer(c8, l5);
		Course_LecturerTable.insert(cl);
		cl = new Course_Lecturer(c9, l3);
		Course_LecturerTable.insert(cl);
		
		Room r = new Room(1);
		Room r0 = RoomTable.insert(r);
		r.setCapacity(1);
		Room r1 = RoomTable.insert(r);
		r.setCapacity(2);
		Room r2 = RoomTable.insert(r);
//		r.setCapacity(3);
//		Room r3 = RoomTable.insert(r);
//		r.setCapacity(5);
//		Room r4 = RoomTable.insert(r);
		
//		Schedule sched;
//		sched = new Schedule(c0, r0, Day.MONDAY, Period.ONE);
//		ScheduleTable.insert(sched);
//		sched = new Schedule(c1, r1, Day.MONDAY, Period.ONE);
//		ScheduleTable.insert(sched);
//		sched = new Schedule(c2, r4, Day.WEDNESDAY, Period.ONE);
//		ScheduleTable.insert(sched);
//		sched = new Schedule(c3, r1, Day.MONDAY, Period.TWO);
//		ScheduleTable.insert(sched);
//		sched = new Schedule(c4, r0, Day.MONDAY, Period.TWO);
//		ScheduleTable.insert(sched);
//		sched = new Schedule(c5, r4, Day.TUESDAY, Period.THREE);
//		ScheduleTable.insert(sched);
//		sched = new Schedule(c6, r2, Day.MONDAY, Period.FOUR);
//		ScheduleTable.insert(sched);
//		sched = new Schedule(c7, r1, Day.MONDAY, Period.THREE);
//		ScheduleTable.insert(sched);
//		sched = new Schedule(c8, r3, Day.TUESDAY, Period.ONE);
//		ScheduleTable.insert(sched);
//		sched = new Schedule(c9, r3, Day.TUESDAY, Period.TWO);
		
		Admin a = new Admin("Enrico Fermi", "password");
		AdminTable.insert(a);
		
	}
}
