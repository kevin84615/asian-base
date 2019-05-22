package info.searchman.lesson.java_mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class EditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DataSource ds;

	public void init() throws ServletException {
		try {

			InitialContext ic = new InitialContext();

			ds = (DataSource) ic.lookup("java:comp/env/jdbc/searchman");
		} catch (Exception e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		request.setCharacterEncoding("UTF-8");

		String mode = request.getParameter("mode");
		String status = "成功しました";
		String message = "";
		String userid = request.getParameter("id");
		String userpw = request.getParameter("pw");
		ShainBeans shain = new ShainBeans(request);
		// Database Connection Confirm
		// result.jsp
		if (shain.DatabaseConnectionConfirm() == false) {
			status = "DataBase接続できません。";
			message = "管理者に連絡してください。";
		} else {
			switch (mode) {
			// Sign up Fail
			// result.jsp
			case "add":
				status = "登録成功しました";
				if (userid.equals("") && userpw.equals("")) {
					status = "登録失敗しました。";
					message = "USERNAMEとPASSWORDを入力してください。";

				} else if (userid.equals("")) {
					status = "登録失敗しました。";
					message = "USERNAMEを入力してください。";

				} else if (userpw.equals("")) {
					status = "登録失敗しました。";
					message = "PASSWORDを入力してください。";

				}
				// Check ID
				// result.jsp
				String returnValue = shain.addData();
				if (returnValue.equals("duplicated")) {
					status = "IDが重複しまいました。";
					message = "もう一度入力してください。";

				} else if (returnValue.equals("other")) {

					status = "原因不明のエラーが発生しました";
					message = "管理者に連絡してください。";
				}
				break;
			// Delete ID
			// delete_ID.jsp
			case "delete":
				request.setAttribute("shain", shain);
				request.getRequestDispatcher("/delete_ID.jsp").forward(request, response);
				break;
			// Delete ID Fail
			// result.jsp
			case "delete_id":
				status = "削除成功しました";
				if (shain.deleteData() == false) {
					status = "削除失敗しました";
					message = "管理者に連絡してください。";
				}
				break;
			// Change password
			// change.jsp
			case "change":
				request.setAttribute("shain", shain);
				request.getRequestDispatcher("/change_password.jsp").forward(request, response);
				break;
			// Change Password Fail
			// result.jsp
			case "change_password":
				status = "変更成功しました";
				if (userpw.equals("")) {
					status = "変更失敗しました。";
					message = "PASSWORDを入力してください。";
				} else if (shain.change_password() == false) {
					status = "変更失敗しました";
					message = "管理者に連絡してください。";
				}
				break;
			// Login Fail
			// result.jsp
			case "verification":
				if (userid.equals("") && userpw.equals("")) {
					status = "ログイン失敗しました。";
					message = "USERNAMEとPASSWORDを入力してください。";
				} else if (userid.equals("")) {
					status = "ログイン失敗しました。";
					message = "USERNAMEを入力してください。";
				} else if (userpw.equals("")) {
					status = "ログイン失敗しました。";
					message = "PASSWORDを入力してください。";
				} else if (shain.verificationData() == false) {
					status = "ログイン失敗しました。";
					message = "USERNAMEもしくはPASSWORDは正しくありません。";
				}
				break;
			// move to add_list
			// add_list.jsp
			case "addlist":
				request.setAttribute("shain", shain);
				request.getRequestDispatcher("/add_list.jsp").forward(request, response);
				break;
			// Add Fail 
			// result.jsp
			case "addlist_database":
				if (shain.add_list() == false) {
					status = "保存失敗しました。";
					message = "管理者に連絡してください。";
				}
				break;
			// move to delete_list
			// delete_list.jsp
			case "deletelist":
				request.setAttribute("shain", shain);
				request.setAttribute("id", userid);
				request.setAttribute("pw", userpw);
				request.setAttribute("status", status);	
				String[] todo = request.getParameterValues("todoid");
				if (todo == null) {
					status = "削除失敗しました。";
					message = "もう一度選択してください。";
				}
				else {
				request.getRequestDispatcher("/delete_list.jsp").forward(request, response);
				}
				break;
			// delete certain data Fail
			// result.jsp
			case "deletelist_database":
				if (shain.delete_list() == false) {
					status = "削除失敗しました。";
					message = "管理者に連絡してください。";
				}
				break;
			// move to edit_list
			// edit_list.jsp
			case "editlist":
				request.setAttribute("shain", shain);
				request.getRequestDispatcher("/edit_list.jsp").forward(request, response);
				break;
			//edit data Fail
			//result.jsp
			case "editlist_database":
				if (shain.edit_list() == false) {
					status = "保存失敗しました。";
					message = "管理者に連絡してください。";
				}
				break;
			//Log out
			//index.jsp
			case "logout":
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				break;
			}
		}
		// Login Success or back to member page
		// member.jsp
		if (status.equals("成功しました") && mode.equals("verification")
				|| status.equals("成功しました") && mode.equals("deletelist_database")
				|| status.equals("成功しました") && mode.equals("addlist_database")
				|| status.equals("成功しました") && mode.equals("editlist_database")) {
			request.setAttribute("id", userid);
			request.setAttribute("pw", userpw);
			request.setAttribute("status", status);
			// Print List
			// member.jsp
			try {
				conn = ds.getConnection();

				StringBuffer sql = new StringBuffer();
				userid = userid.replaceAll("(?i)(')", "''");

				sql.append("SELECT `todo_id`,`todo_text`,`finished`,`upload_date` FROM `todo` WHERE user_id = '" + userid + "'");

				System.out.println(sql);

				pstmt = conn.prepareStatement(new String(sql));

				pstmt.execute();

				rset = pstmt.executeQuery();

				request.setAttribute("print", rset);

				request.getRequestDispatcher("/member.jsp").forward(request, response);

				rset.close();
				pstmt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {

					conn.close();
				} catch (Exception e) {
				}
			}
			request.getRequestDispatcher("/member.jsp").forward(request, response);
		}
		//checkbox didn't selected
		//back.jsp
		else if(status.equals("削除失敗しました。") && mode.equals("deletelist")){
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			request.getRequestDispatcher("/back.jsp").forward(request, response);
		}
		//error page
		//result.jsp
		else {
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
}
