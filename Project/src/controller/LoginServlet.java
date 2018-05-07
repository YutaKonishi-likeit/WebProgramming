package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (!(session == null || session.getAttribute("userInfo") == null)) {
			response.sendRedirect("LoginUsersServlet");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao();
		User user = userDao.findByLoginInfo(loginId, userDao.password(password));

		if (user == null) {
			request.setAttribute("errMsg", "ログインIDまたはパスワードが違います。");
			request.setAttribute("errLoginId", loginId);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
			return;
		}

		HttpSession session = request.getSession();
		session.setAttribute("userInfo", user);

		response.sendRedirect("LoginUsersServlet");
	}

}
