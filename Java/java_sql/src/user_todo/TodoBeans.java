package user_todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user_todo.EscapeCharacter;
import user_todo.TodoServlet;

public class TodoBeans {
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

	public TodoBeans(HttpServletRequest request) {
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

	// Add Todo
	public Boolean add_todo() {
		try {

			doDataBase(TodoServlet.add_todo(id, data, time));

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Delete Todo
	public Boolean delete_todo() {
		try {
			String[] todo = todoid.split(",");
			for (String todo_array : todo) {
				doDataBase(TodoServlet.delete_todo(todo_array));
			}
			return true;
		} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
			arrayIndexOutOfBoundsException.printStackTrace();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// UPDATE Todo
	public Boolean edit_todo() {
		try {

			doDataBase(TodoServlet.edit_todo(todoid, data, time));

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean print_todo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(new String(TodoServlet.select_todo(id)));

			pstmt.execute();

			rset = pstmt.executeQuery();

			ArrayList<String> list_todo_text = new ArrayList<>();
			ArrayList<String> list_upload_date = new ArrayList<>();
			ArrayList<String> list_todo_id = new ArrayList<>();
			ArrayList<ArrayList<String>> list_total = new ArrayList<>();
			while (rset.next()) {

				list_todo_text.add(EscapeCharacter.EscapeHTMLCharacter(rset.getString("todo_text")));

				list_upload_date.add(TodoServlet.date_convert(rset.getString("upload_date")));

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