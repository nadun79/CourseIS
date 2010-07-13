package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Day;
import util.Period;

import beans.*;
import db.*;

/**
 * Servlet implementation class SchedulerServelet
 */
public class SchedulerServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Boolean running = false;
       
	
	public static Boolean isRunning() {
		return running;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchedulerServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("admin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		running = !running;	// Flip on each press for now
		
		if(running)	{
			runScheduler();
			request.getSession().setAttribute("message", "Retreat!");
		} else {
			request.getSession().removeAttribute("message");
		}
		
		request.getSession().setAttribute("running", running);

		String url="/admin/admin.jsp";
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void runScheduler() {
		ScheduleTable.drop();
		ScheduleTable.create();
		
		
		/* 1a ******************************************/
		/* Get list of courses taught by each lecturer */
		List<Lecturer> lecturers = LecturerTable.selectAll();
		List<List<Course_Lecturer>> clList = new ArrayList<List<Course_Lecturer>>();
		for(Lecturer lecturer : lecturers){
			clList.add(Course_LecturerTable.selectAllByLecturer(lecturer));
		}
		
		/* 2a ********************************/
		/* Sort clList by internal list size */
		Collections.sort(clList, new Comparator<List<Course_Lecturer>>() {
		    public int compare(List<Course_Lecturer> o1, List<Course_Lecturer> o2) {
		        return o2.size() - o1.size();
		    }});
		/* 3a ************************************************/
		/* Iterate through sorted list (biggest to smallest) */
		for(List<Course_Lecturer> clInner : clList) {
			//System.out.println("\nInnerList:\n\t" + innerList);
			/* 1b **********************************/
			/* Get each course from each innerList */
			List<Course> courses = new ArrayList<Course>();
			for(Course_Lecturer cl : clInner) {
				courses.add(CourseTable.selectByCode(cl.getC_code()));
			}
			/* Get list of students enrolled in course */
			List<List<Course_Student>> csList = new ArrayList<List<Course_Student>>();
			for(Course course : courses) {
				csList.add(Course_StudentTable.selectAllByCourse(course));
			}
			/* 2b ********************************/
			/* Sort csList by internal list size */
			Collections.sort(csList, new Comparator<List<Course_Student>>() {
			    public int compare(List<Course_Student> o1, List<Course_Student> o2) {
			        return o2.size() - o1.size();
			    }});
			
			/* 3b ***********************************************/
			/* Iterate through sorted list (biggest to smallest) */
			System.out.println("\n***\nBegin Allocation\n***\n");
			for(List<Course_Student> csInner : csList){
				//System.out.println("\ncsInner\n\t" + csInner + "\n");
				
				/* 1c *****************************/
				/* Check whether course is allocated a room */
				if(ScheduleTable.selectAllByCourse(
								CourseTable.selectByCode(
										csInner.get(0).getcCode())) != null) {
					System.out.println("Course already scheduled, moving to next");
					continue;
				}
				
				/* 2c ****************************/
				/* Start at Monday, 1st period, and find a room that will fit */
				if(findRoomForCourse(csInner, clInner)) {
					continue;
				}
				
				/* Unavoidable clash, Do Nothing */
			}
		}
	}
	
	private Boolean findRoomForCourse(List<Course_Student> csInner, 
			List<Course_Lecturer> clInner) {
		/* Get set of room numbers */
		Set<Integer> rooms = new HashSet<Integer>();
		for(Room room : RoomTable.selectAll()) {
			rooms.add(room.getNum());
		}
		
		/* Get set of courses for current lecturer */
		Lecturer lecturer = LecturerTable.selectByName(clInner.get(0).getL_name());
		Set<String> lecturedCourses = new HashSet<String>();
		for(Course_Lecturer cl : Course_LecturerTable.selectAllByLecturer(lecturer)) {
			lecturedCourses.add(cl.getC_code());
		}
		
		for(Day day : Day.days) {
			/* Don't care about weekend */
			if(day.equals(Day.SATURDAY) || day.equals(Day.SUNDAY)) {
				continue;
			}
			for(Period period : Period.periods) {
				/* Find out which rooms are available */
				Set<Integer> scheduledRooms = new HashSet<Integer>();
				for(Schedule schedule : ScheduleTable.selectAllByDayPeriod(day, period)) {
					scheduledRooms.add(schedule.getR_num());
				}
				Set<Integer> difference = new HashSet<Integer>(rooms);
				difference.removeAll(scheduledRooms);
				/* If all the rooms are taken for this day/period */
				if(difference.isEmpty()) {
					continue;
				}
				
				/* Does lecturer for this course already teach at this time? */
				Set<String> scheduledCourses = new HashSet<String>();
				for(Schedule schedule : ScheduleTable.selectAllByDayPeriod(day, period)) {
					scheduledCourses.add(schedule.getC_code());
				}
				
				Set<String> intersection = new HashSet<String>(lecturedCourses);
				intersection.retainAll(scheduledCourses);
System.out.println("\nSCHEDULED on " + day + "," + period + ":\n\t" + scheduledCourses);
System.out.println("\nLECTURED:\n\t" + lecturedCourses);
System.out.println("\nINTERSECTION:\n\t" + intersection);
				/* If lecturer is teaching for this day/period */
				if(!intersection.isEmpty()) {
					continue;
				}
				
				
				/* Find first room that can fit all the students */
				Integer numStudents = csInner.size();
				for(Integer rNum : difference) {
					if(RoomTable.selectByNum(rNum).getCapacity() >= numStudents) {
						Schedule s = new Schedule(
								csInner.get(0).getcCode(), 
								rNum, day, period);
						ScheduleTable.insert(s);
						System.out.println("Scheduled: " + s.toString());
						return true;
					}
					/* Couldn't find a sizable room, just keep going */
				}
			}
		}
		return false;
	}
}
