package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Course;
import beans.Course_Lecturer;
import beans.Lecturer;
import beans.Schedule;
import db.CourseTable;
import db.Course_LecturerTable;
import db.LecturerTable;
import db.ScheduleTable;

/**
 * Servlet implementation class CoursesServlet
 */
public class CoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoursesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("c_code") != null) {
			Course course = CourseTable.selectByCode((String)request.getParameter("c_code"));
			
			/* Who's lecturing this course */
			List<Course_Lecturer> clJoin = Course_LecturerTable.selectAllByCourse(course);
			List<Lecturer> lecturers = new ArrayList();
			for(Course_Lecturer cl : clJoin) {
				lecturers.add(LecturerTable.selectByName(cl.getL_name()));
			}
			
			/* When and where */
			Schedule schedule = ScheduleTable.selectAllByCourse(course);
			
			request.setAttribute("lecturers", lecturers);
			request.setAttribute("schedule", schedule);
			request.removeAttribute("courseList");
			

			String url="/courses.jsp?c_code=" + course.getCode();
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);
			return;
		}
		
		
		List<Course> courses = CourseTable.selectAll();
		request.setAttribute("courseList", courses);
		
		String url="/courses.jsp";
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
