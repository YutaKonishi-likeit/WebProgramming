package model;

import java.util.Date;

public class User {

	private int id;
	private String loginId;
	private String name;
	private Date birthDate;
	private Date birthDate1;
	private Date birthDate2;
	private String password;
	private String createDate;
	private String updateDate;
	private boolean dateFlg1;
	private boolean dateFlg2;

//	ログインユーザ
	public User(int id, String loginId, String name) {
		this.loginId = loginId;
		this.name = name;
	}


//	ユーザ一覧でユーザリスト表示
//	ユーザ一覧でユーザ検索
	public User(int id, String loginId, String name, Date birthDate, String password, String createDate,
			String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
		this.password = password;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

//	ユーザをIDで検索してDetailで表示するためのコンストラクタ
	public User(int id, String loginId, String name, Date birthDate, String createDate, String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public boolean isDateFlg1() {
		return dateFlg1;
	}

	public void setDateFlg1(boolean dateFlg1) {
		this.dateFlg1 = dateFlg1;
	}

	public boolean isDateFlg2() {
		return dateFlg2;
	}

	public void setDateFlg2(boolean dateFlg2) {
		this.dateFlg2 = dateFlg2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getBirthDate1() {
		return birthDate1;
	}

	public void setBirthDate1(Date birthDate1) {
		this.birthDate1 = birthDate1;
	}

	public Date getBirthDate2() {
		return birthDate2;
	}

	public void setBirthDate2(Date birthDate2) {
		this.birthDate2 = birthDate2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}
