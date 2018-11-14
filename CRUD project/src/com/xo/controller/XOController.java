package com.xo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xo.dao.LineDAO;
import com.xo.dao.LineDAOImplementation;
import com.xo.model.Line;

@WebServlet("/XOController")
public class XOController extends HttpServlet {
	
	private LineDAO dao;
	private static final long serialVersionUID = 1L;

       
    public XOController() {
    	dao = new LineDAOImplementation();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter( "action" );
		request.setAttribute("victory", 0 );
		if( action.equalsIgnoreCase( "reset" ) ) {
			dao.reset();
		}
		else if( action.equalsIgnoreCase( "play" ) ) {
		
		}
		else if( action.equalsIgnoreCase( "turn" ) ) {

		int	x = Integer.parseInt(request.getParameter("x"));
		int	y = Integer.parseInt(request.getParameter("y"));
			
			Line line = dao.get_line(y);
			line.setLine(x, dao.get_turn());
			dao.set_line(y, line);
			if(dao.check_victory())
				request.setAttribute("victory", 1 );
			else
				dao.swap_turn();

			
		}

		request.setAttribute("lines", dao.get_all_lines() );
		request.setAttribute("turn", dao.get_turn() );
		RequestDispatcher view = request.getRequestDispatcher( "/play.jsp" );
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = request.getRequestDispatcher( "/play.jsp" );
		request.setAttribute("lines", dao.get_all_lines());
		view.forward(request, response);
	}
}
