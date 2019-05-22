package user_todo;

public class EscapeCharacter {

		public static String EscapeHTMLCharacter(String original) {
		String newString = original;
		newString = newString.replaceAll("(?i)(&)", "&amp;");
		newString = newString.replaceAll("(?i)(')", "&#039;");
		newString = newString.replaceAll("(?i)(\")", "&quot;");
		newString = newString.replaceAll("(?i)(<)", "&lt;");
		newString = newString.replaceAll("(?i)(>)", "&gt;");	
		return newString; 
	}
		public static String EscapeSQLCharacter(String original) {
		String newString = original;
		newString = newString.replaceAll("(?i)(')", "''");	
		return newString; 
	}
}