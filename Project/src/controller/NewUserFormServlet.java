package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class NewUserFormServlet
 */
@WebServlet("/NewUserFormServlet")
public class NewUserFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserFormServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUserForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwords = request.getParameter("passwords");
		String userName = request.getParameter("name");
		String BD = request.getParameter("birthDate");


		Date birthDate = null;

//		if-password-入力欄
		boolean errFlg = false;
		ArrayList<String> errMsgs = new ArrayList<String>();



		if (loginId == "") {
			errMsgs.add("IDを入力してください。");
			errFlg = true;
		}
		if (password == "") {
			errMsgs.add("パスワードを入力してください。");
			errFlg = true;
		} else if (passwords == "") {
			errMsgs.add("パスワード確認を入力してください。");
			errFlg = true;
		} else if (!password.equals(passwords)) {
			errMsgs.add("入力したパスワードが異なっています。");
			errFlg = true;
		}
		if (userName == "") {
			errMsgs.add("ユーザ名を入力してください。");
			errFlg = true;
		}
		if (BD == "") {
			errMsgs.add("生年月日を入力してください。");
			errFlg = true;
		} else {
			try {
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			    format.setLenient(false);
			    format.parse(BD);//Exception
			    birthDate = Date.valueOf(BD);
			} catch (ParseException e) {
				errMsgs.add("生年月日の入力形式が正しくありません。（yyyy-MM-dd）");
				errFlg = true;
			}
		}

		if (errFlg) {
			String errs = "";
			request.setAttribute("errMsg", "登録に失敗しました。");
			for (int i = 0; i < errMsgs.size(); i++) {
				errs += errMsgs.get(i);
				if (i < errMsgs.size() - 1) {
					errs += "<br>";
				}
			}
			request.setAttribute("errs", errs);
			request.setAttribute("loginIdE", loginId);
			request.setAttribute("userNameE", userName);
			request.setAttribute("BDE", BD);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUserForm.jsp");
			dispatcher.forward(request, response);
			return;
		}


		UserDao userDao = new UserDao();
		userDao.newUser(loginId, userDao.password(password), userName, birthDate);

		response.sendRedirect("LoginUsersServlet");
	}

}
