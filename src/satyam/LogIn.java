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
import javax.servlet.http.HttpSession;


@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private Statement st;

	
   
	@Override
	public void init() throws ServletException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/rooms","root","2346");
			st = con.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		boolean valid;
		try {
			String mobileNo = request.getParameter("mobileNo");
			String password = request.getParameter("password");
			valid = check(mobileNo, password);
		PrintWriter out = response.getWriter();	
		if(valid) {
			
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(300);
			String sql = "select * from register where mobileNo = '"+mobileNo+"' and password = '"+password+"'";
			ResultSet rs;
			try {
				rs = st.executeQuery(sql);
				while(rs.next()){
					
					String fullName = rs.getString("fullName");
					String phoneNo = rs.getString("mobileNo");
					session.setAttribute("fullName", fullName);
					session.setAttribute("mobileNo", phoneNo);
					request.getRequestDispatcher("Home.jsp").include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else
			out.println("Invalid User");
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
	
	public boolean check(String mobileNo, String password) throws SQLException {
		int check = 0;
		
		String sql = "select * from register where mobileNo = '"+mobileNo+"' and password = '"+password+"'";
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				check = 1;
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(check == 1) 
			return true;
		return false;
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
