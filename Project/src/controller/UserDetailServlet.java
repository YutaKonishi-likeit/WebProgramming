package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserDetailServlet")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		int ID = Integer.parseInt(id);

		UserDao userDao = new UserDao();
		User user = userDao.findByLoginId(ID);

		request.setAttribute("userDetail", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDetail.jsp");
		dispatcher.forward(request, response);
	}



}
