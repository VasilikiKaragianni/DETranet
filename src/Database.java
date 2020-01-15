/**
 *  This class connects the program with the mySQL database
 */

package gr.aueb.dmst.DETranet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class Database {
  public static void loadmySQLDatabase() {
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
    	Connection con = DriverManager.getConnection(  
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
		    } else if (d.equals("Business Customer Manager")) {
		      new BusinessCustomerManager(f, id, d, e, sa, da, l, p, o);	      	    		
		    } else if (d.equals("Teller")) {
		      new Teller(f, id, d, e, sa, da, l, p, o);
		    } else if (d.equals("Private Customer Manager")) {
		      new PrivateCustomerManager(f, id, d, e, sa, da, l, p, o);
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
		    		  e.getDepartment().equals("Business Customer Manager")) {
		         Business.getcSM().add(b);
		         break;
		       } else if(e.getIdEmployee() == id &&
		    		   e.getDepartment().equals("Business Customer Manager Vip")) {
		         Business.cSMVip.add(b);
		         break;
		       } else if(e.getIdEmployee() == id &&
		    		   e.getDepartment().equals("Business Customer Manager Delays")) {
		         Business.cSMDelays.add(b);
		         break;
		       } else if(e.getIdEmployee() == id &&
		    		   e.getDepartment().equals("Business Service Manager")) {
		       Business.bSM.add(b);
		         break;
		       } else if(e.getIdEmployee()== id && 
		    		   e.getDepartment().equals("Teller")) {
		         Business.tellerBusiness.add(b);
		       }
		     }
		   }
		            
	       s = "SELECT * FROM Private"; // +Private
		   rs = stmt.executeQuery(s);
		   while (rs.next()) {
		     int i = rs.getInt("idPrivate");
		     String n = rs.getString("name");
		     int nl = rs.getInt("nmbrLoans");
		     double a = rs.getDouble("amount");
		     int id = rs.getInt("idEmployee");
		     int ca = rs.getInt("cards");
		     Private p = new Private(n, i, a, ca, nl);
		     for(int c = 0; c < Employee.getEmployees().size(); c++) {
		       Employee e = Employee.getEmployees().get(c);
		       if(e.getIdEmployee() == id &&
		          e.getDepartment().equals("Private Customer Manager")) {
		          Private.pCM.add(p);
		          break;
		        } else if(e.getIdEmployee() == id &&
		          e.getDepartment().equals("Private Customer Manager Vip")) {
		          Private.pCMVip.add(p);
		          break;
		        } else if(e.getIdEmployee() == id &&
		          e.getDepartment().equals("Private Customer Manager Delays")) {
		          Private.pCMDelays.add(p);
		          break;
		        } else if(e.getIdEmployee() == id &&
	        	  e.getDepartment().equals("Teller")) {
		          Private.tellerPrivate.add(p);
		          break;
		         }
		       }
		    }
			rs.close();
			stmt.close();
			con.close();
		 }catch(SQLException e) {
		   System.out.print("SQLException: ");
		   System.out.println(e.getMessage());
		 } catch(ClassNotFoundException e1) {
		   e1.printStackTrace();
		 }
	}

  public static void saveEmployee(String f, int id,String d, String em, double s, int l, String p, double o) {
	  try{ 
		Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
		Connection con=DriverManager.getConnection(  
		  "jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
		long millis=System.currentTimeMillis();  
	    java.sql.Date date=new java.sql.Date(millis);
	    PreparedStatement pstmt = con.prepareStatement("INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, password)"
		  + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    pstmt.setInt(1, id);
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
	    } else{
	      System.out.println("failed to add");
	    }
	  } catch(SQLException e) {
		System.out.print("SQLException: ");
		System.out.println(e.getMessage());
	  } catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	  }
	}

  public static void deleteEmployee(int index) {
	  try{ 
		  Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
		Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
		PreparedStatement pstmt = con.prepareStatement ("DELETE FROM Business WHERE idEmployee= ?");
		pstmt.setInt(1, index);
		int i = pstmt.executeUpdate();
		pstmt = con.prepareStatement ("DELETE FROM Private WHERE idEmployee= ?");
		pstmt.setInt(1, index);
		i = pstmt.executeUpdate();
		pstmt = con.prepareStatement("DELETE FROM Employee WHERE idEmployee =" + index);
	    i = pstmt.executeUpdate();
	    if(i!=0){
	      System.out.println("deleted");
	    } else{
	      System.out.println("failed to delete");
	    }
	  }catch(SQLException e) {
		System.out.print("SQLException: ");
		System.out.println(e.getMessage());
	  } catch (ClassNotFoundException e1) {
		 e1.printStackTrace();
	  }	
	}
  
  public static void createBusinessCust(String n, String t, int id, double am, int nl, int ide) {
	  try{ 
		Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee)"  
		   + "values (?, ?, ?, ?, ?, ?)");
	    pstmt.setInt(1, id);
	    pstmt.setString(2, n);
	    pstmt.setInt(3, nl);
	    pstmt.setDouble(4, am);
	    pstmt.setString(5, t); 
	    pstmt.setInt(6, ide);
		int i = pstmt.executeUpdate();
	    if(i!=0){
	      System.out.println("created");
	    } else {
	      System.out.println("failed to create");
	    }
      } catch(SQLException e) {
		System.out.print("SQLException: ");
		System.out.println(e.getMessage());
	  } catch(ClassNotFoundException e1) {
		e1.printStackTrace();
	  }
	}
  
  public static void createPrivateCust(String n, int id, double am, int c,int l, int ide) {
		 try{ 
			Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee)"  
				+ "values (?, ?, ?, ?, ?, ?)");
		    pstmt.setInt(1, id );
		    pstmt.setInt(2, c);
		    pstmt.setString(3, n);
		    pstmt.setInt(4, l);
		    pstmt.setDouble(5, am); 
		    pstmt.setInt(6, ide);
			int i = pstmt.executeUpdate();
		      if(i!=0){
		  	System.out.println("created");
		  	} else{
		   	  System.out.println("failed to create");
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
		Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
	     PreparedStatement pstmt = con.prepareStatement("DELETE FROM Business WHERE idBusiness =" + index);
	     int i = pstmt.executeUpdate();
	     if(i!=0){
	       System.out.println("deleted");
	     } else{
	       System.out.println("failed to delete");
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
		Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
	    PreparedStatement pstmt = con.prepareStatement("DELETE FROM Private WHERE idPrivate =" + index);
	    int i = pstmt.executeUpdate();
	    if(i!=0){
	      System.out.println("deleted");
	    } else{
	      System.out.println("failed to delete");
	    }
      }catch(SQLException e) {
		System.out.print("SQLException: ");
		System.out.println(e.getMessage());
      } catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	  }
	}
  
  public static void updateLeaves(int id, int l) {
	  try{ 
		Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
	    PreparedStatement pstmt = con.prepareStatement("UPDATE Employee SET leaves = ? WHERE IdEmployee = ?");
	    pstmt.setInt(1, l );
	    pstmt.setInt(2, id);
	    int i = pstmt.executeUpdate();
	    if(i!=0){
	      System.out.println("updated");
	    } else{
	      System.out.println("failed to update");
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
		Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
		PreparedStatement pstmt = con.prepareStatement("UPDATE Employee SET overall = ? WHERE idEmployee = ?");
	    pstmt.setDouble(1, ov );
	    pstmt.setInt(2, id);
		int i = pstmt.executeUpdate();
	    if(i!=0){
	      System.out.println("adjusted");
	    } else{
	      System.out.println("failed to adjust");
	    }
	  }catch(SQLException e) {
		System.out.print("SQLException: ");
		System.out.println(e.getMessage());
	  } catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	 }
  }
  
  public static void computeSalary(int id, double s) {
	  try{ 
		Class.forName("com.mysql.cj.jdbc.Driver"); //if it doesn't work try com.mysql.jdbc.Driver
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://remotemysql.com:3306/sOiwyE9ekg","sOiwyE9ekg","nmOhM09Bay");
		PreparedStatement pstmt = con.prepareStatement("UPDATE Employee SET salary = ? WHERE idEmployee = ?");
	    pstmt.setDouble(1, s );
	    pstmt.setInt(2, id);
		int i = pstmt.executeUpdate();
	    if(i!=0){
	      System.out.println("computed");
	    } else{
	      System.out.println("failed to compute");
	    }
	  }catch(SQLException e) {
		System.out.print("SQLException: ");
		System.out.println(e.getMessage());
	  } catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	  }
    }
}
  
