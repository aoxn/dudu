package com.dudu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dudu.core.OSearcher;

/**
 * Servlet implementation class ContextLoader
 */
@WebServlet("/ContextLoader")
public class ContextLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static OSearcher searcher = null;

    public ContextLoader() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		searcher = new OSearcher();
	}
	
	public static OSearcher getSearcher(){
		if(searcher == null)
			searcher = new OSearcher();
		return searcher;
	}
}
