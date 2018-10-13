package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.DBManager;
import models.User;

/**
 * Servlet implementation class NeuUeberpruefen
 */
@WebServlet("/NeuUeberpruefen")
public class NeuUeberpruefen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NeuUeberpruefen() {
        super();
        // TODO Auto-generated constructor stub
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
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		if(password.equals(password2) != true)
		{
			showError(request, response, "Passwörter stimmen nicht überein");
			return;
		}
		
		DBManager db = new DBManager();
		
		try {
			if(db.getUserByEmail(email) != null) {
				showError(request, response, "Diese Email Addresse ist schon registriert");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		User user = new User(email, password, firstname, lastname);
		User user2 = null;
		try {
			db.createUser(user);
			user2 = db.getUserByEmail(email);
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
		
		if(user2 != null) {
			request.getSession().setAttribute("user", user2);
			
			RequestDispatcher d = request.getRequestDispatcher("Welcome.jsp");
			d.forward(request, response);
		}
		else {
			showError(request, response, "User konnte nicht angelegt werden");
		}
	}
	
	private void showError(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
		
		request.setAttribute("error", errorMessage);
		RequestDispatcher d = request.getRequestDispatcher("Register.jsp");
		d.forward(request, response);
	}

}
