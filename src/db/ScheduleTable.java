package db;

import java.sql.ResultSet;
import java.util.Formatter;
import java.util.List;
import java.util.ArrayList;

import util.Day;
import util.Period;

import beans.Schedule;
import beans.Course;
import beans.Room;

public class ScheduleTable {

	public static Boolean create() {
		String sidField = Schedule.Attribute.R_NUM + 
			" VARCHAR(40) NOT NULL";
		String cCodeField = Schedule.Attribute.C_CODE + 
			" VARCHAR(40) NOT NULL";
		String dayField = Schedule.Attribute.DAY + 
			" INTEGER NOT NULL";
		String periodField = Schedule.Attribute.PERIOD +
			" INTEGER NOT NULL";
		
		Formatter fields = new Formatter();
		fields.format("%s, %s, %s, %s", 
				sidField, 
				cCodeField, 
				dayField,
				periodField);
		
		Formatter f = new Formatter();
		f.format("CREATE TABLE " + Schedule.TABLE_ID + 
				"(%s, PRIMARY KEY (" + 
					Schedule.Attribute.R_NUM + ", " + 
					Schedule.Attribute.C_CODE + ", "+ 
					Schedule.Attribute.DAY + ", "+ 
					Schedule.Attribute.PERIOD + 
				"), " +
				"UNIQUE INDEX (" +
					Schedule.Attribute.C_CODE +
				"))", fields.toString());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			
			System.out.println("Created " + Schedule.TABLE_ID + " Table");
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
	public static Boolean insert(Schedule s) {
		String query = "INSERT INTO " + Schedule.TABLE_ID + 
			" (" + 
				Schedule.Attribute.R_NUM + ", " + 
				Schedule.Attribute.C_CODE + ", " +
				Schedule.Attribute.DAY + ", " + 
				Schedule.Attribute.PERIOD +
			") VALUES " + "('%s', '%s', '%s', '%s')";
		

		Formatter f = new Formatter();
		f.format(query, 
				s.getR_num(), 
				s.getC_code(), 
				s.getDay().getN(),
				s.getPeriod().getN());
				
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			
			System.out.println("Added Row to " + Schedule.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	public static List<Schedule> selectAll() {
		ResultSet result;
		List<Schedule> c_sList = new ArrayList<Schedule>();
		
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Schedule.TABLE_ID);
			
			/* Populate a new Schedule object and add it to the list*/
			while(result.next()) {
				Schedule c_s;
				c_s = new Schedule(
						result.getString(Schedule.Attribute.C_CODE), 
						result.getInt(Schedule.Attribute.R_NUM), 
						new Day(result.getInt(Schedule.Attribute.DAY)),
						new Period(result.getInt(Schedule.Attribute.PERIOD)));
				c_sList.add(c_s);
			}
			
			//System.out.println("Retrieved Schedules = " + c_sList.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		return c_sList.isEmpty() ? null : c_sList;
	}

	/* A course is only held once per week */
	public static Schedule selectAllByCourse(Course course) {
		ResultSet result;
		Schedule schedule = null;
		
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Schedule.TABLE_ID + " " +
					"WHERE " + 
						Schedule.Attribute.C_CODE + 
						" = '" + course.getCode() + "'");
			
			/* If exists, there's only one */
			if(!result.first()) {
				return null;
			}
			
			schedule = new Schedule(
						result.getString(Schedule.Attribute.C_CODE), 
						result.getInt(Schedule.Attribute.R_NUM), 
						new Day(result.getInt(Schedule.Attribute.DAY)),
						new Period(result.getInt(Schedule.Attribute.PERIOD)));
			
			//System.out.println("Retrieved Schedules = " + c_lList.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		return schedule;
	}

	public static List<Schedule> selectAllByRoom(Room room) {
		ResultSet result;
		List<Schedule> c_sList = new ArrayList<Schedule>();
		
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Schedule.TABLE_ID + " " +
					"WHERE " + 
						Schedule.Attribute.R_NUM + 
						" = '" + room.getNum() + "'");
			
			/* Populate a new Course_Student object and add it to the list*/
			while(result.next()) {
				Schedule c_s;
				c_s = new Schedule(
						result.getString(Schedule.Attribute.C_CODE), 
						result.getInt(Schedule.Attribute.R_NUM), 
						new Day(result.getInt(Schedule.Attribute.DAY)),
						new Period(result.getInt(Schedule.Attribute.PERIOD)));
				c_sList.add(c_s);
			}
			
			//System.out.println("Retrieved Schedules = " + c_sList.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		return c_sList;
	}
	

	public static List<Schedule> selectAllByDayPeriod(Day day, Period period) {
		ResultSet result;
		List<Schedule> c_sList = new ArrayList<Schedule>();
		
		try {
			MySQLConnect db = new MySQLConnect();
			String query = 
				"SELECT * FROM " + Schedule.TABLE_ID + " " +
				"WHERE " + 
				Schedule.Attribute.DAY + 
					" = '" + day.getN() + "' AND " +
				Schedule.Attribute.PERIOD +
					" = '" + period.getN() + "'";
			
			//System.out.println("/nQUERY:\n\t" + query);
			
			result = db.query(query);
			
			/* Populate a new Schedule object and add it to the list*/
			while(result.next()) {
				Schedule c_s;
				c_s = new Schedule(
						result.getString(Schedule.Attribute.C_CODE), 
						result.getInt(Schedule.Attribute.R_NUM), 
						new Day(result.getInt(Schedule.Attribute.DAY)),
						new Period(result.getInt(Schedule.Attribute.PERIOD)));
				c_sList.add(c_s);
			}
			
			//System.out.println("Retrieved Schedules = " + c_sList.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		return c_sList;
	}
	
	public static Boolean delete(Schedule cl) {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DELETE FROM " + Schedule.TABLE_ID +
					" WHERE " + 
						Schedule.Attribute.C_CODE + 
						" = '" + cl.getC_code() + "'" +
					" AND " +
						Schedule.Attribute.R_NUM + 
						" = '" + cl.getR_num() + "'");
			
			System.out.println("Removed row from " + Schedule.TABLE_ID);
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean drop() {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DROP TABLE " + Schedule.TABLE_ID);
			
			System.out.println("Dropped " + Schedule.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
