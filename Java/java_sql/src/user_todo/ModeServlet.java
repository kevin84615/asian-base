package user_todo;

import java.io.IOException;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import user_todo.EscapeCharacter;

public class ModeServlet extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");

		String mode = request.getParameter("mode");
		String message01 = "";
		String message02 = "";
		boolean result = false;
		String userid = request.getParameter("id");
		String userpassword = request.getParameter("password");
		UserBeans user = new UserBeans(request);
		TodoBeans todo = new TodoBeans(request);

		switch (mode) {
		// Sign up Fail
		// Result.jsp
		case "add_User":
			if (userid.equals("") && userpassword.equals("")) {
				message01 = "�o�^���s���܂����B";
				message02 = "USERNAME��PASSWORD����͂��Ă��������B";
			} else if (userid.equals("")) {
				message01 = "�o�^���s���܂����B";
				message02 = "USERNAME����͂��Ă��������B";
			} else if (userpassword.equals("")) {
				message01 = "�o�^���s���܂����B";
				message02 = "PASSWORD����͂��Ă��������B";
			} else {
				// Check ID
				// Result.jsp
				String returnValue = user.add_user();
				if (returnValue.equals("duplicated")) {
					message01 = "ID���d�����܂��܂����B";
					message02 = "������x���͂��Ă��������B";
				} else if (returnValue.equals("other")) {
					message01 = "�����s���̃G���[���������܂���";
					message02 = "�Ǘ��҂ɘA�����Ă��������B";
				} else {
					message01 = "�o�^�������܂���";
				}
			}
			break;
		// Delete ID
		// delete_User.jsp
		case "delete_User":
			request.setAttribute("user", user);
			request.getRequestDispatcher("/delete_User.jsp").forward(request, response);
			break;
		// Delete ID Fail
		// Result.jsp
		case "delete_User_confirm":
			if (user.delete_user() == false) {
				message01 = "�폜���s���܂���";
				message02 = "�Ǘ��҂ɘA�����Ă��������B";
			} else {
				message01 = "�폜�������܂���";
			}
			break;
		// Change password
		// change_password.jsp
		case "change_password":
			request.setAttribute("user", user);
			request.getRequestDispatcher("/change_password.jsp").forward(request, response);
			break;
		// Change Password Fail
		// Result.jsp
		case "change_password_save":
			if (userpassword.equals("")) {
				message01 = "�ύX���s���܂����B";
				message02 = "PASSWORD����͂��Ă��������B";
			} else if (user.change_password() == false) {
				message01 = "�ύX���s���܂���";
				message02 = "�Ǘ��҂ɘA�����Ă��������B";
			} else {
				message01 = "�ύX�������܂���";
			}
			break;
		// Login Fail
		// Result.jsp
		case "user_verification":
			if (userid.equals("") && userpassword.equals("")) {
				message01 = "���O�C�����s���܂����B";
				message02 = "USERNAME��PASSWORD����͂��Ă��������B";
			} else if (userid.equals("")) {
				message01 = "���O�C�����s���܂����B";
				message02 = "USERNAME����͂��Ă��������B";
			} else if (userpassword.equals("")) {
				message01 = "���O�C�����s���܂����B";
				message02 = "PASSWORD����͂��Ă��������B";
			} else {
				String value = user.verification_user();
				if (value.equals("false")) {
					message01 = "���O�C�����s���܂����B";
					message02 = "USERNAME��������PASSWORD�͐���������܂���B";
				} else if (value.equals("disconnect")) {
					message01 = "DataBase�ڑ��ł��܂���B";
					message02 = "�Ǘ��҂ɘA�����Ă��������B";
				} else {
					result = true;
				}
			}
			break;
		// move to add_list
		// add_Todo.jsp
		case "add_Todo":
			request.setAttribute("user", user);
			request.getRequestDispatcher("/add_Todo.jsp").forward(request, response);
			break;
		// Add Fail
		// Result.jsp
		case "add_Todo_database":
			if (todo.add_todo() == false) {
				message01 = "�ۑ����s���܂����B";
				message02 = "�Ǘ��҂ɘA�����Ă��������B";
			} else {
				result = true;
			}
			break;
		// move to delete_list
		// delete_Todo.jsp
		case "delete_Todo":
			request.setAttribute("user", user);
			String[] select = request.getParameterValues("todoid");
			if (select == null) {
				message01 = "�폜���s���܂����B";
				message02 = "������x�I�����Ă��������B";
			} else {
				request.getRequestDispatcher("/delete_Todo.jsp").forward(request, response);
			}
			break;
		// delete certain data Fail
		// Result.jsp
		case "delete_Todo_database":
			if (todo.delete_todo() == false) {
				message01 = "�폜���s���܂����B";
				message02 = "�Ǘ��҂ɘA�����Ă��������B";
			} else {
				result = true;
			}
			break;
		// move to edit_list
		// edit_Todo.jsp
		case "edit_Todo":
			request.setAttribute("user", user);
			request.getRequestDispatcher("/edit_Todo.jsp").forward(request, response);
			break;
		// edit data Fail
		// Result.jsp
		case "edit_Todo_database":
			if (todo.edit_todo() == false) {
				message01 = "�ۑ����s���܂����B";
				message02 = "�Ǘ��҂ɘA�����Ă��������B";
			} else {
				result = true;
			}
			break;
		// Log out
		// index.jsp
		case "logout":
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			break;
		}
		// member.jsp
		if (result  && mode.equals("user_verification") || result == true && mode.equals("add_Todo_database")
				|| result  && mode.equals("delete_Todo_database")
				|| result  && mode.equals("edit_Todo_database")) {
			userid = EscapeCharacter.EscapeHTMLCharacter(userid);
			userpassword = EscapeCharacter.EscapeHTMLCharacter(userpassword);
			// Login Success
			if (mode.equals("user_verification")) {
				request.setAttribute("id", userid);
				request.setAttribute("password", userpassword);
				user.print_todo(request, response);
			}
			// back to member page
			if (mode.equals("add_Todo_database") || mode.equals("delete_Todo_database")
					|| mode.equals("edit_Todo_database")) {
				request.setAttribute("id", userid);
				request.setAttribute("password", userpassword);
				todo.print_todo(request, response);
			}
		}
		// checkbox didn't selected
		// back.jsp
		else if (message01.equals("�폜���s���܂����B") && mode.equals("delete_Todo")) {
			request.setAttribute("message01", message01);
			request.setAttribute("message02", message02);
			request.getRequestDispatcher("/back.jsp").forward(request, response);
		}
		// Result page
		// Result.jsp
		else {
			request.setAttribute("message01", message01);
			request.setAttribute("message02", message02);
			request.getRequestDispatcher("/Result.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
