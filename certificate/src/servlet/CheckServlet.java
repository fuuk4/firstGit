package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.User;
import dto.UserDTO;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			String pw = request.getParameter("pw");
			if(pw.isEmpty()){
				throw new NullPointerException();
			}
			HttpSession userval = request.getSession();
			if(100 <= id && id <= 130){
				UserDTO teach = User.teachSearch(id,pw);
				if(teach.equals(null)){
					throw new NullPointerException();
				}
				userval.setAttribute("user", teach);
			}else if(1000 <= id && id <= 5000){
				UserDTO stu = User.stuSearch(id, pw);
				if(stu.equals(null)){
					throw new NullPointerException();
				}
				userval.setAttribute("user", stu);
			}else{
				String view = "/WEB-INF/view/noUser.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
		}catch(NullPointerException e){
			String view = "/WEB-INF/view/noUser.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}catch(NumberFormatException e){
			String view = "/WEB-INF/view/noUser.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		String view = "/WEB-INF/view/home.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
