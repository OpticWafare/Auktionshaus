package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.DBManager;
import models.User;

/**
 * Servlet implementation class UserUeberpruefen
 */
@WebServlet("/UserUeberpruefen")
public class UserUeberpruefen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUeberpruefen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email = request.getParameter("email");
		String password =  request.getParameter("password");
		System.out.println("Email: "+email);
		System.out.println("Password: "+password);
		
		DBManager db = new DBManager();
		
		User user = null;
		try {
			user = db.loginUser(email, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				db.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(user != null) {
			request.getSession().setAttribute("user", user);
			
			RequestDispatcher d = request.getRequestDispatcher("Welcome.jsp");
			d.forward(request, response);
		}
		else {
			request.setAttribute("error", "Error: E-Mail oder Password sind nicht korrekt");
			RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
			d.forward(request, response);
		}
	}

}
