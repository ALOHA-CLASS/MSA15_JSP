package board.servlet;

import java.io.IOException;
import java.util.UUID;

import board.DTO.Users;
import board.Service.UserService;
import board.Service.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
       
	/**
	 * [GET]
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// url : /users/idCheck
		String root = request.getContextPath();
		String path = request.getPathInfo();		// /idCheck
		
		// /idCheck - 아이디 중복 확인
		if( path.equals("/idCheck") ) {
			System.out.println("아이디 중복 확인...");
			String username = request.getParameter("username");
			boolean check = userService.idCheck(username);
			response.getWriter().print(check);
		}
		
		// /join	- 회원가입 화면
		if( path.equals("/join") ) {
			String url = "/page/join.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		// /login	- 로그인 화면
		if( path.equals("/login") ) {
			String url = "/page/login.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		
	}

	/**
	 * [POST]
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();			// /Board
		String path = request.getPathInfo();			// /join
		// /join - 회원가입
		if( path.equals("/join") ) {
			System.out.println("회원가입 요청 처리...");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			Users user = Users.builder()
							  .id( UUID.randomUUID().toString() )
							  .username(username)
							  .password(password)
							  .name(name)
							  .email(email)
							  .build();
			int result = userService.join(user);
			// 회원가입 성공
			if( result > 0 ) {
				response.sendRedirect(root + "/");
			} 
			// 회원가입 실패
			else {
				response.sendRedirect(root + "/join.jsp?error=true");
			}
		}
		// [회원가입 끝] ##############################################################
		
		
		// /login - 로그인
		if( path.equals("/login") ) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Users user = Users.builder()
							  .username(username)
							  .password(password)
							  .build();
			boolean result = userService.login(user);
			// 로그인 성공
			if( result ) {
				// 회원 조회
				Users loginUser = userService.selectByUsername(username);
				loginUser.setPassword(null);
				// 세션에 사용자 정보 등록
				HttpSession session = request.getSession();
				session.setAttribute("loginId", user.getUsername());
				session.setAttribute("loginUser", loginUser);
				response.sendRedirect(root + "/");
			} 
			// 로그인 실패
			else {
				response.sendRedirect(root + "/login.jsp?error=true");
			}
		}
		// [로그인 끝] ##############################################################
	}

}