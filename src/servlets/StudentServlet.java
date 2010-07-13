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

import db.CourseTable;
import db.Course_StudentTable;
import db.StudentTable;

import beans.Course;
import beans.Course_Student;
import beans.Student;

import servlets.SchedulerServelet;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((Boolean)request.getSession().getAttribute("auth") != true) {
			response.sendRedirect("index.xhtml");
			return;
		}
		if(!request.getSession().getAttribute("authType").equals("Student")) {
			response.sendRedirect((String)request.getSession().getAttribute("authType"));
			return;
		}
		
		Student user = (Student)request.getSession().getAttribute("user");
		request.setAttribute("user", user);
		
		List<Course> courses = new ArrayList<Course>();

		List<Course_Student> course_student = Course_StudentTable.selectAllByStudent(user);
		for(Course_Student join : course_student ) {
			courses.add(CourseTable.selectByCode(join.getcCode()));
		}
		request.setAttribute("courseList", courses);
		
		if(SchedulerServelet.isRunning())	{
			request.getSession().setAttribute("running", true);
		} else {
			request.getSession().removeAttribute("running");
		}
		
		String url="/student/student.jsp";
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
