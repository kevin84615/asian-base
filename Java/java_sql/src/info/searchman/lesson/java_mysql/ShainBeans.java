package info.searchman.lesson.java_mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;

public class ShainBeans {
	private String id;
	private String pw;
	private String data;
	private String todoid;

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private DataSource ds = null;
	
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = sdf.format(timestamp);

	public ShainBeans(HttpServletRequest request) {
		setId(request.getParameter("id"));
		setPw(request.getParameter("pw"));
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
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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

	// Database Connection Confirm
	public Boolean DatabaseConnectionConfirm() {
		try {
			String DCC = "";
			String sql = "select * from user where user_id = ('" + DCC + "')";

			doDataBase(sql);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ADD New Account
	public String addData() {
		try {
			id = id.replaceAll("(?i)(')", "''");
			pw = pw.replaceAll("(?i)(')", "''");
			String sql1 = "insert into user(user_id, password) values ('" + id + "','" + pw + "')";
			String sql2 = "INSERT INTO `company_db`.`todo` (`user_id`, `todo_text`, `finished`, `created_date`, `upload_date`) VALUES ('"
					+ id + "', '”ƒ‚¢•¨‚·‚é', '1', '" + time + "', '" + time + "')";

			doDataBase(sql1);
			doDataBase(sql2);

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
	public Boolean deleteData() {
		try {
			id = id.replaceAll("(?i)(')", "''");
			String sql1 = "delete from user where user_id=('" + id + "')";
			String sql2 = "delete from todo where user_id=('" + id + "')";
			doDataBase(sql1);
			doDataBase(sql2);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//Change Password
	public Boolean change_password() {
		try {
			id = id.replaceAll("(?i)(')", "''");
			pw = pw.replaceAll("(?i)(')", "''");
			String sql = "UPDATE user SET password ='" + pw + "' WHERE user_id = '" + id + "'";

			doDataBase(sql);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Account Login Verification
	public Boolean verificationData() {
 		boolean var = false;
		try {

			ResultSet rset = null;
			InitialContext ic = new InitialContext();

			ds = (DataSource) ic.lookup("java:comp/env/jdbc/searchman");

			conn = ds.getConnection();

			StringBuffer sql = new StringBuffer();
			id = id.replaceAll("(?i)(')", "''");
			pw = pw.replaceAll("(?i)(')", "''");
			sql.append("select * from user where user_id = '" + id + "' and password = '" + pw + "'");

			pstmt = conn.prepareStatement(new String(sql));

			pstmt.execute();

			String a = "";
			String b = "";
			String c = "";
			rset = pstmt.executeQuery();
			while (rset.next()) {
				a = rset.getString(1);
				b = rset.getString(2);
			}

			pstmt.close();
			conn.close();

			a = a.replaceAll("(?i)(')", "''");
			b = b.replaceAll("(?i)(')", "''");
			if (a.equals(id) && b.equals(pw)) {
				var = true;

			}
			if (id.equals(c) && pw.equals(c)) {
				var = false;
			}

			return var;

		} catch (Exception e) {
			e.printStackTrace();
			return var;
		}
	}
	//Add Todo
	public Boolean add_list() {
		try {
			id = id.replaceAll("(?i)(')", "''");
			data = data.replaceAll("(?i)(')", "''");
			String sql = "INSERT INTO `company_db`.`todo` (`user_id`, `todo_text`, `finished`, `created_date`, `upload_date`) VALUES ('"
					+ id + "', '" + data + "', '0', '" + time + "', '" + time + "')";

			doDataBase(sql);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//Delete Todo
	public Boolean delete_list(){
		try {
			String []todo= todoid.split(",");
			for(int i=1; i<=todo.length; i++)
			{
				todo[i] = todo[i].replaceAll("(?i)(')", "''");
				String sql = "DELETE FROM `company_db`.`todo` WHERE  `todo_id`='" + todo[i] + "'";
			
			doDataBase(sql);
			
			}
			return true;
		} 
		catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
			arrayIndexOutOfBoundsException.printStackTrace();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
	//UPDATE Todo
	public Boolean edit_list(){
		try {
			todoid = todoid.replaceAll("(?i)(')", "''");
			data = data.replaceAll("(?i)(')", "''");
			String sql = "UPDATE `todo` SET `todo_text` ='" + data + "',`upload_date` ='" + time + "' WHERE `todo_id` = '" + todoid + "'";

			doDataBase(sql);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}