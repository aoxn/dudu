package com.dudu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.search.TopDocs;

import com.dudu.core.OSearcher;

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
		String str = request.getParameter("s");
		OSearcher sea = ContextLoader.getSearcher();
		TopDocs docs = null;
		if(str!=null&&str!="")
			docs = sea.getTermQueryPage(str,null,null);
		Test.print(docs,sea);
	}

}
