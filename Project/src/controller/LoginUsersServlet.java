package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class LoginUsersServlet
 */
@WebServlet("/LoginUsersServlet")
public class LoginUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUsersServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userInfo") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();

		request.setAttribute("userList", userList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUsers.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("name");
		String BD1 = request.getParameter("date1");
		String BD2 = request.getParameter("date2");

		Date date1 = null;
		Date date2 = null;

		boolean dateFlg1 = false;
		boolean dateFlg2 = false;

		boolean errFlg = false;
		ArrayList<String> errMsgs = new ArrayList<String>();

		if (!(BD1 == "" && BD2 == "")) {
			try {
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			    format.setLenient(false);
			    if(BD1 != "") {
			    	format.parse(BD1);//Exception
				    date1 = Date.valueOf(BD1);
				}
			    if(BD2 != "") {
			    	format.parse(BD2);//Exception
				    date2 = Date.valueOf(BD2);
				}
			} catch (ParseException e) {
				errMsgs.add("生年月日の入力形式が正しくありません。（yyyy-MM-dd）");
				errFlg = true;
			}
		}
		if(BD1 == "") {
			dateFlg1 = true;
		}
		if(BD2 == "") {
			dateFlg2 = true;
		}

		if (errFlg) {
			String errs = "";
			request.setAttribute("errMsg", "検索に失敗しました。");
			for (int i = 0; i < errMsgs.size(); i++) {
				errs += errMsgs.get(i);
				if (i < errMsgs.size() - 1) {
					errs += "<br>";
				}
			}
			request.setAttribute("errs", errs);
			request.setAttribute("loginIdE", loginId);
			request.setAttribute("userNameE", userName);
			request.setAttribute("BDE1", BD1);
			request.setAttribute("BDE2", BD2);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUsers.jsp");
			dispatcher.forward(request, response);
			return;
		}

		UserDao userDao = new UserDao();
		List<User> userList = null;
		try {
			userList = userDao.search(loginId, userName, date1, date2, dateFlg1, dateFlg2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.setAttribute("loginIdE", loginId);
		request.setAttribute("userNameE", userName);
		request.setAttribute("BDE1", BD1);
		request.setAttribute("BDE2", BD2);
		request.setAttribute("userList", userList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUsers.jsp");
		dispatcher.forward(request, response);
	}

}
