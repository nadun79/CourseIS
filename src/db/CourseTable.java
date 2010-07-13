package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;


import beans.Course;

public class CourseTable {

	public static Boolean create() {
		String codeField = Course.Attribute.CODE + 
			" VARCHAR(40) NOT NULL";
		String titleField = Course.Attribute.TITLE + 
			" VARCHAR(32) NOT NULL";
		String descriptionField = Course.Attribute.DESCRIPTION + 
			" VARCHAR(512) NOT NULL";
		String unitsField = Course.Attribute.UNITS + 
			" INTEGER NOT NULL";
		
		Formatter fields = new Formatter();
		fields.format("%s, %s, %s, %s", codeField, titleField, 
				descriptionField, unitsField);
		
		Formatter f = new Formatter();
		f.format("CREATE TABLE " + Course.TABLE_ID + "(%s, " +
				"PRIMARY KEY (" +
					Course.Attribute.CODE +
				"))", fields.toString());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Created " + Course.TABLE_ID + " Table");
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
	public static Course insert(Course course) {
		String query = 
			"INSERT INTO " + Course.TABLE_ID + " (" +
				Course.Attribute.CODE + ", " +
				Course.Attribute.TITLE + ", " +
				Course.Attribute.DESCRIPTION + ", " +
				Course.Attribute.UNITS + ") " +
			"VALUES ('%s', '%s', '%s', '%s')";
		

		Formatter f = new Formatter();
		f.format(query, 
				course.getCode(), 
				course.getTitle(), 
				course.getDesc(), 
				course.getUnits());
				
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			
			System.out.println("Added Row to " + Course.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		return new Course(course);
	}

	public static List<Course> selectAll() {
		ResultSet result;
		List<Course> courses = new ArrayList<Course>();
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Course.TABLE_ID);
			
			while(result.next()) {
				Course course = new Course();
				course.setCode(result.getString(Course.Attribute.CODE));
				course.setTitle(result.getString(Course.Attribute.TITLE));
				course.setDesc(result.getString(Course.Attribute.DESCRIPTION));
				course.setUnits(result.getInt(Course.Attribute.UNITS));
				courses.add(course);
			}
			
			//System.out.println("Retrieved Courses " + courses.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		
		return courses.isEmpty() ? null : courses;
	}
	
	public static Course selectByCode(String code) {
		ResultSet result;
		Course course;
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Course.TABLE_ID + 
					" WHERE " +
					Course.Attribute.CODE + " = '" + code + "'");
			
			/* Populate a new student object */
			if(!result.first()) {	// No such row
				db.close();
				return null;
			}

			course = new Course();
			course.setCode(result.getString(Course.Attribute.CODE));
			course.setTitle(result.getString(Course.Attribute.TITLE));
			course.setDesc(result.getString(Course.Attribute.DESCRIPTION));
			course.setUnits(result.getInt(Course.Attribute.UNITS));
			
			//System.out.println("Retrieved Course with code = " + course.getCode());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		
		return course;
	}
	
	/* ID should never be changed manually, other values will be updated */
	public static Course update(Course course) {
		String query = 
			"UPDATE " + Course.TABLE_ID + 
			" SET " +
				Course.Attribute.TITLE + " = '%s', " +
				Course.Attribute.DESCRIPTION + " = '%s', " +
				Course.Attribute.UNITS + " = '%d' " +
			"WHERE " +
				Course.Attribute.CODE + " = '%s'";

		Formatter f = new Formatter();
		f.format(query, 
				course.getTitle(), 
				course.getDesc(), 
				course.getUnits(), 
				course.getCode());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Updated Row in " + Course.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		return new Course(course);
	}

	public static Boolean delete(Course course) {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DELETE FROM " + Course.TABLE_ID +
					" WHERE " +
						Course.Attribute.CODE + " = '" + course.getCode() + "'");
			
			System.out.println("Removed Course");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean drop() {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DROP TABLE " + Course.TABLE_ID + "");
			
			System.out.println("Dropped " + Course.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
