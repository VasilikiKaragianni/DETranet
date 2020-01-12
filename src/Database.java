import com.mysql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Calendar;

public class Database {
	public static void loadmySQLDatabase() {
		try{  
			Class.forName("com.mysql.jdbc.Driver"); //if it doesn't work try com.mysql.cj.jdbc.Driver
		
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
	      	Statement stmt = con.createStatement();
	      		
	            String s = ("SELECT * FROM Employee");
	            ResultSet rs = stmt.executeQuery(s);
	            while (rs.next()) {
	      	    	String f = rs.getString("fullname");
	      	    	int id = rs.getInt("idEmployee");
	      	    	String d = rs.getString("department");
	      	    	String e = rs.getString("email");
	      	    	double sa = rs.getDouble("salary");
	      	    	Date da = rs.getDate("firstDate");
	      	    	int l = rs.getInt("leaves");
	      	    	String p = rs.getString("password");
	      	    	double o = rs.getDouble("overall");
	      	    
	      	    	if (d.equals("Manager")) {
	      	    		new Manager (f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Loan Manager")) {
	      	    		new LoanManager(f, id, d, e, sa, da, l, p, o);	      	    	
	      	    	} else if (d.equals("Deposit Manager")) {
	      	    		new DepositManager(f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Private Customer Manager")) {
	      	    		new PrivateCustomerManager(f, id, d, e, sa, da, l, p, o);	      	    		
	      	    	} else if (d.equals("Teller")) {
	      	    		new Teller(f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Private Customer Manager Vip")) {
	      	    		new PrivateCustomerManagerVip(f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Private Customer Manager Delays")) {
	      	    		new PrivateCustomerManagerDelays (f, id, d, e, sa, da, l, p, o);
	      	    	} else if (d.equals("Business Customer Manager Vip")) {
	      	    		new BusinessCustomerManagerVip (f, id, d, e, sa, da, l, p, o);	      	    		
	      	    	} else if (d.equals("Business Customer Manager Delays")) {
	      	    		new BusinessCustomerManagerDelays (f, id, d, e, sa, da, l, p, o);	      	    		
	      	    	} else if (d.equals("Business Service Manager")) {
	      	    		new BusinessServiceManager (f, id, d, e, sa, da, l, p, o);	      	    		
	      	    	} 
	      	    }
	      	    
	      	    
	      	    s = "SELECT * FROM Business"; // +Business
	            rs = stmt.executeQuery(s);
	            while (rs.next()) {
	            	int i = rs.getInt("idBusiness");
	            	String n = rs.getString("name");
	            	int nl = rs.getInt("nmbrLoans");
	            	double a = rs.getDouble("amount");
	            	int id = rs.getInt("idEmployee");
	            	String t = rs.getString("type");
	            	Business b = new Business(n, t, i, a, nl);
	            	for(int c=0; c < Employee.getEmployees().size(); c++) {
	            		Employee e = Employee.getEmployees().get(c);
	            		if( e.getIdEmployee()== id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Business Customer Manager")) {
	            			Business.cSM.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Business Customer Manager Vip")) {
	            			Business.cSMVip.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Business Customer Manager Delays")) {
	            			Business.cSMDelays.add(b);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
        				Employee.getEmployees().get(c).getDepartment().equals("Business Service Manager")) {
	            			Business.bSM.add(b);
	            			break;
	            		} else if(e.getIdEmployee()== id && Employee.getEmployees().get(c).getDepartment().equals("Teller")) {
	            			Business.tellerBusiness.add(b);
	            		}
	            	}
	            }
	            
	            s = "SELECT * FROM Private"; // +Business
	            rs = stmt.executeQuery(s);
	            while (rs.next()) {
	            	int i = rs.getInt("idPrivate");
	            	String n = rs.getString("name");
	            	int nl = rs.getInt("nmbrLoans");
	            	double a = rs.getDouble("amount");
	            	int id = rs.getInt("idEmployee");
	            	int ca = rs.getInt("cards");
	            	Private p = new Private(n, i, a, ca, nl);
	            	for(int c=0; c < Employee.getEmployees().size(); c++) {
	            		Employee e = Employee.getEmployees().get(c);
	            		if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Private Customer Manager")) {
	            			Private.pCM.add(p);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Private Customer Manager Vip")) {
	            			Private.pCMVip.add(p);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
	            				Employee.getEmployees().get(c).getDepartment().equals("Private Customer Manager Delays")) {
	            			Private.pCMDelays.add(p);
	            			break;
	            		} else if(e.getIdEmployee() == id &&
        				Employee.getEmployees().get(c).getDepartment().equals("Teller")) {
	            			Private.tellerPrivate.add(p);
	            			break;
	            		}
	            	}
	            }
		      	rs.close();
		      	System.out.println();
		      	stmt.close();
		        con.close();
	            }
	      	catch(SQLException e) {
	      		System.out.print("SQLException: ");
	      		System.out.println(e.getMessage());
	      	} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	public static void savemysqlDatabase() {
		try{  
			Class.forName("com.mysql.jdbc.Driver"); 	//if it doesn't work try com.mysql.cj.jdbc.Driver
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
			Statement stmt = con.createStatement();
			String query = "DROP TABLE Private";
		    stmt.executeUpdate(query);
		    query = "DROP TABLE Business";
		    stmt.executeUpdate(query);
		    query = "DROP TABLE Employee";
		    stmt.executeUpdate(query);
		    
	      	Statement pst = con.createStatement();
		    for(int i = 0; i <= Employee.getEmployees().size(); i++) {
		    	  String sql = "INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, password) VALUES(?,?,?,?,?,?,?,?,?)";
		    	  pst = con.prepareStatement(sql);
		    	  PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	  pstmt.setInt(1, Employee.getEmployees().get(i).getIdEmployee());
		    	  pstmt.setString(2, Employee.getEmployees().get(i).getFullname());
		    	  pstmt.setString(3, Employee.getEmployees().get(i).getDepartment());
		    	  pstmt.setInt(4, Employee.getEmployees().get(i).getLeaves());
		    	  pstmt.setDouble(5, Employee.getEmployees().get(i).getOverall());
		    	  pstmt.setDate(6, (java.sql.Date) Employee.getEmployees().get(i).getFirstDate());
		    	  pstmt.setDouble(7, Employee.getEmployees().get(i).getSalary());
		    	  pstmt.setString(8, Employee.getEmployees().get(i).getEmail());
		    	  pstmt.setString(9, Employee.getEmployees().get(i).getPassword());
		    }
		    
		    for(int i = 0; i <= Business.getbSM().size(); i++) {
		    	String sql = "INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES(?,?,?,?,?,?)";
		    	pst = con.prepareStatement(sql);
		    	PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pstmt.setInt(1, Business.getbSM().get(i).getIdBusiness());
		    	pstmt.setString(2, Business.getbSM().get(i).getName());
		    	pstmt.setInt(3, Business.getbSM().get(i).getNmbrLoans());
		    	pstmt.setDouble(4, Business.getbSM().get(i).getAmount());
		    	pstmt.setString(5, Business.getbSM().get(i).getType());
		    	for(int d = 0; d < Employee.getEmployees().size(); d++) {
		    		if(Employee.getEmployees().get(d).getDepartment().equals("Business Service Manager ")) {
		    			int id = Employee.getIdEmployees().get(d);
		    			pstmt.setInt(6, id);
		    		}
		    	}
		    }
		    
		    for(int i = 0; i <= Business.getcSM().size(); i++) {
		    	String sql = "INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES(?,?,?,?,?,?)";
		    	pst = con.prepareStatement(sql);
		    	PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pstmt.setInt(1, Business.getcSM().get(i).getIdBusiness());
		    	pstmt.setString(2, Business.getcSM().get(i).getName());
		    	pstmt.setInt(3, Business.getcSM().get(i).getNmbrLoans());
		    	pstmt.setDouble(4, Business.getcSM().get(i).getAmount());
		    	pstmt.setString(5, Business.getcSM().get(i).getType());
		    	for(int d = 0; d < Employee.getEmployees().size(); d++) {
		    		if(Employee.getEmployees().get(d).getDepartment().equals("Business Customer Manager")) {
		    			int id = Employee.getIdEmployees().get(d);
		    			pstmt.setInt(6, id);
		    		}
		    	}
		    }
		    
		    for(int i = 0; i <= Business.getcSMVip().size(); i++) {
		    	String sql = "INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES(?,?,?,?,?,?)";
		    	pst = con.prepareStatement(sql);
		    	PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pstmt.setInt(1, Business.getcSMVip().get(i).getIdBusiness());
		    	pstmt.setString(2, Business.getcSMVip().get(i).getName());
		    	pstmt.setInt(3, Business.getcSMVip().get(i).getNmbrLoans());
		    	pstmt.setDouble(4, Business.getcSMVip().get(i).getAmount());
		    	pstmt.setString(5, Business.getcSMVip().get(i).getType());
		    	for(int d = 0; d < Employee.getEmployees().size(); d++) {
		    		if(Employee.getEmployees().get(d).getDepartment().equals("Business Customer Manager Vip")) {
		    			int id = Employee.getIdEmployees().get(d);
		    			pstmt.setInt(6, id);
		    		}
		    	}
		    }
		    
		    for(int i = 0; i <= Business.getcSMDelays().size(); i++) {
		    	String sql = "INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES(?,?,?,?,?,?)";
		    	pst = con.prepareStatement(sql);
		    	PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pstmt.setInt(1, Business.getcSMDelays().get(i).getIdBusiness());
		    	pstmt.setString(2, Business.getcSMDelays().get(i).getName());
		    	pstmt.setInt(3, Business.getcSMDelays().get(i).getNmbrLoans());
		    	pstmt.setDouble(4, Business.getcSMDelays().get(i).getAmount());
		    	pstmt.setString(5, Business.getcSMDelays().get(i).getType());
		    	for(int d = 0; d < Employee.getEmployees().size(); d++) {
		    		if(Employee.getEmployees().get(d).getDepartment().equals("Business Customer Manager Delays")) {
		    			int id = Employee.getIdEmployees().get(d);
		    			pstmt.setInt(6, d);
		    		}
		    	}
		    }
		    
		    for(int i = 0; i <= Business.getTellerBusiness().size(); i++) {
		    	String sql = "INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES(?,?,?,?,?,?)";
		    	pst = con.prepareStatement(sql);
		    	PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pstmt.setInt(1, Business.getTellerBusiness().get(i).getIdBusiness());
		    	pstmt.setString(2, Business.getTellerBusiness().get(i).getName());
		    	pstmt.setInt(3, Business.getTellerBusiness().get(i).getNmbrLoans());
		    	pstmt.setDouble(4, Business.getTellerBusiness().get(i).getAmount());
		    	pstmt.setString(5, Business.getTellerBusiness().get(i).getType());
		    	//pstmt.setInt(6, Business.getTellerBusiness().get(i).get
		    }
		    
		    for(int i= 0; i <= Private.getpCM().size(); i++) {
		    	String sql ="INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES(?,?,?,?,?,?)";
		    	pst = con.prepareStatement(sql);
		    	PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pstmt.setInt(1, Private.getpCM().get(i).getIdPrivate());
		    	pstmt.setInt(2, Private.getpCM().get(i).getCards());
		    	pstmt.setString(3, Private.getpCM().get(i).getName());
		    	pstmt.setInt(4, Private.getpCM().get(i).getNmbrLoans());
		    	pstmt.setDouble(5, Private.getpCM().get(i).getAmount());
		    	for(int d = 0; d < Employee.getEmployees().size(); d++) {
		    		if(Employee.getEmployees().get(d).getDepartment().equals("Private Customer Manager")) {
		    			int id = Employee.getIdEmployees().get(d);
		    			pstmt.setInt(6, id);
		    		}
		    	}
		    }
		    
		    for(int i= 0; i <= Private.getTeller().size(); i++) {
		    	String sql ="INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES(?,?,?,?,?,?)";
		    	pst = con.prepareStatement(sql);
		    	PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pstmt.setInt(1, Private.getTeller().get(i).getIdPrivate());
		    	pstmt.setInt(2, Private.getTeller().get(i).getCards());
		    	pstmt.setString(3, Private.getTeller().get(i).getName());
		    	pstmt.setInt(4, Private.getTeller().get(i).getNmbrLoans());
		    	pstmt.setDouble(5, Private.getTeller().get(i).getAmount());
		    	for(int d = 0; d < Employee.getEmployees().size(); d++) {
		    		if(Employee.getEmployees().get(d).getDepartment().equals("Teller")) {
		    			int id = Employee.getIdEmployees().get(d);
		    			pstmt.setInt(6, id);
		    		}
		    	}
		    }
		    
		    for(int i= 0; i <= Private.getpCMVip().size(); i++) {
		    	String sql ="INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES(?,?,?,?,?,?)";
		    	pst = con.prepareStatement(sql);
		    	PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pstmt.setInt(1, Private.getpCMVip().get(i).getIdPrivate());
		    	pstmt.setInt(2, Private.getpCMVip().get(i).getCards());
		    	pstmt.setString(3, Private.getpCMVip().get(i).getName());
		    	pstmt.setInt(4, Private.getpCMVip().get(i).getNmbrLoans());
		    	pstmt.setDouble(5, Private.getpCMVip().get(i).getAmount());
		    	for(int d = 0; d < Employee.getEmployees().size(); d++) {
		    		if(Employee.getEmployees().get(d).getDepartment().equals("Private Customer Manager Vip")) {
		    			int id = Employee.getIdEmployees().get(d);
		    			pstmt.setInt(6, id);
		    		}
		    	}
		    }
		    
		    for(int i= 0; i <= Private.getpCMDelays().size(); i++) {
		    	String sql ="INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES(?,?,?,?,?,?)";
		    	pst = con.prepareStatement(sql);
		    	PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    	pstmt.setInt(1, Private.getpCMDelays().get(i).getIdPrivate());
		    	pstmt.setInt(2, Private.getpCMDelays().get(i).getCards());
		    	pstmt.setString(3, Private.getpCMDelays().get(i).getName());
		    	pstmt.setInt(4, Private.getpCMDelays().get(i).getNmbrLoans());
		    	pstmt.setDouble(5, Private.getpCMDelays().get(i).getAmount());
		    	for(int d = 0; d < Employee.getEmployees().size(); d++) {
		    		if(Employee.getEmployees().get(d).getDepartment().equals("Private Customer Manager Delays")) {
		    			int id = Employee.getIdEmployees().get(d);
		    			pstmt.setInt(6, id);
		    		}
		    	}
		    }
		    
		}catch(SQLException e) {
	      		System.out.print("SQLException: ");
	      		System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
	    }
		
	}
	  
	public static void saveEmployee(String f, int id,String d, String em, double s, int l, String p, double o) {
		try{ 
			Class.forName("com.mysql.jdbc.Driver"); 	//if it doesn't work try com.mysql.cj.jdbc.Driver
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
			long millis=System.currentTimeMillis();  
	        java.sql.Date date=new java.sql.Date(millis);
	        PreparedStatement pstmt = con.prepareStatement("INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, password)"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    	  pstmt.setInt(1, id + 2);
	    	  pstmt.setString(2, f);
	    	  pstmt.setString(3, d);
	    	  pstmt.setInt(4, l);
	    	  pstmt.setDouble(5, o); 
	    	  pstmt.setDate(6, date);
	    	  pstmt.setDouble(7, s);
	    	  pstmt.setString(8, em);
	    	  pstmt.setString(9, p);
	    	  int i = pstmt.executeUpdate();
      if(i!=0){
        System.out.println("added");
      }
      else{
        System.out.println("failed to add");
      }

		}catch(SQLException e) {
			System.out.print("SQLException: ");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void deleteBusinessCust(int index) {
		try{ 
			Class.forName("com.mysql.jdbc.Driver"); 	//if it doesn't work try com.mysql.cj.jdbc.Driver
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
	        PreparedStatement pstmt = con.prepareStatement("DELETE FROM Business WHERE idBusiness =" + index);
	    	 int i = pstmt.executeUpdate();
	    	 if(i!=0){
	    		 System.out.println("deleted");
	    	 }
	    	 else{
	    		 System.out.println("failed to delete");
	    	 }

		}catch(SQLException e) {
			System.out.print("SQLException: ");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void createBusinessCust(String n, String t, int id, double am, int nl) {
		try{ 
			Class.forName("com.mysql.jdbc.Driver"); 	//if it doesn't work try com.mysql.cj.jdbc.Driver
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee)"  
					+ "values (?, ?, ?, ?, ?, ?)");
	    	  pstmt.setInt(1, id );
	    	  pstmt.setString(2, n);
	    	  pstmt.setInt(3, nl);
	    	  pstmt.setDouble(4, am);
	    	  pstmt.setString(5, t); 
	    	  pstmt.setInt(6, 5);
			int i = pstmt.executeUpdate();
	    	 if(i!=0){
	    		 System.out.println("created");
	    	 }
	    	 else{
	    		 System.out.println("failed to create");
	    	 }

		}catch(SQLException e) {
			System.out.print("SQLException: ");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void deletePrivateCust(int index) {
		try{ 
			Class.forName("com.mysql.jdbc.Driver"); 	//if it doesn't work try com.mysql.cj.jdbc.Driver
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
	        PreparedStatement pstmt = con.prepareStatement("DELETE FROM Private WHERE idBusiness =" + index);
	    	 int i = pstmt.executeUpdate();
	    	 if(i!=0){
	    		 System.out.println("deleted");
	    	 }
	    	 else{
	    		 System.out.println("failed to delete");
	    	 }

		}catch(SQLException e) {
			System.out.print("SQLException: ");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void createPrivateCust(String n, int id, double am, int c,int l) {
		try{ 
			Class.forName("com.mysql.jdbc.Driver"); 	//if it doesn't work try com.mysql.cj.jdbc.Driver
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee)"  
					+ "values (?, ?, ?, ?, ?, ?)");
	    	  pstmt.setInt(1, id );
	    	  pstmt.setInt(2, c);
	    	  pstmt.setString(3, n);
	    	  pstmt.setInt(4, l);
	    	  pstmt.setDouble(5, am); 
	    	  pstmt.setInt(6, 5);
			int i = pstmt.executeUpdate();
	    	 if(i!=0){
	    		 System.out.println("created");
	    	 }
	    	 else{
	    		 System.out.println("failed to create");
	    	 }

		}catch(SQLException e) {
			System.out.print("SQLException: ");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void adjustOverall(int id, double ov) {
		try{ 
			Class.forName("com.mysql.jdbc.Driver"); 	//if it doesn't work try com.mysql.cj.jdbc.Driver
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
			PreparedStatement pstmt = con.prepareStatement("UPDATE Employee set overall = ? where idEmployee = ?");
	    	  pstmt.setDouble(1, ov );
	    	  pstmt.setInt(2, id);
			int i = pstmt.executeUpdate();
	    	 if(i!=0){
	    		 System.out.println("adjusted");
	    	 }
	    	 else{
	    		 System.out.println("failed to adjust");
	    	 }

		}catch(SQLException e) {
			System.out.print("SQLException: ");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
