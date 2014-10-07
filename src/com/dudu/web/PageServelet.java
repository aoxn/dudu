package com.dudu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.search.TopDocs;

import com.dudu.core.OSearcher;
import com.dudu.core.PageVO;

/**
 * Servlet implementation class PageServelet
 */
@WebServlet("/PageServelet")
public class PageServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PageServelet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servlet doget");
		request.setCharacterEncoding("UTF-8"); 
		String str = request.getParameter("s");
		if(str!=null&&!str.equals("")){
			OSearcher sea = ContextLoader.getSearcher();
			TopDocs docs = null;
			if(str!=null&&str!="")
				docs = sea.getTermQueryPage(str,null,null);
			List<PageVO> a = Test.getReturn(docs,sea);
			response.setCharacterEncoding("UTF-8");
			request.setAttribute("docs",a);
		}
		request.getRequestDispatcher("/index.jsp").forward(request,response);
	}

}
