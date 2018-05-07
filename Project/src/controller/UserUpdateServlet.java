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
import model.User;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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

		request.setAttribute("userUpdate", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String password = request.getParameter("password");
		String passwords = request.getParameter("passwords");
		String userName = request.getParameter("name");
		String BD = request.getParameter("birthDate");

		String id = request.getParameter("id");
		int ID = Integer.parseInt(id);

		Date birthDate = null;

		boolean errFlg = false;
		ArrayList<String> errMsgs = new ArrayList<String>();



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


		if (!(password == "" && passwords == "")) {
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

			UserDao userDao = new UserDao();
			User user = userDao.findByLoginId(ID);
			user.setName(userName);
			user.setBirthDate(birthDate);

			request.setAttribute("errs", errs);
			request.setAttribute("userUpdate", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}
		if (!(password == "" && passwords == "")) {
			if (password.equals(passwords)) {
				UserDao userDao = new UserDao();
				userDao.userUpdatePass(userDao.password(password), ID);
			}
		}

		UserDao userDao = new UserDao();
		userDao.userUpdate(userName, birthDate, ID);

		response.sendRedirect("LoginUsersServlet");
	}

}
