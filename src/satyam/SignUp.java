package satyam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Statement st;
	private Connection con;
       
   @Override
public void init() throws ServletException {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/rooms", "root", "2346");
		st = con.createStatement();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fullName = request.getParameter("fullName");
		String country = request.getParameter("country");
		String mobileNo = request.getParameter("mobileNo");
		String password = request.getParameter("password");
		String sql = "insert into register values('"+fullName+"','"+country+"','"+mobileNo+"','"+password+"')";
		String sql1 = "select * from register";
		try {
			ResultSet rs = st.executeQuery(sql1);
			String FullName = rs.getString(1);
			String MobileNo = rs.getString(3);
			if(FullName.equals(MobileNo)) {
				request.getRequestDispatcher("Home.jsp");
				PrintWriter out = response.getWriter();
				out.println("User Already Created.");
				out.println("<a href="+"Login.jsp"+">Proceed to Login</a>");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			st.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("index.html").forward(request, response);;
	}
	
	@Override
	public void destroy() {
		
		try {
			con.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
