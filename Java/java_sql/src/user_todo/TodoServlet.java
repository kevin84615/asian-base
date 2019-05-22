package user_todo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import user_todo.EscapeCharacter;

public class TodoServlet {

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time = sdf.format(timestamp);

	// Add Todo
	public static String add_todo(String id, String data, String time) {
		id = EscapeCharacter.EscapeSQLCharacter(id);
		data = EscapeCharacter.EscapeSQLCharacter(data);
		String sql = "INSERT INTO `company_db`.`todo` (`user_id`, `todo_text`, `finished`, `created_date`, `upload_date`) VALUES ('"
				+ id + "', '" + data + "', '0', '" + time + "', '" + time + "')";

		return sql;
	}

	// Delete Todo
	public static String delete_todo(String todo_array) {
		String todo_str = EscapeCharacter.EscapeSQLCharacter(todo_array);
		String sql = "DELETE FROM `company_db`.`todo` WHERE  `todo_id`='" + todo_str + "'";

		return sql;
	}

	// UPDATE Todo
	public static String edit_todo(String todoid, String data, String time) {
		todoid = EscapeCharacter.EscapeSQLCharacter(todoid);
		data = EscapeCharacter.EscapeSQLCharacter(data);
		String sql = "UPDATE `todo` SET `todo_text` ='" + data + "',`upload_date` ='" + time + "' WHERE `todo_id` = '"
				+ todoid + "'";
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