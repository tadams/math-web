package com.tadams.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tadams.Problem;

@SuppressWarnings("serial")
public class MathWebServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String mathProb = (String) request.getParameter("mathProb");

		if (mathProb != null) {
			try {
				Problem problem = new Problem(mathProb);
				request.setAttribute("problem", problem);
			} catch(Exception ignore){}
		}
		
		forward("/index.jsp", request, response);
	}

	private void forward(String url, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			throw new RuntimeException(e);
		}
	}
}
