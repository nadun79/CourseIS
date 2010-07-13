package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.AdminTable;
import db.LecturerTable;
import db.StudentTable;

import beans.Admin;
import beans.Lecturer;
import beans.Student;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userType = request.getParameter("user");
		
		if(userType == null) {
			response.sendRedirect("index.xhtml");
			return;
		}
	
		Boolean auth = (Boolean)request.getSession().getAttribute("auth");
		if(auth != null) {
			if(auth) {
				response.sendRedirect(userType);
				return;
			}
		}
		
		userType = Character.toUpperCase(userType.charAt(0)) + 
				userType.substring(1).toLowerCase();
		
		ArrayList<String> validTypes = new ArrayList<String>();
		validTypes.add("Student");
		validTypes.add("Lecturer");
		validTypes.add("Admin");
		if(!validTypes.contains(userType)) {
			response.sendRedirect("index.xhtml");
			return;
		}
		
		request.setAttribute("userType", userType);
		
		String url="/login.jsp";
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		
		if(userType == null) {
			response.sendRedirect("index.xhtml");
			return;
		}
		
		String url="/login.jsp";
		request.setAttribute("userType", userType);
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		
		if(username == null || password == null ||
				username.equals("") || password.equals("")) {
			rd.forward(request, response);
			return;
		}
		
		Object validUser = null;
		
		if(userType.equals("Student")) {
			int id;
			try {
				id = Integer.parseInt(username);
			} catch (NumberFormatException e) {
				request.setAttribute("message", 
						"Student User Name is the same as your Student ID.");
				rd.forward(request, response);
				return;
			}
			validUser = validateStudent(id, password);
		} else if(userType.equals("Lecturer")) {
			validUser = validateLecturer(username, password);
		} else if(userType.equals("Admin")) {
			validUser = validateAdmin(username, password);
		}
		
		if(validUser == null) {
			request.setAttribute("message", 
					"Invalid User Name/Password Combination.");
			rd.forward(request, response);
			return;
		}

		/* Login Successful */
		request.getSession().setAttribute("auth", true);
		request.getSession().setAttribute("authType", userType);
		request.getSession().setAttribute("user", validUser);
		response.sendRedirect(userType);
		
	}
	
	private Student validateStudent(int username, String password) {
		Student student = StudentTable.selectByID(username);
		if(student == null) {
			return null;
		}
		if(password.equals(student.getPassword())) {
			return student;
		}
		return null;
	}
	
	private Lecturer validateLecturer(String username, String password) {
		Lecturer lecturer = LecturerTable.selectByName(username);
		if(lecturer == null) {
			return null;
		}
		if(password.equals(lecturer.getPassword())) {
			return lecturer;
		}
		
		return null;
	}
	
	private Admin validateAdmin(String username, String password) {
		Admin admin = AdminTable.selectByName(username);
		if(admin == null) {
			return null;
		}
		if(password.equals(admin.getPassword())) {
			return admin;
		}
		return null;
	}
}
