package satyam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/EnterpriseRegister")
public class EnterpriseRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Statement st;
	private Connection con;
	
       
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/rooms","root","2346");
			st = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String partnerName = request.getParameter("partnerName");
		String phoneNo = request.getParameter("mobileNo");
		String password = request.getParameter("password");
			String sql1 = "insert into register_partner values('"+partnerName+"','"+password+"','"+phoneNo+"')";
			try {
				st.executeUpdate(sql1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("enterprise.html").forward(request, response);
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
