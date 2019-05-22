package user_todo;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class UserServlet {
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time = sdf.format(timestamp);

	// Add New Account
	public static String add_user(String id, String password) {
		id = EscapeCharacter.EscapeSQLCharacter(id);
		password = EscapeCharacter.EscapeSQLCharacter(password);
		String sql = "insert into user(user_id, password) values ('" + id + "','" + password + "')";

		return sql;
	}

	// Delete Account
	public static String delete_user(String id) {
		id = EscapeCharacter.EscapeSQLCharacter(id);
		String sql = "delete from user where user_id=('" + id + "')";
		return sql;
	}

	// Delete user todo
	public static String delete_user_todo(String id) {
		id = EscapeCharacter.EscapeSQLCharacter(id);
		String sql = "delete from todo where user_id=('" + id + "')";
		return sql;
	}

	// Change Password
	public static String change_password(String id, String password) {
		id = EscapeCharacter.EscapeSQLCharacter(id);
		password = EscapeCharacter.EscapeSQLCharacter(password);
		String sql = "UPDATE user SET password ='" + password + "' WHERE user_id = '" + id + "'";
		return sql;
	}
	
	// Select user
	public static StringBuffer select_user(String id,String password) {
		id = EscapeCharacter.EscapeSQLCharacter(id);
		password = EscapeCharacter.EscapeSQLCharacter(password);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from user where user_id = '" + id + "' and password = '" + password + "'");
		return sql;
	}

	// Account Login Verification
	public static StringBuffer verification_user(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT `todo_id`,`todo_text`,`finished`,`upload_date` FROM `todo` WHERE user_id = '" + id + "'");
		return sql;
	}

	// Select user_todo
	public static StringBuffer select_todo(String id) {
		StringBuffer sql = new StringBuffer();
		id = EscapeCharacter.EscapeSQLCharacter(id);
		sql.append("SELECT `todo_id`,`todo_text`,`finished`,`upload_date` FROM `todo` WHERE user_id = '" + id + "'");
		return sql;
	}

	// Date_type_convert
	public static String date_convert(String upload_date) {
		upload_date = upload_date.replaceFirst("(?i)(-)", "îN");
		upload_date = upload_date.replaceFirst("(?i)(-)", "åé");
		upload_date = upload_date.replaceFirst("(?i)( )", "ì˙");
		upload_date = upload_date.substring(0, 16);

		int YEAR = Integer.parseInt(upload_date.substring(0, 4));
		int MONTH = Integer.parseInt(upload_date.substring(5, 7));
		int DATE = Integer.parseInt(upload_date.substring(8, 10));
		MONTH = MONTH - 1;
		DATE = DATE - 1;

		String[] week = new String[7];
		week[0] = "ì˙";
		week[1] = "åé";
		week[2] = "âŒ";
		week[3] = "êÖ";
		week[4] = "ñÿ";
		week[5] = "ã‡";
		week[6] = "ìy";

		Calendar YEAR_MONTH_DATE = Calendar.getInstance();
		YEAR_MONTH_DATE.set(Calendar.YEAR, YEAR);
		YEAR_MONTH_DATE.set(Calendar.MONTH, MONTH);
		YEAR_MONTH_DATE.set(Calendar.DAY_OF_MONTH, DATE);
		int DAY_OF_WEEK = YEAR_MONTH_DATE.get(Calendar.DAY_OF_WEEK);

		upload_date = upload_date.substring(5, 11) + "(" + week[DAY_OF_WEEK] + ")" + upload_date.substring(11, 16);

		return upload_date;
	}
}
