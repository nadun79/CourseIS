package db;

import java.sql.ResultSet;
import java.util.Formatter;
import java.util.List;
import java.util.ArrayList;

import beans.Course_Lecturer;
import beans.Course;
import beans.Lecturer;

public class Course_LecturerTable {

	public static Boolean create() {
		String sidField = Course_Lecturer.Attribute.L_NAME + 
			" VARCHAR(40) NOT NULL";
		String cCodeField = Course_Lecturer.Attribute.C_CODE + 
			" VARCHAR(40) NOT NULL";
		
		Formatter fields = new Formatter();
		fields.format("%s, %s", sidField, cCodeField);
		
		Formatter f = new Formatter();
		f.format("CREATE TABLE " + Course_Lecturer.TABLE_ID + 
				"(%s, PRIMARY KEY (" + 
					Course_Lecturer.Attribute.L_NAME + ", " + 
					Course_Lecturer.Attribute.C_CODE + 
				"))", fields.toString());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			
			System.out.println("Created " + Course_Lecturer.TABLE_ID + " Table");
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
	public static Boolean insert(Course_Lecturer cl) {
		String query = "INSERT INTO " + Course_Lecturer.TABLE_ID + 
			" (" + 
				Course_Lecturer.Attribute.L_NAME + ", " + 
				Course_Lecturer.Attribute.C_CODE + 
			") VALUES " + "('%s', '%s')";
		

		Formatter f = new Formatter();
		f.format(query, cl.getL_name(), cl.getC_code());
				
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			
			System.out.println("Added Row to " + Course_Lecturer.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	public static List<Course_Lecturer> selectAllByCourse(Course course) {
		ResultSet result;
		List<Course_Lecturer> c_lList = new ArrayList<Course_Lecturer>();
		
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Course_Lecturer.TABLE_ID + " " +
					"WHERE " + 
						Course_Lecturer.Attribute.C_CODE + 
						" = '" + course.getCode() + "'");
			
			/* Populate new Course_Student and add to list */
			while(result.next()) {
				Course_Lecturer c_s;
				c_s = new Course_Lecturer(
						result.getString(Course_Lecturer.Attribute.C_CODE), 
						result.getString(Course_Lecturer.Attribute.L_NAME));
				c_lList.add(c_s);
			}
			
			//System.out.println("Retrieved Lecturers = " + c_lList.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		return c_lList;
	}

	public static List<Course_Lecturer> selectAllByLecturer(Lecturer lecturer) {
		ResultSet result;
		List<Course_Lecturer> c_sList = new ArrayList<Course_Lecturer>();
		
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Course_Lecturer.TABLE_ID + " " +
					"WHERE " + 
						Course_Lecturer.Attribute.L_NAME + 
						" = '" + lecturer.getName() + "'");
			
			/* Populate a new Course_Student object and add it to the list*/
			while(result.next()) {
				Course_Lecturer c_s;
				c_s = new Course_Lecturer(
						result.getString(Course_Lecturer.Attribute.C_CODE), 
						result.getString(Course_Lecturer.Attribute.L_NAME));
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
	
	public static Boolean delete(Course_Lecturer cl) {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DELETE FROM " + Course_Lecturer.TABLE_ID +
					" WHERE " + 
						Course_Lecturer.Attribute.C_CODE + 
						" = '" + cl.getC_code() + "'" +
					" AND " +
						Course_Lecturer.Attribute.L_NAME + 
						" = '" + cl.getL_name() + "'");
			
			System.out.println("Removed row from " + Course_Lecturer.TABLE_ID);
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean drop() {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DROP TABLE " + Course_Lecturer.TABLE_ID);
			
			System.out.println("Dropped " + Course_Lecturer.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
