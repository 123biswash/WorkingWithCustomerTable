
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Customer {
	Scanner sc = new Scanner(System.in);
	public int YorN=1;
	public String RorN="yes";
	public String StreetAddress;
	public String City;
	public String State;
	public String ZipCode;
	public String sql=null;
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt=null;
	private String lastName;
	
	public void CustomerMethod() throws Exception{
		
		
		do{
			try{
		
			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("Enter customer's last name:");

			
			lastName = sc.next();
            con = DriverManager.getConnection("jdbc:mysql://localhost/Customers?"+ "user=root&password=password");
            pstmt = con.prepareStatement("SELECT * FROM customers WHERE LastName = ?");
            
            pstmt.setString (1, lastName);
		  	rs = pstmt.executeQuery();
		  	
		  	if( rs.next() ){
		  		
		  		System.out.print (rs.getString("Title"));
		  		System.out.println(rs.getString("FullName"));
		  		System.out.println(rs.getString("StreetAddress"));
		  		System.out.println(rs.getString("City"));
		  		System.out.println(rs.getString("State"));
		  		System.out.println(rs.getString("ZipCode"));
		  		System.out.println(rs.getString("EmailAddress"));
		  		System.out.println(rs.getString("Position"));
		  		System.out.println(rs.getString("Company"));
		  		
		  		System.out.print("Do you want to move to another customer or update this record (1 for new customer and 2 for update)?");
		  		YorN=sc.nextInt();
		  		
			}else {
			  	  System.out.println("Do you want to reenter the name or create a new entry with that last name?(R for reenter, N for new entry)");
			  	  RorN = sc.next();
			  	  
			  	}
		  	if (YorN==2)
	  		{
				
				System.out.print("Enter what you would like to change the Address to: ");
				StreetAddress = sc.nextLine();
				System.out.println("Enter what you would like to change the City to: ");
				City = sc.nextLine();
				System.out.println("Enter what you would like to change the State to: ");
				State = sc.nextLine();
				System.out.println("Enter what you would like to change the ZipCode to: ");
				ZipCode = sc.nextLine();
				sql="UPDATE customers SET Address = ?, City=?,State=?,ZipCode=?  WHERE LastName=?";
				pstmt.setString(1, StreetAddress);
	            pstmt.setString(2, City);
	            pstmt.setString(3, State);
	            pstmt.setString(4, ZipCode);
	            pstmt.setString(5, lastName);
	          
	            pstmt.executeUpdate();
	            System.out.print("Do you want to move to another customer or close program? (1 for another entry and 3 for ending program)?");
		  		YorN=sc.nextInt();

	  		}
		  	if (RorN=="N")
	  		{
	  		System.out.println("Enter what you would like to change the address to: ");
			StreetAddress = sc.next();
			System.out.println("Enter what you would like to change the City to: ");
			City = sc.next();
			System.out.println("Enter what you would like to change the State to: ");
			State = sc.next();
			System.out.println("Enter what you would like to change the ZipCode to: ");
			ZipCode = sc.next();
			sql= "INSERT into customers(Address,City,State,ZipCode)values(?,?,?,?)";;
			   
	            pstmt.setString(1, StreetAddress);
	            pstmt.setString(2, City);
	            pstmt.setString(3, State);
	            pstmt.setString(4, ZipCode);
	          
	            pstmt.executeUpdate();
	  		}
		  	
            }
	
		catch (SQLException e){

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//rs.close();
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		}while(RorN.equals("R")||YorN==1);
		sc.close();
	}
	
	
}
 
/*
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomersApp {
	public static void main(String[] args) {
		String check = "yes";
		String inp = "1";
		Customers c1 = new Customers();
		Scanner sc = new Scanner(System.in);
		String sql = null;
		String sql2 = null;
		String sql3 = null;
		String lastName=null;
		String street=null;
		String city=null; 
		String state=null;
		String zip=null;
		do{
		if(inp.equals("1")){
			lastName=c1.getInput();
		}
		else if(inp.equals("2"))
		{
			System.out.println("Enter the new street: ");
			street = sc.nextLine();
			System.out.println("Enter the new city: ");
			city = sc.nextLine();
			System.out.println("Enter the new state: ");
			state = sc.nextLine();
			System.out.println("Enter the new zip code: ");
			zip = sc.nextLine();
			System.out.println();
		}
		sql = "select FirstName, LastName, StreetAddress,City,State,ZipCode, EmailAddress, Position from customers,Address,tblPosition "
				+ "where customers.AddressID=Address.AddressID AND customers.PositionID=tblPosition.PositionID AND customers.LastName=?";
		sql2 = "update Address set StreetAddress=?,City=?,State=?,ZipCode=? where Address.AddressID=customers.AddressID";
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;

		{
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Customers?"+ "user=root&password=password");
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, lastName);
            //if(inp.equals("1")){
            rs = pstmt.executeQuery();
    		while(rs.next()){

    			System.out.print(rs.getString("FirstName")+ "\t");
    			System.out.print(rs.getString("LastName") + "\n");
    			System.out.print(rs.getString("StreetAddress")+ "\n");
    			System.out.print(rs.getString("City")+", ");
    			System.out.print(rs.getString("State")+ " ");
    			System.out.print(rs.getString("ZipCode")+ "\n");
    			System.out.print(rs.getString("EmailAddress")+"\n");
    			System.out.print(rs.getString("Email"));
    			System.out.println();
    		}
           // }
            pstmt2 = con.prepareStatement(sql2);
    		pstmt2.setString(1, street);
    		pstmt2.setString(2, city);
    		pstmt2.setString(3, state);
    		pstmt2.setString(4, zip);
    		pstmt2.executeUpdate();
    	
        	}catch (SQLException e){
        
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}finally {
			try {

				pstmt.close();
				//pstmt2.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
		System.out.println("Press (1) to search for another customer or press (2) to Edit the customer's address.");
		inp = sc.nextLine();
		}while((inp.equals("1")) || inp.equals("2"));
	}
}
 
 *
 */
