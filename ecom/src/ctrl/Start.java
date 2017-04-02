package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.usersdao;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Start() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();           
		System.out.println(request.getSession().getId() + "\n");
		request.getSession().setAttribute("logged_in", false);
		String target = "/MainPage.jspx";

		//Main Page to Sign in page
		if(request.getParameter("signin") != null){
			System.out.println("reaches 1\n");
			System.out.println(request.getParameter("signin").toString() + "\n");
			request.setAttribute("register_page", false);
			target = "/Login2.jspx";
		}
		
		//Sign in/Main Page to Registration Page
		else if (request.getParameter("new_register") != null){
			System.out.println("reaches 2\n");
			System.out.println(request.getParameter("new_register").toString() + "\n");
			target = "/Login2.jspx";
			request.setAttribute("register_page", true);
		}

		//Login/Registration page to main page after user Login
		else if (request.getParameter("register_login") != null || request.getParameter("login") != null){
			usersdao u;	
			
			//Login page to Main page
			if(request.getParameter("login") != null){
				try {
					u = new usersdao();
					u.retrieve(request.getParameter("username"), request.getParameter("password"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			//Login to
			else {
				try {
					u = new usersdao();
					u.registerNewUser(request.getParameter("fname"), request.getParameter("lname"), request.getParameter("email"), request.getParameter("password"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}
			
			request.getSession().setAttribute("logged_in", true);
			target = "/MainPage.jspx";
		}
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
