package db;

import java.sql.ResultSet;
import java.util.Formatter;
import java.util.List;
import java.util.ArrayList;

import beans.Course_Student;
import beans.Course;
import beans.Student;

public class Course_StudentTable {

	public static Boolean create() {
		String sidField = "s_id INTEGER NOT NULL";
		String cCodeField = "c_code VARCHAR(40) NOT NULL";
		
		Formatter fields = new Formatter();
		fields.format("%s, %s", sidField, cCodeField);
		
		Formatter f = new Formatter();
		f.format("CREATE TABLE course_student(%s, " +
				"PRIMARY KEY (s_Id, c_code))", fields.toString());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Created Course_Student Table");
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
	public static Boolean insert(Course_Student cs) {
		String query = "INSERT INTO course_student " +
		"(s_id, c_code) VALUES " + "('%s', '%s')";
		

		Formatter f = new Formatter();
		f.format(query, cs.getsId(), cs.getcCode());
				
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Added Row to Course_Student Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	public static List<Course_Student> selectAllByCourse(Course course) {
		ResultSet result;
		List<Course_Student> c_sList = new ArrayList<Course_Student>();
		
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM course_student " +
					"WHERE c_code = '" + course.getCode() + "'");
			
			/* Populate new Course_Student and add to list */
			while(result.next()) {
				Course_Student c_s;
				c_s = new Course_Student(
						result.getString("c_code"), 
						result.getInt("s_id"));
				c_sList.add(c_s);
			}
			
			//System.out.println("Retrieved Students = " + c_sList.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		return c_sList;
	}

	public static List<Course_Student> selectAllByStudent(Student student) {
		ResultSet result;
		List<Course_Student> c_sList = new ArrayList<Course_Student>();
		
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM course_student " +
					"WHERE s_id = '" + student.getId() + "'");
			
			/* Populate a new Course_Student object and add it to the list*/
			while(result.next()) {
				Course_Student c_s;
				c_s = new Course_Student(
						result.getString("c_code"), 
						result.getInt("s_id"));
				c_sList.add(c_s);
			}
			
			//System.out.println("Retrieved Courses = " + c_sList.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		return c_sList;
	}
	
	public static Boolean delete(Course_Student cs) {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DELETE FROM course_student " +
					"WHERE c_code = '" + cs.getcCode() + "' " +
					"AND s_id = '" + cs.getsId() + "'");
			System.out.println("Removed Course_Student");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean drop() {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DROP TABLE course_student");
			System.out.println("Dropped Course_Student Table");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
