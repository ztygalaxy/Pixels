package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.iImgDao;
import Dao.iUserDao;
import Entity.User;

/**
 * Servlet implementation class NewImaServlet
 */
@WebServlet("/NewImaServlet")
public class NewImaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewImaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String nameString=request.getParameter("name");
		String authorString=request.getParameter("author");
		System.out.println(nameString+"  "+authorString);
		iImgDao iimgDao=new iImgDao();
		try {
			if (iimgDao.insert(nameString, authorString)>=1){
				response.getWriter().write("true");
				System.out.println("true");
			}
			else {
				response.getWriter().write("error");
				System.out.println("error---");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
