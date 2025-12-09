package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// @WebServlet("/MyServlet") // 요경 경로를 /MyServlet 매핑한다.
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet {		

	// GET 방식 요청을 처리하는 메소드
	protected void doGet(
		HttpServletRequest request,			// 요청 객체 
		HttpServletResponse response		// 응답 객체
	) throws ServletException, IOException {
		// HTML 컨텐츠 타입으로 "Hello, World!" 를 응답한다.
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("Hello, World!");
		response.getWriter().println("<br>");
		
		// 클라이언트의 요청 메소드 확인
		String method = request.getMethod();
		writer.println("HTTP 요청 메소드 : " + method);
		writer.println("<br>");
		
		// 요청 파라미터 가져오기
		String paramValue = request.getParameter("paramName");
		writer.println("파라미터 값: " + paramValue);
		writer.println("<br>");
		
		// 요청 URI 와 URL 가져오기
		String requestURI = request.getRequestURI();
		writer.println("요청 URI " + requestURI);
		writer.println("<br>");
		StringBuffer requestURL = request.getRequestURL();
		writer.println("요청 URL " + requestURL.toString());
		writer.println("<br>");
		
		// 특정 HTTP 헤더 값 가져오기
		String userAgent = request.getHeader("User-Agent");
		writer.println("User-Agent 헤더 : " + userAgent);
		writer.println("<br>");
		
		// 클라리언트 IP 주소 확인
		String remoteAddr = request.getRemoteAddr();
		writer.println("클라이언트 IP 주소 : " + remoteAddr);
		writer.println("<br>");
	}

	// POST 방식 요청을 처리하는 메소드
	protected void doPost(
		HttpServletRequest request, 
		HttpServletResponse response
	) throws ServletException, IOException {
		doGet(request, response);
	}

}
