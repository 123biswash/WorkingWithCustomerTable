
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
	public int YorN;
	public String RorN;
	public String Address;
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
		  		
		  		System.out.println(rs.getString("Title"));
		  		System.out.println(rs.getString("FullName"));
		  		System.out.println(rs.getString("StreetAddress"));
		  		System.out.println(rs.getString("City"));
		  		System.out.println(rs.getString("State"));
		  		System.out.println(rs.getString("ZipCode"));
		  		System.out.println(rs.getString("Email"));
		  		System.out.println(rs.getString("Position"));
		  		System.out.println(rs.getString("Company"));
		  		System.out.print("Do you want to move to another customer or update this record (1 for new customer and 2 for update)?");
		  		YorN=sc.nextInt();
		  		if (YorN==2)
			  		{
						
						System.out.println("Enter what you would like to change the Address to: ");
						Address = sc.next();
						System.out.println("Enter what you would like to change the City to: ");
						City = sc.next();
						System.out.println("Enter what you would like to change the State to: ");
						State = sc.next();
						System.out.println("Enter what you would like to change the ZipCode to: ");
						ZipCode = sc.next();
						sql="UPDATE customers SET Address = ?, City=?,State=?,ZipCode=?  WHERE lastName=?";
						pstmt.setString(1, Address);
			            pstmt.setString(2, City);
			            pstmt.setString(3, State);
			            pstmt.setString(4, ZipCode);
			          
			            pstmt.executeUpdate();
			  		}
		  	} else {
			  	  System.out.println("Do you want to reenter the name or create a new entry with that last name?(R for reenter, N for new entry)");
			  	  RorN = sc.next();
			  	  if (RorN=="N")
				  		{
				  		System.out.println("Enter what you would like to change the address to: ");
						Address = sc.next();
						System.out.println("Enter what you would like to change the City to: ");
						City = sc.next();
						System.out.println("Enter what you would like to change the State to: ");
						State = sc.next();
						System.out.println("Enter what you would like to change the ZipCode to: ");
						ZipCode = sc.next();
						sql= "INSERT into customers(Address,City,State,ZipCode)values(?,?,?,?)";;
						   
				            pstmt.setString(1, Address);
				            pstmt.setString(2, City);
				            pstmt.setString(3, State);
				            pstmt.setString(4, ZipCode);
				          
				            pstmt.executeUpdate();
				  		}
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
		}while(RorN=="R"||YorN==1);
		sc.close();
	}
	
	
}
 
