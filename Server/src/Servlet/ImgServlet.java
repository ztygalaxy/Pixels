package Servlet;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.iImgDao;
import Entity.Ima;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/ImgServlet")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().println("GotIt");
		
		iImgDao iimgDao=new iImgDao();
		ArrayList<Ima> imaLists=new ArrayList<>();
		try {
			imaLists=iimgDao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().write("[");
		for (int i=0;i<imaLists.size()-1;i++){
			response.getWriter().append("{\"url\":\"http://192.168.0.7/Cyhs/"+imaLists.get(i).getUrl()+".jpg\","+"\"author\":\""+imaLists.get(i).getName()+"\"},");
		}
		response.getWriter().append("{\"url\":\"http://192.168.0.7/Cyhs/"+imaLists.get(imaLists.size()-1).getUrl()+".jpg\","+"\"author\":\""+imaLists.get(imaLists.size()-1).getName()+"\"}");
		response.getWriter().append("]");
		System.out.println("Í¼Æ¬·ÃÎÊ");
//		PrintWriter pw= response.getWriter();
//		pw.write("hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
