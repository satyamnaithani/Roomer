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
import javax.servlet.http.HttpSession;

@WebServlet("/HotelRegister")
public class HotelRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Statement st;
	private Connection con;
       
   @Override
public void init() throws ServletException {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/rooms", "root", "2346");
		st = con.createStatement();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String partnerName = (String)session.getAttribute("partnerName");
		String hotelName = request.getParameter("hotelName");
		String city = request.getParameter("city");
		String location = request.getParameter("location");
		String description = request.getParameter("description");
		
		String sql = "insert into hotel values('"+partnerName+"','"+hotelName+"','"+city+"','"+location+"','"+description+"')";
		try {
			st.executeUpdate(sql);
			
			session.setAttribute("hotelName", hotelName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("PartnerHome.jsp").forward(request, response);
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
