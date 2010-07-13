package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import beans.Room;

public class RoomTable {

	public static Boolean create() {
		String numField = Room.Attribute.NUM + 
			" INTEGER NOT NULL AUTO_INCREMENT";
		String capacityField = Room.Attribute.CAPACITY + 
			" INTEGER NOT NULL";
		String availableField = Room.Attribute.AVAILABLE + 
		" BOOLEAN NOT NULL";
		
		Formatter fields = new Formatter();
		fields.format("%s, %s, %s", numField, capacityField, availableField);
		
		Formatter f = new Formatter();
		f.format("CREATE TABLE " + Room.TABLE_ID + "(%s, " +
				"PRIMARY KEY (" +
					Room.Attribute.NUM +
				"))", fields.toString());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Created " + Room.TABLE_ID + " Table");
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
	public static Room insert(Room room) {
		String query = 
			"INSERT INTO " + Room.TABLE_ID + " (" +
				Room.Attribute.CAPACITY + ", " +
				Room.Attribute.AVAILABLE + ") " +
			"VALUES ('%d', '%s')";
		

		Formatter f = new Formatter();
		f.format(query, 
				room.getCapacity(),
				room.getAvailability());
				
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			
			System.out.println("Added Row to " + Room.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		/* Get num of newly added room */
		ResultSet result;
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT num FROM " + Room.TABLE_ID);
			if(!result.last()) {	// No such row
				db.close();
				return null;
			}
			
			room.setNum(result.getInt(Room.Attribute.NUM));
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		return new Room(room);
	}
	

	public static List<Room> selectAll() {
		ResultSet result;
		List<Room> rooms = new ArrayList<Room>();
		try {
			MySQLConnect db = new MySQLConnect();
			result = db.query("SELECT * FROM " + Room.TABLE_ID);

			while(result.next()) {
				Room room = new Room();
				room.setAvailability(result.getInt(Room.Attribute.AVAILABLE));
				room.setCapacity(result.getInt(Room.Attribute.CAPACITY));
				room.setNum(result.getInt(Room.Attribute.NUM));
				rooms.add(room);
			}
			
			//System.out.println("Retrieved Rooms " + rooms.toString());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		
		return rooms.isEmpty() ? null : rooms;
	}
	
	public static Room selectByNum(int num) {
		ResultSet result;
		Room room;
		try {
			MySQLConnect db = new MySQLConnect();
			
			String query = 
				"SELECT * FROM " + Room.TABLE_ID + 
				" WHERE " +
					Room.Attribute.NUM + " = '" + num + "'";
			//System.out.println(query);
			result = db.query(query);
			
			
			/* Populate a new student object */
			if(!result.first()) {	// No such row
				db.close();
				return null;
			}

			room = new Room();
			room.setNum(result.getInt(Room.Attribute.NUM));
			room.setCapacity(result.getInt(Room.Attribute.CAPACITY));
			room.setAvailability(result.getInt(Room.Attribute.AVAILABLE));
			
			//System.out.println("Retrieved Course with num = " + room.getNum());
			db.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
		
		return room;
	}
	
	/* num should never be changed manually, other values will be updated */
	public static Room update(Room room) {
		String query = 
			"UPDATE " + Room.TABLE_ID + 
			" SET " +
				Room.Attribute.AVAILABLE + " = '%s', " + 
				Room.Attribute.CAPACITY + " = '%d' " +
			"WHERE " +
				Room.Attribute.NUM + " = '%d'";

		Formatter f = new Formatter();
		f.format(query, 
				room.getAvailability(),
				room.getCapacity(), 
				room.getNum());
		
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute(f.toString());
			System.out.println("Updated Row in " + Room.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		return new Room(room);
	}

	public static Boolean delete(Room course) {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DELETE FROM " + Room.TABLE_ID +
					" WHERE " +
						Room.Attribute.NUM + " = '" + course.getNum() + "'");
			
			System.out.println("Removed Room");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean drop() {
		try {
			MySQLConnect db = new MySQLConnect();
			db.execute("DROP TABLE " + Room.TABLE_ID + "");
			
			System.out.println("Dropped " + Room.TABLE_ID + " Table");
			db.close();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
