package com.sopra;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AffichageAuto extends HttpServlet {

	private static final long serialVersionUID 		= 1L;
	public static final String VUE        			= "/code.jsp";

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
	/*	HttpSession session = request.getSession();
		session.setAttribute("couleur1", request.getParameter("up_couleur1"));
		session.setAttribute("couleur2", request.getParameter("up_couleur2"));
		session.setAttribute("metrique", request.getParameter("up_metrique"));
		session.setAttribute("siteAsuivre", request.getParameter("up_siteAsuivre"));
		
		// On récupère le paramètre de rafraichissement et on le passe de ms à min
		int refreshTime = Integer.parseInt(request.getParameter("up_refreshTime")) * 1000 * 60;
	
		session.setAttribute("refreshTime", refreshTime);*/

		/* la reception d'une requete GET, affichage des indispos */
		//this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		response.setContentType("text/html");
	    response.getOutputStream().println("Hello World !");
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		doGet(request, response);
	}
}