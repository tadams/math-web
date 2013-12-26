package com.tadams.web;

import com.tadams.FractionProblem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@SuppressWarnings("serial")
public class MathWebServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String mathProb = request.getParameter("mathProb");

        if (isNotEmpty(mathProb)) {
			try {
				FractionProblem fractionProblem = new FractionProblem(mathProb);
				request.setAttribute("problem", fractionProblem);

			} catch(Exception ignore) {}
		}
		
		forward("/index.jsp", request, response);
	}

	private void forward(String url,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);

		} catch (ServletException e) {
			throw new RuntimeException(e);
		}
	}
}
