package tests;

import db.AdminTable;
import beans.Admin;

public class AdminTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(!AdminTable.create()) {
			System.out.println("Error creating admin table;");
		}

		String name = "name";
		String password = "password";
		Admin admin = new Admin(name, password);

		Admin l1 = AdminTable.insert(admin);
		if(l1 == null) {
			System.out.println("Error inserting admin");
		}
		
		Admin l2 = AdminTable.selectByName(admin.getName());
		if(l2 == null) {
			System.out.println("Couldn't find admin with id " + l1.getName());
		} else {
			System.out.println("Found: " + l2.toString());
		}
		
		l2.setPassword("Anonymous");
		
		Admin l3 = AdminTable.update(l2);
		if(l3 == null) {
			System.out.println("Couldn't find admin with name " + l2.getName());
		} else {
			System.out.println("Updated to: " + l3.toString());
		}
		
		if(!AdminTable.delete(l3)) {
			System.out.println("Couldn't delete admin with name " + l3.getName());
		}
		
		if(!AdminTable.drop()) {
			System.out.println("Error dropping admin table;");
		}

	}

}
