package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

public class UserDao {


	public String password(String password) {
		Charset charset = StandardCharsets.UTF_8;
		String algorithm = "MD5";
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String result = DatatypeConverter.printHexBinary(bytes);
		return result;
	}

//	ログインするユーザ
	public User findByLoginInfo(String loginid, String password) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginid);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			int id = rs.getInt("id");
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");

			return new User(id, loginIdData, nameData);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

	}

//	ユーザ一覧でユーザ検索
	public List<User> search(String loginid, String userName, Date birthDate1, Date birthDate2, boolean dateFlg1,
			boolean dateFlg2)
			throws ParseException {
		List<User> userList = new ArrayList<User>();
		Connection conn = null;
		String sql = null;

		String birthdate1 = "";
		String birthdate2 = "";

		if (!dateFlg1) {
			birthdate1 = birthDate1.toString();
		}
		if (!dateFlg2) {
			birthdate2 = birthDate2.toString();
		}

		try {
			if (loginid != "") {

				sql = "SELECT * FROM user WHERE login_id = '?'";
				sql = sql.replace("?", loginid);
				if (userName != "") {

					sql += " AND name LIKE '%?%'";
					sql = sql.replace("?", userName);

					if (birthdate1 != "" && birthdate2 != "") {
						sql += " AND birth_date BETWEEN '?1' AND '?2'";
						sql = sql.replace("?1", birthdate1);
						sql = sql.replace("?2", birthdate2);
					} else if (birthdate1 != "") {
						sql += " AND birth_date > '?'";
						sql = sql.replace("?", birthdate1);
					} else if (birthdate2 != "") {
						sql += " AND birth_date < '?'";
						sql = sql.replace("?", birthdate2);
					} else if (birthdate1 == "" && birthdate2 == "") {

					}
				}
			} else if (userName != "") {

				sql = "SELECT * FROM user WHERE name LIKE '%?%'";
				sql = sql.replace("?", userName);

				if (birthdate1 != "" && birthdate2 != "") {
					sql += " AND birth_date BETWEEN '?1' AND '?2'";
					sql = sql.replace("?1", birthdate1);
					sql = sql.replace("?2", birthdate2);
				} else if (birthdate1 != "") {
					sql += " AND birth_date > '?'";
					sql = sql.replace("?", birthdate1);
				} else if (birthdate2 != "") {
					sql += " AND birth_date < '?'";
					sql = sql.replace("?", birthdate2);
				} else if (birthdate1 == "" && birthdate2 == "") {

				}
			} else if (birthdate1 != "" && birthdate2 != "") {
				sql = "SELECT * FROM user WHERE birth_date BETWEEN '?1' AND '?2'";
				sql = sql.replace("?1", birthdate1);
				sql = sql.replace("?2", birthdate2);
			} else if (birthdate1 != "") {
				sql = "SELECT * FROM user WHERE birth_date > '?'";
				sql = sql.replace("?", birthdate1);
			} else if (birthdate2 != "") {
				sql = "SELECT * FROM user WHERE birth_date < '?'";
				sql = sql.replace("?", birthdate2);
			} else if (birthdate1 == "" && birthdate2 == "") {

			}

			conn = DBManager.getConnection();
			sql += " AND id <> 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);
			}
			System.out.println(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;

	}

//	ユーザをIDで検索してDetailで表示
	public User findByLoginId(int ID) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, ID);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			int id = rs.getInt("id");
			String loginId = rs.getString("login_id");
			String name = rs.getString("name");
			Date birthDate = rs.getDate("birth_date");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");
			return new User(id, loginId, name, birthDate, createDate, updateDate);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

	}

//	ユーザ削除
	public void userDelete(int id) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM user WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void userUpdate(String userName, Date newBirthDate, int id) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE user SET name = ?, birth_date = ?, update_date = now() WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userName);
			pStmt.setDate(2, newBirthDate);
			pStmt.setInt(3, id);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void userUpdatePass(String password, int id) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE user SET password = ? WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, password);
			pStmt.setInt(2, id);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

//	新規ユーザ追加
	public void newUser(String newLoginId, String password, String userName, Date newBirthDate) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO user (login_id, name, birth_date, password, create_date, update_date) VALUES (?, ?, ?, ?, now(), now())";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, newLoginId);
			pStmt.setString(2, userName);
			pStmt.setDate(3, newBirthDate);
			pStmt.setString(4, password);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}

	}

//	ユーザ一覧でユーザリスト表示
	public List<User> findAll() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE id <> 1";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

}
