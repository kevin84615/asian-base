package user_todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user_todo.EscapeCharacter;
import user_todo.UserServlet;

public class UserBeans {
	private String id;
	private String password;
	private String data;
	private String todoid;

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private DataSource ds = null;
	private ResultSet rset = null;

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time = sdf.format(timestamp);

	public UserBeans(HttpServletRequest request) {
		setId(request.getParameter("id"));
		setPassword(request.getParameter("password"));
		setData(request.getParameter("data"));
		setBox(request.getParameter("todoid"));
	}

	private void doDataBase(String sql) throws Exception {

		InitialContext ic = new InitialContext();

		ds = (DataSource) ic.lookup("java:comp/env/jdbc/searchman");
		conn = ds.getConnection();

		System.out.println(sql);
		pstmt = conn.prepareStatement(sql);

		boolean ret = pstmt.execute();

		pstmt.close();
		conn.close();

		if (ret != true) {
		}

	}

	public String getId() {
		id = EscapeCharacter.EscapeHTMLCharacter(id);
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		password = EscapeCharacter.EscapeHTMLCharacter(password);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId_edit() {
		return id;
	}

	public void setId_edit(String id) {
		this.id = id;
	}

	public String getPassword_edit() {
		return password;
	}

	public void setPassword_edit(String password) {
		this.password = password;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getBox() {
		return todoid;
	}

	public void setBox(String todoid) {
		this.todoid = todoid;
	}

	// ADD New Account
	public String add_user() {
		try {

			doDataBase(UserServlet.add_user(id, password));

		} catch (SQLIntegrityConstraintViolationException sQLIntegrityConstraintViolationException) {
			// Duplicate
			sQLIntegrityConstraintViolationException.printStackTrace();
			return "duplicated";

		} catch (Exception e) {
			// other error
			e.printStackTrace();
			return "other";
		}
		return "";

	}

	// Delete Account
	public Boolean delete_user() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_db?serverTimezone=UTC&useSSL=false",
					"suser", "spass");

			conn.setAutoCommit(false);
			Statement statement = conn.createStatement();

			statement.executeUpdate(UserServlet.delete_user(id));
			statement.executeUpdate(UserServlet.delete_user_todo(id));

			conn.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Change Password
	public Boolean change_password() {
		try {

			doDataBase(UserServlet.change_password(id, password));

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Account Login Verification
	public String verification_user() {
		String var = "false";
		try {

			InitialContext ic = new InitialContext();

			ds = (DataSource) ic.lookup("java:comp/env/jdbc/searchman");

			conn = ds.getConnection();

			pstmt = conn.prepareStatement(new String(UserServlet.select_user(id,password)));

			pstmt.execute();

			String user_id = "";
			String user_password = "";
			String c = "";
			rset = pstmt.executeQuery();
			while (rset.next()) {
				user_id = rset.getString(1);
				user_password = rset.getString(2);
			}

			pstmt.close();
			conn.close();

			if (user_id.equals(id) && user_password.equals(password)) {
				var = "true";

			}
			if (id.equals(c) && password.equals(c)) {
				var = "false";
			}

			return var;

		} catch (SQLException sQLException) {
			sQLException.printStackTrace();
			var = "disconnect";
			return var;
		} catch (Exception e) {
			e.printStackTrace();
			return var;
		}
	}

	public Boolean print_todo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(new String(UserServlet.select_todo(id)));

			pstmt.execute();

			rset = pstmt.executeQuery();

			ArrayList<String> list_todo_text = new ArrayList<>();
			ArrayList<String> list_upload_date = new ArrayList<>();
			ArrayList<String> list_todo_id = new ArrayList<>();
			
			ArrayList<ArrayList<String>> list_total = new ArrayList<>();
			
			while (rset.next()) {

				list_todo_text.add(EscapeCharacter.EscapeHTMLCharacter(rset.getString("todo_text")));

				list_upload_date.add(UserServlet.date_convert(rset.getString("upload_date")));

				list_todo_id.add(rset.getString("todo_id"));

			}
					
			list_total.add(list_todo_text);
			list_total.add(list_upload_date);
			list_total.add(list_todo_id);
			request.setAttribute("total", list_total);
			
			
			request.getRequestDispatcher("/member.jsp").forward(request, response);

			rset.close();
			pstmt.close();
			conn.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}