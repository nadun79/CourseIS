package db;

import java.sql.ResultSet;
import java.util.Formatter;


import beans.Admin;

public class AdminTable {

	public static Boolean create() {
		String nameField = Admin.Attribute.NAME + 
			" VARCHAR(40) NOT NULL";
		String passwordField = Admin.Attribute.PASSWORD +
			" VARCHAR(32) NOT NULL";
		
		Formatter fields = new Formatter();
		fields.format("%s, %s", nameField, passwordField);
		
		Formatter f = new Formatter();
		f.format("CREATE TABLE " + Admin.TABLE_ID + "(%s, " +
				"PRIMARY KEY (" +
					Admin.Attribute.NAME +
				"))", fields.toString());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Created " + Admin.TABLE_ID + " Table");
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
	public static Admin insert(Admin lecturer) {
		String query = 
			"INSERT INTO " + Admin.TABLE_ID + " (" +
				Admin.Attribute.NAME + ", " +
				Admin.Attribute.PASSWORD + ") " +
			"VALUES ('%s', '%s')";
		

		Formatter f = new Formatter();
		f.format(query, lecturer.getName(), lecturer.getPassword());
				
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Added Row to " + Admin.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		return new Admin(lecturer);
	}
	
	public static Admin selectByName(String name) {
		ResultSet result;
		Admin lecturer;
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Admin.TABLE_ID + " " +
					"WHERE " +
					Admin.Attribute.NAME + " = '" + name + "'");
			
			/* Populate a new student object */
			if(!result.first()) {	// No such row
				db.close();
				return null;
			}

			lecturer = new Admin();
			lecturer.setName(result.getString(Admin.Attribute.NAME));
			lecturer.setPassword(result.getString(Admin.Attribute.PASSWORD));
			
			//System.out.println("Retrieved Lecturer with name = " + lecturer.getName());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		
		return lecturer;
	}
	
	/* ID should never be changed manually, other values will be updated */
	public static Admin update(Admin lecturer) {
		String query = 
			"UPDATE " + Admin.TABLE_ID + 
			" SET " +
				Admin.Attribute.PASSWORD + " = '%s' " +
			"WHERE " +
				Admin.Attribute.NAME + " = '%s'";

		Formatter f = new Formatter();
		f.format(query, lecturer.getPassword(), lecturer.getName());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Updated Row in " + Admin.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		return new Admin(lecturer);
	}

	public static Boolean delete(Admin lecturer) {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DELETE FROM " + Admin.TABLE_ID + 
					" WHERE " +
						Admin.Attribute.NAME + " = '" + lecturer.getName() + "'");
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
			db.execute("DROP TABLE " + Admin.TABLE_ID + "");
			System.out.println("Dropped " + Admin.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
