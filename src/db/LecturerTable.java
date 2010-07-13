package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import beans.Lecturer;

public class LecturerTable {

	public static Boolean create() {
		String nameField = Lecturer.Attribute.NAME + 
			" VARCHAR(40) NOT NULL";
		String passwordField = Lecturer.Attribute.PASSWORD +
			" VARCHAR(32) NOT NULL";
		
		Formatter fields = new Formatter();
		fields.format("%s, %s", nameField, passwordField);
		
		Formatter f = new Formatter();
		f.format("CREATE TABLE " + Lecturer.TABLE_ID + "(%s, " +
				"PRIMARY KEY (" +
					Lecturer.Attribute.NAME +
				"))", fields.toString());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Created " + Lecturer.TABLE_ID + " Table");
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
	public static Lecturer insert(Lecturer lecturer) {
		String query = 
			"INSERT INTO " + Lecturer.TABLE_ID + " (" +
				Lecturer.Attribute.NAME + ", " +
				Lecturer.Attribute.PASSWORD + ") " +
			"VALUES ('%s', '%s')";
		

		Formatter f = new Formatter();
		f.format(query, lecturer.getName(), lecturer.getPassword());
				
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Added Row to " + Lecturer.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		return new Lecturer(lecturer);
	}
	
	public static List<Lecturer> selectAll() {
		ResultSet result;
		List<Lecturer> lecturers = new ArrayList<Lecturer>();
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Lecturer.TABLE_ID);

			while(result.next()) {
				Lecturer lecturer = new Lecturer();
				lecturer.setName(result.getString(Lecturer.Attribute.NAME));
				lecturer.setPassword(result.getString(Lecturer.Attribute.PASSWORD));
				lecturers.add(lecturer);
			}
			
			//System.out.println("Retrieved Lecturers " + lecturers.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		
		return lecturers.isEmpty() ? null : lecturers;
	}
	
	public static Lecturer selectByName(String name) {
		ResultSet result;
		Lecturer lecturer;
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Lecturer.TABLE_ID + " " +
					"WHERE " +
					Lecturer.Attribute.NAME + " = '" + name + "'");
			
			/* Populate a new student object */
			if(!result.first()) {	// No such row
				db.close();
				return null;
			}

			lecturer = new Lecturer();
			lecturer.setName(result.getString(Lecturer.Attribute.NAME));
			lecturer.setPassword(result.getString(Lecturer.Attribute.PASSWORD));
			
			//System.out.println("Retrieved Lecturer with name = " + lecturer.getName());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		
		return lecturer;
	}
	
	/* ID should never be changed manually, other values will be updated */
	public static Lecturer update(Lecturer lecturer) {
		String query = 
			"UPDATE " + Lecturer.TABLE_ID + 
			" SET " +
				Lecturer.Attribute.PASSWORD + " = '%s' " +
			"WHERE " +
				Lecturer.Attribute.NAME + " = '%s'";

		Formatter f = new Formatter();
		f.format(query, lecturer.getPassword(), lecturer.getName());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Updated Row in " + Lecturer.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		return new Lecturer(lecturer);
	}

	public static Boolean delete(Lecturer lecturer) {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DELETE FROM " + Lecturer.TABLE_ID + 
					" WHERE " +
						Lecturer.Attribute.NAME + " = '" + lecturer.getName() + "'");
			System.out.println("Removed Lecturer");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean drop() {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DROP TABLE " + Lecturer.TABLE_ID + "");
			System.out.println("Dropped " + Lecturer.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
